package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "[Borrowing_Record]")
public class Borrowing_Record {
    @Id
    @Column(name = "Borrowing_Record_Id")
    private int borrowingRecordId;
    @Column(name = "Borrowing_Time")
    private Timestamp borrowingTime;
    @Column(name = "Return_Time")
    private Timestamp returnTime;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "User_Id")
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Inventory_Id")
    private Inventory inventory;
}
