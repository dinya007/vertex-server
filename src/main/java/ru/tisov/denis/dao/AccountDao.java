package ru.tisov.denis.dao;

import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
public interface AccountDao {

    Account create(BigDecimal initialBalance);

    Account get(long id);

}
