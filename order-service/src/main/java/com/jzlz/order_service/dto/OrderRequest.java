package com.jzlz.order_service.dto;

import java.util.List;

public class PedidoRequest {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
