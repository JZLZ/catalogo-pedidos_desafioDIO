package com.jzlz.order_service.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe que representa um Pedido (Order).
 * Contém uma lista de produtos, o total do pedido e a data de criação.
 */
public class Order {

    // Lista dos produtos que fazem parte do pedido
    private List<Product> products;

    // Valor total do pedido, calculado somando o preço de todos os produtos
    private double total;

    // Data e hora em que o pedido foi criado
    private LocalDateTime dataCriacao;

    /**
     * Construtor padrão (necessário para frameworks e serialização)
     */
    public Order() {}

    /**
     * Construtor que recebe a lista de produtos, calcula o total e define a data atual
     * @param products lista de produtos do pedido
     */
    public Order(List<Product> products) {
        this.products = products;
        // Calcula o total somando o preço de cada produto
        this.total = products.stream()
                .mapToDouble(Product::getPreco)
                .sum();
        // Define a data de criação como o momento atual
        this.dataCriacao = LocalDateTime.now();
    }

    // ----- Getters e Setters -----

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
