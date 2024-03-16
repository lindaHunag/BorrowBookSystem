package com.example.demo;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Borrowing_Record;
import com.example.demo.model.entity.Inventory;
import com.example.demo.model.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Utility;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@SpringBootTest

class DemoApplicationTests {
//	@Autowired
//	private LoginController accountController;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	BorrowingRecordRepository borrowingRecordRepository;

	@Autowired
	Utility util;

	// 每個test class執行前執行
	@BeforeClass
	public static void checkBeforeClass() {
		// 切片邏輯
		System.out.println("每個test class執行前執行");
	}

	// 每個test method之前執行
	@Before
	public void checkBefore(){
		System.out.println("每個test method之前執行");
	}

	// 單元測試


	// 忽略此單元測試（單元測試class中沒加上JUnit會執行失敗）
	@Ignore
	public  void wontRun(){
		System.out.println("忽略此單元測試");
	}


	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}

	@Test
	public void contextLoads() throws Exception {
//		try {
		User entity = new User();
//			entity.setAccount("user1");
//			entity.setPassword("111111");
//			String res = accountController.login(entity);
//			System.out.println(res);
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}

		entity.setPhoneNumber("0912345678");
		entity.setPazzW0rd(util.encryptPazzW0rdWithSalt("1111"));
		entity.setUserName("test");
		entity.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
//		entity.(new Timestamp(System.currentTimeMillis()));
		userRepository.save(entity);
	}

	@Test
	public void insertBook(){
		Book book = new Book();
		book.setIsbn("9789861755267");
		book.setAuthor("James Clear");
		book.setIntroduction("每天都進步1%，一年後，你會進步37倍；\n" +
				"　　每天都退步1%，一年後，你會弱化到趨近於0！\n" +
				"　　你的一點小改變、一個好習慣，將會產生複利效應，\n" +
				"　　如滾雪球般，為你帶來豐碩的人生成果！");
		book.setName("原子習慣：細微改變帶來巨大成就的實證法則");
		bookRepository.save(book);
	}

	@Test
	public void insertInventory(){
		Inventory inventory = new Inventory();
		inventory.setISBN("9789861755267");
		inventory.setStatus(0);
		inventory.setStoreTime(new Timestamp(System.currentTimeMillis()));

		Book book = new Book();
		book.setIsbn("9789861755267");
		book.setName("原子習慣：細微改變帶來巨大成就的實證法則");
		book.setAuthor("James Clear");

		inventory.setBook(book);
//		book.setInventory(inventory);
		inventoryRepository.save(inventory);
//		bookRepository.save(book);

	}

	@Test
	public void borrowBook(){
//		User user = userRepository.findByPhoneNumber("0912345678");
		Inventory inventory = inventoryRepository.findByInventoryId(3);
//		Borrowing_Record borrowing_record = new Borrowing_Record();
//		borrowing_record.setUser(user);
//		borrowing_record.setInventory(inventory);
//		borrowing_record.setBorrowingTime(new Timestamp(System.currentTimeMillis()));
//		borrowingRecordRepository.save(borrowing_record);

		inventory.setStatus(1);
		inventoryRepository.save(inventory);

	}

}
