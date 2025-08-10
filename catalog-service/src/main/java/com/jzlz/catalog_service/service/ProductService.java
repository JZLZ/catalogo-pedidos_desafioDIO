package com.jzlz.catalog.service;

import com.jzlz.catalog.entity.Product;
import com.jzlz.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product salvar(Product product) {
        return repository.save(product);
    }

    public List<Product> listarTodos() {
        return repository.findAll();
    }

    public Optional<Product> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
