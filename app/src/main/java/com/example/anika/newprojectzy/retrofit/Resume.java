package com.example.anika.newprojectzy.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by x on 11.06.2017.
 */

public class Resume {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fio")
    @Expose
    private String fio;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("opit")
    @Expose
    private String opit;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("town")
    @Expose
    private String town;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getOpit() {
        return opit;
    }

    public void setOpit(String opit) {
        this.opit = opit;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
