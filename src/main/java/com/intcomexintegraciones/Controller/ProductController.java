package com.intcomexintegraciones.Controller;

import com.intcomexintegraciones.Exception.CategoryNotFoundException;
import com.intcomexintegraciones.Exception.NoElementsFoundException;
import com.intcomexintegraciones.Exception.ProductAlreadyExistsException;
import com.intcomexintegraciones.Exception.ResourceNotFoundException;
import com.intcomexintegraciones.Model.Product;
import com.intcomexintegraciones.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listarProductosPaginados(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Product> productos = productService.listarProductosPaginados(page, size);
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (NoElementsFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (ResourceNotFoundException | CategoryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.createProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException | CategoryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> generateProducts() {
        try {
            productService.generateProductsAsync(100000).get(); // Esperar a que se completen las tareas asíncronas
            return new ResponseEntity<>("Se han generado 100,000 productos aleatorios.", HttpStatus.CREATED);
        } catch (InterruptedException | ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar los productos: " + ex.getMessage());
        }
    }
}