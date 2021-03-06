package com.giumig.apps.lastminutequiz.model;

/**
 * Created by gius on 14/12/16.
 */

public class PurchasedGood {

    private Good good;
    private double basicSaleTax;
    private double importTax;

    public PurchasedGood() {
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public double getFinalPrice() {
        return (double)Math.round((good.getPrice() + getBasicSaleTax() + getImportTax()) * 100.0) / 100.0;
//        return good.getPrice() + getBasicSaleTax() + getImportTax();
    }


    public double getBasicSaleTax() {
        return basicSaleTax;
    }

    public void setBasicSaleTax(double basicSaleTax) {
        this.basicSaleTax = basicSaleTax;
    }

    public double getImportTax() {
        return importTax;
    }

    public void setImportTax(double importTax) {
        this.importTax = importTax;
    }


}
