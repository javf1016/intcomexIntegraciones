package com.intcomexintegraciones.Service.Impl;

import com.intcomexintegraciones.Exception.CategoryNotFoundException;
import com.intcomexintegraciones.Exception.NoElementsFoundException;
import com.intcomexintegraciones.Exception.ProductAlreadyExistsException;
import com.intcomexintegraciones.Exception.ResourceNotFoundException;
import com.intcomexintegraciones.Model.Category;
import com.intcomexintegraciones.Model.Product;
import com.intcomexintegraciones.Repository.CategoryRepository;
import com.intcomexintegraciones.Repository.ProductRepository;
import com.intcomexintegraciones.Service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {
    protected static final Logger logger = LogManager.getLogger();

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @CircuitBreaker(openTimeout = 5000, resetTimeout = 10000)
    public Product createProduct(Product product) {
        // Verificar si la categoría asociada al producto existe
        Category existingCategory = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("La categoría especificada no existe."));

        // Verificar si el producto ya existe con el mismo nombre
        boolean productExists = productRepository.existsByProductName(product.getProductName());

        if (productExists) {
            throw new ProductAlreadyExistsException("El producto con el mismo nombre ya existe.");
        }

        // Asignar la categoría al producto y luego guardarlo
        product.setCategory(existingCategory);
        return productRepository.save(product);
    }

    @Override
    @Async
    public CompletableFuture<Void> generateProductsAsync(int count) {
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() < 2) {
            throw new IllegalStateException("Debe haber al menos dos categorías para generar productos.");
        }

        Random random = new Random();
        logger.info("Iniciando la creación de productos...");
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setProductName("Producto " + (i + 1));
            product.setSupplierId((long) (random.nextInt(10) + 1));
            product.setCategory(categories.get(random.nextInt(categories.size())));
            product.setQuantityPerUnit((random.nextInt(10) + 1) + " unidades");
            product.setUnitPrice(random.nextDouble() * 100);
            product.setUnitsInStock(random.nextInt(500));
            product.setUnitsOnOrder(random.nextInt(200));
            product.setReorderLevel(random.nextInt(50));
            product.setDiscontinued(random.nextBoolean());

            productRepository.save(product);
            logger.info("Producto {} creado.", i + 1);
        }
        logger.info("Creación de productos finalizada.");
        return CompletableFuture.completedFuture(null);
    }


    @Override
    public Page<Product> listarProductosPaginados(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productsPage = productRepository.findAll(pageable);

        // Verificar si la página está vacía
        if (productsPage.isEmpty()) {
            throw new NoElementsFoundException("No se encontraron productos en la página solicitada.");
        }

        return productsPage;
    }
    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id));
        // Verificar si la categoría asociada al producto existe
        Category existingCategory = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("La categoría especificada no existe."));
        product.setCategory(existingCategory);
        return product;
    }
}