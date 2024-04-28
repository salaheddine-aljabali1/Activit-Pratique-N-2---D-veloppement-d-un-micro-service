package org.sid.eBankaccountservice.service;

import jakarta.transaction.Transactional;
import org.sid.eBankaccountservice.dto.BankAccountRequestDTO;
import org.sid.eBankaccountservice.dto.BankAccountResponseDTO;
import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.mappers.AccountMapper;
import org.sid.eBankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())

                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);

      /*  BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .type(saveBankAccount.getType())
                .createdAt(saveBankAccount.getCreatedAt())
                .balance(saveBankAccount.getBalance())
                .currency(saveBankAccount.getCurrency())
                .build();
*/
        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())

                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;

}
}

