package com.jzlz.order_service.controller;

import com.jzlz.order_service.dto.OrderRequest;
import com.jzlz.order_service.model.Order;
import com.jzlz.order_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    // URL base para o serviço de catálogo de produtos
    private static final String CATALOG_URL = "http://localhost:8100";

    // Lista thread-safe para armazenar os pedidos em memória
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    /**
     * Endpoint para criar um novo pedido.
     * Recebe uma requisição JSON com listas de ids e/ou nomes de produtos.
     * Busca os produtos no serviço de catálogo e monta o pedido.
     * Retorna o pedido criado ou erro caso nenhum produto seja encontrado.
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        List<Product> products = new ArrayList<>();

        // Buscar produtos por IDs enviados
        if (request.getIds() != null) {
            for (Long id : request.getIds()) {
                try {
                    Product product = restTemplate.getForObject(CATALOG_URL + "/" + id, Product.class);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao buscar produto por ID " + id + ": " + e.getMessage());
                }
            }
        }

        // Buscar produtos por nomes enviados
        if (request.getNomes() != null) {
            for (String nome : request.getNomes()) {
                try {
                    String url = UriComponentsBuilder
                            .fromHttpUrl(CATALOG_URL + "/search")
                            .queryParam("nome", nome)
                            .toUriString();

                    Product[] foundProducts = restTemplate.getForObject(url, Product[].class);

                    if (foundProducts != null) {
                        for (Product p : foundProducts) {
                            products.add(p);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao buscar produto por nome '" + nome + "': " + e.getMessage());
                }
            }
        }

        // Se não encontrou nenhum produto, retorna erro 400
        if (products.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum produto encontrado para os IDs ou nomes fornecidos.");
        }

        // Cria o pedido e adiciona na lista
        Order order = new Order(products);
        orders.add(order);

        // Retorna 201 Created com o pedido criado
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * Endpoint para listar todos os pedidos criados.
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }
}
