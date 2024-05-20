package com.intcomexintegraciones.Service;

import com.intcomexintegraciones.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface ProductService {
    Product createProduct(Product product);
    CompletableFuture<Void> generateProductsAsync(int count);
    Page<Product> listarProductosPaginados(int page, int size);
    Product getProductById(Long id);
}