package sruthi.com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sruthi.com.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
