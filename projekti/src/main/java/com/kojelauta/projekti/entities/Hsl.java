package com.kojelauta.projekti.entities;

public class Hsl{
private String address;
private String time;
private String stop;
private String vehicle;

public Hsl(){

}
public Hsl(String address, String time, String stop, String vehicle){
    this.address=address;
    this.time=time;
    this.stop=stop;
    this.vehicle=vehicle;
}

public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public String getTime() {
    return time;
}
public void setTime(String time) {
    this.time = time;
}
public String getStop() {
    return stop;
}
public void setStop(String stop) {
    this.stop = stop;
}
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((time == null) ? 0 : time.hashCode());
    result = prime * result + ((stop == null) ? 0 : stop.hashCode());
    result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
    return result;
}
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Hsl other = (Hsl) obj;
    if (address == null) {
        if (other.address != null)
            return false;
    } else if (!address.equals(other.address))
        return false;
    if (time == null) {
        if (other.time != null)
            return false;
    } else if (!time.equals(other.time))
        return false;
    if (stop == null) {
        if (other.stop != null)
            return false;
    } else if (!stop.equals(other.stop))
        return false;
    if (vehicle == null) {
        if (other.vehicle != null)
            return false;
    } else if (!vehicle.equals(other.vehicle))
        return false;
    return true;
}
public String getVehicle() {
    return vehicle;
}
public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
}
@Override
public String toString(){
return this.address+" "+this.stop;
}

}