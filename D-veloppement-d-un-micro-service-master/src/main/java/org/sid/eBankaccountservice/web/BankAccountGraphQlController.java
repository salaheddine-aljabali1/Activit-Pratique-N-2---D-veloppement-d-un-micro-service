package org.sid.eBankaccountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.eBankaccountservice.dto.BankAccountRequestDTO;
import org.sid.eBankaccountservice.dto.BankAccountResponseDTO;
import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.entities.Customer;
import org.sid.eBankaccountservice.repositories.BankAccountRepository;
import org.sid.eBankaccountservice.repositories.CustomerRepository;
import org.sid.eBankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<BankAccount>accountList(){
        return  bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return  bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
       return accountService.addAccount(bankAccount);

    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);

    }
    @MutationMapping
    public void  deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        //return true;

    }
    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
}
/*
@Data @NoArgsConstructor @AllArgsConstructor
class BankAccountDTO{
    private Double balance;
    private String type;
    private String currency;
}

record BankAccountDTO(Double balance, String type,String currency){

}*/
