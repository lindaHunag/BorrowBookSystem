package com.example.demo.service.Impl;

import com.example.demo.enums.StatusCodeEnum;
import com.example.demo.exception.UserException;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Inventory;
import com.example.demo.model.vo.BookListResVo;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<BookListResVo> getBookList() throws Exception {
        try {
            List<Book> bookList = bookRepository.findAll();
            List<Inventory> inventoryList = new ArrayList<>();
            for (int i = 0; i < bookList.size(); i++) {
                Inventory inventory = inventoryRepository.findByISBN(bookList.get(i).getIsbn());
                inventoryList.add(inventory);
            }
            return inventoryList.stream().map(
                    x -> {
                        BookListResVo res = new BookListResVo();
                        res.setInventoryId(x.getInventoryId());
                        res.setISBN(x.getISBN());
                        res.setBookAuthor(x.getBook().getAuthor());
                        res.setBookName(x.getBook().getName());
                        res.setIntroduction(x.getBook().getIntroduction());
                        res.setStatus(x.getStatus());

                        return res;
                    })
                    .toList();

        } catch (Exception e) {
            throw new UserException(StatusCodeEnum.BookError);
        }
    }
}
