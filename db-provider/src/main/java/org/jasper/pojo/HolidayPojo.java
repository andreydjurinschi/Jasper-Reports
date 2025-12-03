package org.jasper.pojo;


import java.time.LocalDate;

public class HolidayPojo {
    private String country;
    private String name;
    private LocalDate date;

    public HolidayPojo(String name, String country, LocalDate date) {
        this.name = name;
        this.country = country;
        this.date = date;
    }

    public HolidayPojo() {
    }

    public String getNAME() {
        return name;
    }

    public void setNAME(String name) {
        this.name = name;
    }

    public String getCOUNTRY() {
        return country;
    }

    public void setCOUNTRY(String country) {
        this.country = country;
    }

    public LocalDate getDATE() {
        return date;
    }

    public void setDATE(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HolidayPojo{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", date=" + date +
                '}';
    }
}
