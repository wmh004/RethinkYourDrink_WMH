package com.example.rethinkyourdrink;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ActivityClass {

    @PrimaryKey(autoGenerate = true)
    public int activityID;

    @NonNull
    private String day;

    @NonNull
    private String beverage;

    @NonNull
    private String amount;

    @NonNull
    private String description;

    public ActivityClass(@NonNull String day, @NonNull String beverage, @NonNull String amount, @NonNull String description) {
        this.day = day;
        this.beverage = beverage;
        this.amount = amount;
        this.description = description;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public String getDay() {
        return day;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }
    public String getBeverage() {
        return beverage;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
