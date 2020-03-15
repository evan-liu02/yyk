package com.anju.yyk.client.data;

public class BillsItemData {
    private String title;
    private String price;

    public BillsItemData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillsItemData{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
