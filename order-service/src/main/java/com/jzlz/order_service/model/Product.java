package com.jzlz.order_service.model;

/**
 * Classe que representa um Produto.
 * Contém informações básicas como id, nome, descrição e preço.
 */
public class Product {

    // Identificador único do produto
    private Long id;

    // Nome do produto
    private String nome;

    // Descrição detalhada do produto
    private String descricao;

    // Preço do produto (utilizar BigDecimal seria mais adequado para valores monetários)
    private double preco;

    /**
     * Construtor padrão necessário para frameworks que fazem deserialização.
     */
    public Product() {}

    /**
     * Construtor completo para facilitar criação de objetos Product.
     *
     * @param id Identificador único do produto
     * @param nome Nome do produto
     * @param descricao Descrição detalhada
     * @param preco Preço do produto
     */
    public Product(Long id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    // ----- Getters e Setters -----

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
