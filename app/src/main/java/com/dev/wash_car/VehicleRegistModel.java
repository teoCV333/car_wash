package com.dev.wash_car;

public class VehicleRegistModel {
    private String id;
    private String fullname;
    private String lastname;
    private String vehicleType;
    private String plate;
    private String age;
    private String phone;
    private String email;

    public VehicleRegistModel() {}

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getAge() {
        return age;
    }

    public String getPlate() { return age; }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPlate(String plate) { this.plate = plate; }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
