package com.khaja.tacocloud.tacoorder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TacoOrder implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private Date placedAt;
    @NotNull
    @Valid
    private Delivery delivery;
    @NotNull
    @Valid
    private CreditCard creditCard;
    @NotNull
    @Valid
    private List<Taco> tacos = new ArrayList<>();

    public TacoOrder() {

    }

    public TacoOrder(Delivery delivery, CreditCard creditCard, List<Taco> tacos) {
        this.delivery = delivery;
        this.creditCard = creditCard;
        this.tacos = tacos;
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

}
