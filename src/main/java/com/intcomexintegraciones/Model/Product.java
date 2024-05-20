package com.intcomexintegraciones.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private Long supplierId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private String quantityPerUnit;

    private double unitPrice;

    private int unitsInStock;

    private int unitsOnOrder;

    private int reorderLevel;

    private boolean discontinued;
}
