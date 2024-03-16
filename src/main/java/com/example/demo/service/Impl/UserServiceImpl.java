package com.example.demo.service.Impl;

import com.example.demo.enums.StatusCodeEnum;
import com.example.demo.exception.UserException;
import com.example.demo.model.dto.req.BorrowBookReqDto;
import com.example.demo.model.dto.req.UserBorrowingBookListReqDto;
import com.example.demo.model.entity.Borrowing_Record;
import com.example.demo.model.entity.Inventory;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.UserBorrowingBookListResVo;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<UserBorrowingBookListResVo> getUserBorrowingRecord(UserBorrowingBookListReqDto req) throws Exception {
        try {
            User user = userRepository.findByPhoneNumber(req.getPhoneNumber());
            List<Borrowing_Record> borrowing_recordList = borrowingRecordRepository.findByUserUserId(user.getUserId());
            List<UserBorrowingBookListResVo> res = new ArrayList<>();
            if (borrowing_recordList.size() > 0) {
                for (Borrowing_Record record : borrowing_recordList) {
                    Inventory inventory = inventoryRepository.findByInventoryId(record.getInventory().getInventoryId());
                    record.setInventory(inventory);
                }
                return borrowing_recordList.stream().map(
                        x -> {
                            UserBorrowingBookListResVo result = new UserBorrowingBookListResVo();
                            result.setBookName(x.getInventory().getBook().getName());
                            result.setBorrowTime(x.getBorrowingTime());
                            result.setReturnTime(x.getReturnTime());

                            return result;
                        })
                        .toList();
            }
        } catch (Exception e) {
            throw new UserException(StatusCodeEnum.BorrowingRecordError);
        }
        return null;
    }

    @Override
    @Transactional
    public void borrowBook(BorrowBookReqDto req) throws Exception {
        try {
            User user = userRepository.findByPhoneNumber(req.getPhoneNumber());
            Inventory inventory = inventoryRepository.findByInventoryId(req.getInventoryId());
            Borrowing_Record borrowing_record = borrowingRecordRepository.findByUserUserIdAndInventoryInventoryId(user.getUserId(), req.getInventoryId());
            if (borrowing_record == null || (borrowing_record != null && borrowing_record.getReturnTime() != null)) { // 無借閱紀錄或借閱相同書籍已歸還
                borrowing_record = new Borrowing_Record();
            }
            borrowing_record.setUser(user);
            borrowing_record.setInventory(inventory);

            if (req.getBorrowFlag().equals("0")) {
                borrowing_record.setBorrowingTime(new Timestamp(System.currentTimeMillis()));
                borrowingRecordRepository.save(borrowing_record);

                inventory.setStatus(1);
                inventoryRepository.save(inventory);
            } else {
                borrowing_record.setReturnTime(new Timestamp(System.currentTimeMillis()));
                borrowingRecordRepository.save(borrowing_record);

                inventory.setStatus(0);
                inventoryRepository.save(inventory);
            }
        } catch (Exception e) {
            throw new UserException(StatusCodeEnum.BorrowBookError);
        }
    }

}
