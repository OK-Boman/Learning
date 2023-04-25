/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HKS.weather2price.entities;

/**
 *
 * @author Solja
 */
public class RequestInfo {
    private String msg;
    
    public RequestInfo() {
        // tällaisiin luokkiin kannattaa aina tehdä myös oletuskonstruktori
    }
    
    public RequestInfo(String msg) {
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }    
}
