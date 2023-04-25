/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HLN.EPrice.entities;

/**
 *
 * @author Henri
 */
public class RequestInfo {
    private boolean success;
    private String message;

    public RequestInfo(){
        success = true;
        message = "Success";
    }
    
    public RequestInfo(String message){
        success=true;
        this.message=message;
    }
    
    public RequestInfo(String message, boolean success){
        this.success=success;
        this.message=message;
    }
    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
