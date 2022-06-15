package com.app.droidcell.medizone;



public class Letter {

    private int id;
    private String note;
    private byte[] image;


    public Letter(int id, String note, byte[] image) {
        this.id = id;
        this.note = note;
        this.image = image;
    }


    public Letter(String note, byte[] image) {
        this.note = note;
        this.image = image;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
