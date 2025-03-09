package com.capitole.brand;

import com.capitole.exception.NullOrEmptyValueException;

import java.util.Objects;

public final class Brand {

    private final BrandId id;

    private final String name;

    private Brand(BrandId brandId, String name) {
        this.id = brandId;
        this.name = name;
    }

    public static Brand create(BrandId brandId, String name) {

        if (name == null || name.isEmpty()) {
            throw new NullOrEmptyValueException("Brand Name");
        }
        return new Brand(brandId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) && Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public BrandId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
