package org.sid.eBankaccountservice.service;

import org.sid.eBankaccountservice.dto.BankAccountRequestDTO;
import org.sid.eBankaccountservice.dto.BankAccountResponseDTO;
import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.enums.AccountType;

public interface AccountService   {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);

}
