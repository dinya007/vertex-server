package ru.tisov.denis.service;

import ru.tisov.denis.dao.AccountDaoFactory;

/**
 * @author dinyat
 * 06/09/2017
 */
public class TransferServiceFactory {

    private TransferServiceFactory() {
        throw new IllegalStateException("This constructor shouldn't be called");
    }

    public static TransferService getTransferService() {
        return new DefaultTransferService(AccountDaoFactory.getAccountDao());
    }

}
