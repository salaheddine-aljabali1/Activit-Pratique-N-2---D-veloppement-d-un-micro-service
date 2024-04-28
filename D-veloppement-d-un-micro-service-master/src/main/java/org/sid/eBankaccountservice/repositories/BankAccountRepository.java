package org.sid.eBankaccountservice.repositories;

import org.sid.eBankaccountservice.entities.BankAccount;
import org.sid.eBankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource //tu demande  a Spring au demarrage demarre moi un web service restfull qui permet de gerer quoi
                            // les entiter de la bank
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @RestResource(path = "/bytype")
List<BankAccount> findByType(@Param("t") AccountType type);
}
