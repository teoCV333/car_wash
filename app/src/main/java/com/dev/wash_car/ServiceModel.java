package com.dev.wash_car;

import java.util.Date;

public class ServiceModel {
    private String[] products;
    private Date schedule;
    private String typeService;

    public ServiceModel() {}

    public String[] getProducts() { return products; }
    public Date getSchedule() { return schedule; }
    public String getTypeService() { return typeService; }

    public void setProducts(String[] products) {
        this.products = products;
    }
    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }
}
