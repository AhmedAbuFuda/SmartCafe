package com.ahmed.smartcoffee.pojo;

public class Order {
    private String id;
    private String name;
    private String size;
    private String quantity;
    private String table;
    private String price;

    public Order(String id, String name, String size, String quantity, String table, String price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.table = table;
        this.price = price;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
