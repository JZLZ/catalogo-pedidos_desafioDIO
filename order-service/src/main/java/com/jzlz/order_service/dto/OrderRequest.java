package com.jzlz.order_service.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO (Data Transfer Object) para receber a requisição de criação de pedido.
 * Permite informar os produtos desejados por listas de IDs e/ou nomes.
 */
public class OrderRequest {

    /**
     * Lista de IDs dos produtos a serem adicionados ao pedido.
     * Pode ser nulo ou vazio se forem usados nomes para busca.
     */
    private List<Long> ids;

    /**
     * Lista de nomes dos produtos a serem adicionados ao pedido.
     * Pode ser nulo ou vazio se forem usados IDs para busca.
     */
    private List<String> nomes;

    /**
     * Construtor padrão, necessário para frameworks que fazem deserialização.
     */
    public OrderRequest() {}

    // ----- Getters e Setters -----

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<String> getNomes() {
        return nomes;
    }

    public void setNomes(List<String> nomes) {
        this.nomes = nomes;
    }
}
