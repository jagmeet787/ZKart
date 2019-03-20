package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{
	public Account findByAccountNumber(Integer accountNumber);
	public List<Account> findByUserId(Integer userId);
}
