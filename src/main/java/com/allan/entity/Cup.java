/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allan.entity;

/**
 *
 * @author Allan de souza melo
 */
public class Cup {
    private int id;
    private int times;
    private int user_id;
    private String cupDate;
    private String cupTime;
    
    public String getCupDate() {
        return cupDate;
    }

    public void setCupDate(String cupDate) {
        this.cupDate = cupDate;
    }

    public String getCupTime() {
        return cupTime;
    }

    public void setCupTime(String cupTime) {
        this.cupTime = cupTime;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}
