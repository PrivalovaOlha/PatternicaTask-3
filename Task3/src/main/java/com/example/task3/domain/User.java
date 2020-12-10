package com.example.task3.domain;

import java.time.LocalDate;

public class User {
    private String username;
    private String passwordHash;
    private String address;
    private String city;
    private String phoneNumber;
    private LocalDate lastEntryDate;

    public User(String username, String passwordHash, String address, String city, String phoneNumber, LocalDate lastEntryDate) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.lastEntryDate = lastEntryDate;
    }

    @Override
    public String toString() {
        return "User: " +
                "username='" + username + '\'' +
                ", lastEntryDate=" + lastEntryDate + ".";
    }

}
