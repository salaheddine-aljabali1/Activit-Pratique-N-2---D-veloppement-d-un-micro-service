package org.sid.eBankaccountservice;

import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.entities.Customer;
import org.sid.eBankaccountservice.enums.AccountType;
import org.sid.eBankaccountservice.repositories.BankAccountRepository;
import org.sid.eBankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankAccountServiceApplication.class, args);
	}
@Bean
CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			Stream.of("nouamane","mohamed","yassin").forEach(c ->{
				Customer customer = Customer.builder()
						.name(c)
						.build();
				Customer save = CustomerRepository.save(customer);
				;
			} );
			CustomerRepository.findAll().forEach(customer -> {
				for (int i = 0; i < 10; i++) {
					BankAccount bankAccount = BankAccount.builder()

							.id(UUID.randomUUID().toString())
							.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
							.balance(10000 + Math.random() * 90000)
							.createdAt(new Date())
							.currency("MAD")
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});




		};
}

}
