package com.minimarket_api.services;

import com.minimarket_api.persistence.entities.AccountEntity;
import com.minimarket_api.persistence.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<AccountEntity> findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    public AccountEntity save(AccountEntity account) {
        return this.accountRepository.save(account);
    }

    public void saveAll(List<AccountEntity> accounts) {
        this.accountRepository.saveAll(accounts);
    }
}
