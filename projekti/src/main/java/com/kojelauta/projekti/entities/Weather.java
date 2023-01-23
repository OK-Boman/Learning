package com.kojelauta.projekti.entities;

public class Weather {
    private int temp;
    private String icon;
    private String iconUrl;

    public Weather() {

    }


    public Weather(double temp, String icon) {
        this.temp = (int) Math.round(temp);
        this.icon = icon;
        this.iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String toString() {
        return this.temp + ", " + this.icon;
    }

}
