package com.SpringBootMVC.ExpensesTracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "expenses")
public class Expense {
    @Id
    private String id;
    @Field("amount")
    private int amount;
    @Field("date_time")
    private String dateTime;
    @Field("description")
    private String description;

    @Transient
    private String categoryName;
    @Transient
    private String date;
    @Transient
    private String time;

    @DBRef
    Client client;

    @DBRef
    Category category;

    public Expense() {
    }

    public Expense(int amount, String dateTime, String description, Client client, Category category) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.description = description;
        this.client = client;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateTime='" + dateTime + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", client=" + client +
                ", category=" + category +
                '}';
    }
}
