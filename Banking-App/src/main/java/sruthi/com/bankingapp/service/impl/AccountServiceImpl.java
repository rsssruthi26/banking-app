package sruthi.com.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sruthi.com.bankingapp.dto.AccountDto;
import sruthi.com.bankingapp.entity.Account;
import sruthi.com.bankingapp.mapper.AccountMapper;
import sruthi.com.bankingapp.repository.AccountRepository;
import sruthi.com.bankingapp.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

         Account account = accountRepository
                 .findById(id)
                 .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
         return AccountMapper.mapToAccountDto(account);


    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doesn't exist"));
        if(amount > account.getBalance()) {
            throw new RuntimeException("Insufficient amount!");
        }
        double finalBalance = account.getBalance() - amount;
        account.setBalance(finalBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map((account)-> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Doesn't exist"));

        accountRepository.deleteById(id);


    }
}
