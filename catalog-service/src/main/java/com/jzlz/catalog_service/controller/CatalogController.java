package com.jzlz.catalog_service.controller;

import com.jzlz.catalog_service.repository.ProductRepository;
import com.jzlz.catalog_service.entity.Product;
import com.jzlz.catalog_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
public class CatalogController {

    @Autowired
    private ProductRepository repository;

    //Teste de catalogo
    @GetMapping("/test")
    public String getCatalog() {
        return "Catálogo disponível!";
    }

    //criar produto
    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    //criar produtos em lotes
    @PostMapping("/batch")
    public List<Product> criarProdutosEmLote(@RequestBody List<Product> produtos) {
        return repository.saveAll(produtos);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if (repository.existsById(id)) {
            repository.deleteById(id);
            response.put("mensagem", "Produto excluído");
            return ResponseEntity.ok(response);
        } else {
            response.put("mensagem", "Produto não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Listar todos os produtos
    @GetMapping("/produtos")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public ResponseEntity<Product> getProductByName(@RequestParam String nome) {
        return repository.findAll().stream()
                .filter(prod -> prod.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
