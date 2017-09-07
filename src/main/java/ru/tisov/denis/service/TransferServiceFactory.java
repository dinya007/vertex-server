package ru.tisov.denis.service;

import ru.tisov.denis.dao.AccountDaoFactory;

/**
 * @author dinyat
 * 06/09/2017
 */
public class TransferServiceFactory {

    private static DefaultTransferService transferService = new DefaultTransferService(AccountDaoFactory.getAccountDao());

    private TransferServiceFactory() {
        throw new IllegalStateException("This constructor shouldn't be called");
    }

    public static TransferService getTransferService() {
        return transferService;
    }

}
