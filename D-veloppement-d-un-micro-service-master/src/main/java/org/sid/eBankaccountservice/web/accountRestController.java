package org.sid.eBankaccountservice.web;

import org.sid.eBankaccountservice.dto.BankAccountRequestDTO;
import org.sid.eBankaccountservice.dto.BankAccountResponseDTO;
import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.mappers.AccountMapper;
import org.sid.eBankaccountservice.repositories.BankAccountRepository;
import org.sid.eBankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class accountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public accountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
@GetMapping("/bankAccounts")
    public List<BankAccount> bankAccount(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccouts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
       //if(bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
        return accountService.addAccount(requestDTO);

    }
    @PutMapping ("/bankAccouts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
      BankAccount  account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);

    }
    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }
}
