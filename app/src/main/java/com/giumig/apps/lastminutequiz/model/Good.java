package com.giumig.apps.lastminutequiz.model;

/**
 * Created by gius on 13/12/16.
 */

public class Good {

    private GoodType type;
    private double price;
    private boolean isImported;

    public Good() {
    }

    public GoodType getType() {
        return type;
    }

    public void setType(GoodType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public enum GoodType    {
        BOOK, FOOD, MEDICAL_PRODUCT, GENERIC
    }

}
