package com.khaja.taco_cloud.domain;


import jakarta.validation.constraints.NotBlank;


public class Delivery {
    @NotBlank(message = "name is required")
    private String deliveryName;
    @NotBlank(message = "street is required")
    private String deliveryStreet;
    @NotBlank(message = "city is required")
    private String deliveryCity;
    @NotBlank(message = "state is required")
    private String deliveryState;
    private String deliveryZip;

    public Delivery() {
    }

    public Delivery(String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState,
            String deliveryZip) {
        this.deliveryName = deliveryName;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    @Override
    public String toString() {
        return "Delivery [deliveryName=" + deliveryName + ", deliveryStreet=" + deliveryStreet + ", deliveryCity="
                + deliveryCity + ", deliveryState=" + deliveryState + ", deliveryZip=" + deliveryZip + "]";
    }

}
