package com.yard.sale.domain.repository;

import com.yard.sale.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> getAll();

    public Optional<List<Product>> getByCategory(int categoryId);

    public Optional<List<Product>> getScareProducts(int quantity);

    public Optional<Product> getProduct(int productId);

    public Product save(Product product);

    public void delete(int productId);

    public Product update(int productId,Product newProduct);
}
