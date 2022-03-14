package com.example.olx_bare;


public class Listing {
    public String getLink() {
        return link;
    }
da k = new da();
    public void setLink(String link) {
        this.link = k.URL+"Upload/"+link;
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

    String link,head,detail,Number,Address,type,expprice;
    int sellerid,Lid;
    float lat,longi;
    public  void setAddress(String add){Address=add;}
    public String getAddress(){return Address;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNumber(String num){Number= num;};
    public String getNumber(){return Number;}
    public void setprice(String expected_price) {
    this.expprice=expected_price;
    }
    public String getExpprice(){
        return expprice;
    }
}
