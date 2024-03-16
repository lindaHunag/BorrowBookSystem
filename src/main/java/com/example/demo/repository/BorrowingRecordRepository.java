package com.example.demo.repository;

import com.example.demo.model.entity.Borrowing_Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<Borrowing_Record, Integer> {
    List<Borrowing_Record> findByUserUserId(int userId);
    Borrowing_Record findByUserUserIdAndInventoryInventoryId(int userId, int inventoryId);
}
