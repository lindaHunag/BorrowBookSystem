package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

// 庫存
@Entity
@Data
@Table(name = "[Inventory]") // DB名稱
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Inventory_Id")
    private int inventoryId;
    @Column(name = "ISBN", length = 13)
    private String ISBN;
    @Column(name = "Store_Time")
    private Timestamp storeTime;
    @Column(name = "Status") // 0:在庫 1:出借中 2：整理中（歸還後未入庫） 4:遺失 5:毀損 6：廢棄
    private int status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ISBN_Id")
    private Book book;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory", fetch = FetchType.EAGER)
    private List<Borrowing_Record> borrowing_recordList;
}
