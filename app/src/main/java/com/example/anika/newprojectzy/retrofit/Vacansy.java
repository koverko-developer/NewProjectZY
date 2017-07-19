package com.example.anika.newprojectzy.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by x on 11.06.2017.
 */

public class Vacansy {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dolzhnost")
    @Expose
    private String dolzhnost;
    @SerializedName("trebovaniya")
    @Expose
    private String trebovaniya;
    @SerializedName("alldescr")
    @Expose
    private String alldescr;
    @SerializedName("unp")
    @Expose
    private String unp;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("town")
    @Expose
    private String town;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDolzhnost() {
        return dolzhnost;
    }

    public void setDolzhnost(String dolzhnost) {
        this.dolzhnost = dolzhnost;
    }

    public String getTrebovaniya() {
        return trebovaniya;
    }

    public void setTrebovaniya(String trebovaniya) {
        this.trebovaniya = trebovaniya;
    }

    public String getAlldescr() {
        return alldescr;
    }

    public void setAlldescr(String alldescr) {
        this.alldescr = alldescr;
    }

    public String getUnp() {
        return unp;
    }

    public void setUnp(String unp) {
        this.unp = unp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
