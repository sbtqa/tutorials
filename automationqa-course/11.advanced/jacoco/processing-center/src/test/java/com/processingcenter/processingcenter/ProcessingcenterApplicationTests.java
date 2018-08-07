package com.processingcenter.processingcenter;

import com.processingcenter.processingcenter.config.AppConfiguration;
import com.processingcenter.processingcenter.entity.Account;
import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.processingcenter.processingcenter.repositories.TransactionRepository;
import com.processingcenter.processingcenter.services.PaymentService;
import io.qameta.allure.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
@ComponentScan
@SpringBootTest
@EnableTransactionManagement
@EnableWebMvc
@Epic("Fuctional Tests")
@Feature("Processing center functional tests")
public class ProcessingcenterApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	private BasicDataSource dataSource;

	public static final String USERNAME1 = "Vladimir";
	public static final String USERLASTNAME1 = "Ivanov";
	public static final String USERNAME2 = "Ekaterina";
	public static final String USERLASTNAME2 = "Popova";
	public Account account1;
	public Account account2;

	PaymentService paymentService;

	@BeforeTest
	public void beforeTest() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/pcdb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("1");

		paymentService = new PaymentService();
	}

	@BeforeMethod
	public void beforeMethod(){
        paymentService.setAccountRepository(this.accountRepository);
        paymentService.setTransactionRepository(this.transactionRepository);
    }

	@Test(description = "Test that checks addition of new account to database")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Add new account test")
	public void addAccountTest() {

		//create account and check if they are added
		account1 = new Account(USERNAME1, USERLASTNAME1, 1000);
		Long id1 = accountRepository.save(account1).getAccId();
		Account addedAccount = accountRepository.findByAccId(id1);

		//check if newly added accounts present in db table
        Assert.assertEquals(account1, addedAccount);
	}

	@Test(description = "Test that checks deletion of existing account from database")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Delete account test")
	public void accountDeleteTest(){

        //create two new users and save them to db
        account1 = new Account(USERNAME1, USERLASTNAME1, 1000);
        Long idToDelete = accountRepository.save(account1).getAccId();

		//before deleting, let's check if account of id = 1 present in db table
		Account accountIdToDelete = accountRepository.findByAccId(idToDelete);
		Assert.assertEquals(idToDelete, accountIdToDelete.getAccId());

		//create first and second account and check if they are added
		account1 = accountRepository.findByAccId(idToDelete);
		accountRepository.delete(account1);
		Account deletedAccount = accountRepository.findByAccId(idToDelete);

		//check if we actually deleted account with given id
		Assert.assertNull(deletedAccount);
	}

	@Test(description = "Test that checks money transfer from one to another account balance with sufficient funds")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Payment with sufficient balance test")
	public void paymentWithSufficientFundsAccountTest(){

        //create two new users and save them to db
        account1 = new Account(USERNAME1, USERLASTNAME1, 1000);
        account2 = new Account(USERNAME2, USERLASTNAME2, 1000);

        Long userid1 = accountRepository.save(account1).getAccId();
        Long userid2 = accountRepository.save(account2).getAccId();

        //transfer all money user1 has in account
        paymentService.makePayment(userid1, userid2, accountRepository.findByAccId(userid1).getBalance());

        //check if balance of user1 in db  equals 0 now
        Assert.assertEquals((int)accountRepository.findByAccId(userid1).getBalance(), 0);
        Assert.assertEquals((int)accountRepository.findByAccId(userid2).getBalance(), 2000);
	}

	@Test(description = "Test that checks money transfer from one to another account balance with insufficient funds")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Payment with insufficient balance test")
	public void paymentWithInsuffiecientFundsAccountTest(){

        //create two new users and save them to db
        account1 = new Account(USERNAME1, USERLASTNAME1, 1000);
        account2 = new Account(USERNAME2, USERLASTNAME2, 1000);

        Long userid1 = accountRepository.save(account1).getAccId();
        Long userid2 = accountRepository.save(account2).getAccId();

        //transfer more money than user1 has in account
        paymentService.makePayment(userid1, userid2, accountRepository.findByAccId(userid1).getBalance() + 1);

        //check if balances of both account are the same as before
        Assert.assertEquals((int)accountRepository.findByAccId(userid1).getBalance(), 1000);
        Assert.assertEquals((int)accountRepository.findByAccId(userid2).getBalance(), 1000);
	}
}
