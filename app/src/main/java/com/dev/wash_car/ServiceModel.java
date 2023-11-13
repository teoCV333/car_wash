package com.dev.wash_car;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ServiceModel {
    private static final ServiceModel instance = new ServiceModel();
    private List<HashMap<String, Object>> products;
    private Date schedule;
    private String typeService;

    public ServiceModel() {}

    public static ServiceModel getInstance() {
        return instance;
    }

    public List<HashMap<String, Object>> getProducts() { return products; }
    public Date getSchedule() { return schedule; }
    public String getTypeService() { return typeService; }

    public void setProducts(List<HashMap<String, Object>> products) {
        this.products = products;
    }
    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }
}
