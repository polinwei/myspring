package com.polinwei.myspring;

import com.polinwei.myspring.db.maria.dao.BankAccountDao;
import com.polinwei.myspring.db.maria.model.BankAccount;
import com.polinwei.myspring.db.maria.service.BankAccountService;
import com.polinwei.myspring.db.mongo.dao.UserDao;
import com.polinwei.myspring.db.mongo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyspringApplicationTests {

	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private BankAccountDao bankAccountDao;
	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
	}

	/**
	 * Delete all myspring.bank_account
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		bankAccountDao.deleteAll();
	}

	/**
	 * step 1: insert three records into myspring.bank_account
	 * step 2: verify whether has three records in table: bank_account
	 */
	@Test
	public void testInsertToBankAccount() {
		BankAccount bk = new BankAccount(1L, 1000, "Tom");
		bankAccountDao.save(bk);
		bk = new BankAccount(2L, 2000, "Jerry");
		bankAccountDao.save(bk);
		bk = new BankAccount(3L, 3000, "Donald");
		bankAccountDao.save(bk);

		Assert.assertEquals(3,bankAccountDao.count());
	}

	@Test
	public void mongoDbTest() {
		userDao.save(new User("polin.wei"));

	}
}
