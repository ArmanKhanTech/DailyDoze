package com.android.dailydoze.Utility;

import java.io.Serializable;

public class Details implements Serializable {
    public String name, phone, gender, dob, country, city, state, address, zip, type, blood, medical, other, height, weight, bp;

    public Details() {}

    public void setUserName(String name) {
        this.name = name;
    }
    public void setUserPhone(String phone) {
        this.phone = phone;
    }
    public void setUserGender(String gender) {
        this.gender = gender;
    }
    public void setUserDOB(String dob) {
        this.dob = dob;
    }
    public void setUserCountry(String country) {
        this.country = country;
    }
    public void setUserState(String state) {
        this.state = state;
    }
    public void setUserCity(String city) {
        this.city = city;
    }
    public void setUserAddress(String address) {
        this.address = address;
    }
    public void setUserZip(String zip) {
        this.zip = zip;
    }
    public void setUserType(String type) {
        this.type = type;
    }
    public void setUserBlood(String blood) {
        this.blood = blood;
    }
    public void setUserMedical(String medical) {
        this.medical = medical;
    }
    public void setUserOther(String other) {
        this.other = other;
    }
    public void setUserHeight(String height) {
        this.height = height;
    }
    public void setUserWeight(String weight) {
        this.weight = weight;
    }
    public void setUserBp(String bp) {
        this.bp = bp;
    }
}