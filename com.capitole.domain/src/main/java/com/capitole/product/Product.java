package com.capitole.product;

import com.capitole.exception.NullOrEmptyValueException;

import java.util.Objects;

public final class Product {
    private final ProductId id;
    private final String name;

    private Product(ProductId productId, String name) {
        this.id = productId;
        this.name = name;
    }

    public static Product create(ProductId productId, String name) {

        if (name == null || name.isEmpty()) {
            throw new NullOrEmptyValueException("Name");
        }
        return new Product(productId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
