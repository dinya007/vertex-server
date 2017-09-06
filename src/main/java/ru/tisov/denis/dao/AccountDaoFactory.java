package ru.tisov.denis.dao;

/**
 * @author dinyat
 * 06/09/2017
 */
public class AccountDaoFactory {

    private AccountDaoFactory() {
        throw new IllegalStateException("This constructor shouldn't be called");
    }

    public static AccountDao getAccountDao() {
        return new InMemoryAccountDao();
    }

}
