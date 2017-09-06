package ru.tisov.denis.dao;

import ru.tisov.denis.domain.Account;

/**
 * @author dinyat
 * 06/09/2017
 */
public interface AccountDao {

    void create(Account account);

    Account get(long id);

}
