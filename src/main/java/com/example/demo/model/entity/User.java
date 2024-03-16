package com.example.demo.model.entity;

import javax.persistence.*;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

// 資料庫欄位
@Entity
@RequiredArgsConstructor
@Data
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id", length = 10)
    private int userId;
    @Column(name = "Phone_number", length = 10)
    private String phoneNumber;
    @Column(name = "Password", length = 200)
    private String pazzW0rd;
    @Column(name = "User_Name", length = 10)
    private String userName;
    @Column(name = "Registration_Time")
    private Timestamp registrationTime;
    @Column(name = "Last_Login_Time")
    private Timestamp lastLoginTime;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Borrowing_Record> borrowing_recordList;
}