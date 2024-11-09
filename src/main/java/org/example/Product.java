package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Category category;

    public static List<Product> searchByName(List<Product> products, String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static List<Product> searchByCategory(List<Product> products, String category) {
        return products.stream()
                .filter(product -> product.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
