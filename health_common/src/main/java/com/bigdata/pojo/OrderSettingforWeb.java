package com.bigdata.pojo;

import java.io.Serializable;

public class OrderSettingforWeb implements Serializable {
    private int date;
    private int number;
    private int reservations;

    public OrderSettingforWeb(int date, int number, int reservations) {
        this.date = date;
        this.number = number;
        this.reservations = reservations;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReservation() {
        return reservations;
    }

    public void setReservation(int reservations) {
        this.reservations = reservations;
    }
}
