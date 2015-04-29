package edu.millersville.sdmcinto.utils;

/**
 * Created by Millersville wStudent Shayne McIntosh
 * for CSCI 419 | Dr. Webster
 */
public class ListingItem {

    private int listingID;
    private String title;
    private double price;
    private String category;
    private String dateCreated;

    public ListingItem (int listingID, String title, double price, String category, String dateCreated) {

        this.listingID = listingID;
        this.title = title;
        this.price = price;
        this.category = category;
        this.dateCreated = dateCreated;
    }

    //getters

    public int getListingID() {
        return listingID;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    //setters

    public void setListingID(int id) {
        listingID = id;
    }

    public void setTitle(String t){
        title = t;
    }

    public void setPrice(double p){
        price = p;
    }

    public void setCategory(String c){
        category = c;
    }

    public void setDateCreated(String d){
        dateCreated = d;
    }
}
