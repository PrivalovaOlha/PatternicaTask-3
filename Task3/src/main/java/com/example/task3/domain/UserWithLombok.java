package com.example.task3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
@AllArgsConstructor
@ToString(of = {"username","lastEntryDate"})
public class UserWithLombok {
    private String username;
    private String passwordHash;
    private String address;
    private String city;
    private String phoneNumber;
    private LocalDate lastEntryDate;
}
