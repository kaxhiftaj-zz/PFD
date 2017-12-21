package com.techease.pfd.Controller;

/**
 * Created by Adam Noor on 08-Dec-17.
 */

public class BestDealModel {

    private String ItemName;
    private String ItemPrice;
    private String ItemDes;
    private String ResturantImage;

    private String featured;

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemDes() {
        return ItemDes;
    }

    public void setItemDes(String itemDes) {
        ItemDes = itemDes;
    }

    public String getResturantImage() {
        return ResturantImage;
    }

    public void setResturantImage(String resturantImage) {
        ResturantImage = resturantImage;
    }




}
