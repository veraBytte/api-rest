package com.yard.sale.persistance;

import com.yard.sale.domain.Product;
import com.yard.sale.domain.repository.ProductRepository;
import com.yard.sale.persistance.crud.ProductoCrudRepository;
import com.yard.sale.persistance.entity.Producto;
import com.yard.sale.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScareProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThan(quantity);
        return productos.map(
                productos1 -> mapper.toProducts(productos1)
        );
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

    @Override
    public Product update(int productId, Product newProduct) {
        return null;
    }

    public Producto update(int idProducto,Producto nuevoProducto){
        return productoCrudRepository.findById(idProducto)
                .map(
                        producto -> {
                            producto.setCategoria(nuevoProducto.getCategoria());
                            producto.setCantidadStock(nuevoProducto.getCantidadStock());
                            producto.setEstado(nuevoProducto.getEstado());
                            producto.setNombre(nuevoProducto.getNombre());
                            producto.setCodigoBarras(nuevoProducto.getCodigoBarras());
                            producto.setPrecio(nuevoProducto.getPrecio());
                            return productoCrudRepository.save(producto);
                        }
                ).get();
    }
}
