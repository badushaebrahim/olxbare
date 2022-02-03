package com.example.olx_bare;


public class Listing {
    public String getLink() {
        return link;
    }
da k = new da();
    public void setLink(String link) {
        this.link = k.URL+link;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public int getLid() {
        return Lid;
    }

    public void setLid(int lid) {
        Lid = lid;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLongi() {
        return longi;
    }

    public void setLongi(float longi) {
        this.longi = longi;
    }

    String link,head,detail;
    int sellerid,Lid,expprice;
    float lat,longi;

    public void setprice(int expected_price) {
    this.expprice=expected_price;
    }
    public int getExpprice(){
        return expprice;
    }
}
