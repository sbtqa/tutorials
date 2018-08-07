package com.processingcenter.processingcenter;

import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.processingcenter.processingcenter.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ProcessingcenterApplication implements CommandLineRunner{
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProcessingcenterApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
//		accountRepository.save(new Account("Tatyana", "Voteva", 31000));
//		accountRepository.save(new Account("Andrey", "Sokolov", 19000));
//		accountRepository.save(new Account("Gael", "Guedia", 150000));
//		accountRepository.save(new Account("Petr", "Petrov", 4100000));
// 		accountRepository.save(new Account("Oskar", "Guera", 31000));
//		accountRepository.save(new Account("Petr", "Ivanov", 400));
//		transactionRepository.save(new Transaction(1L, 2L, 100));
	}
}
