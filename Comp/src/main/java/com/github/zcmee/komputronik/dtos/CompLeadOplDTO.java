package com.github.zcmee.komputronik.dtos;

import com.github.zcmee.komputronik.dictionaries.OrderStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CompLeadOplDTO implements Serializable {
    @NotNull(message = "Pole id polecenia jest polem obowiązkowym")
    private Long id;

    @NotNull(message = "Pole Status zamówienia jest polem obowiązkowym")
    private Integer orderStatus;

    @NotNull(message = "Pole status polecenia jest polem obowiązkowym")
    private String idTel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getIdTel() {
        return idTel;
    }

    public void setIdTel(String idTel) {
        this.idTel = idTel;
    }
}
