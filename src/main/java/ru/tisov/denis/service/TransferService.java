package ru.tisov.denis.service;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
public interface TransferService {

    void transfer(long sourceAccountId, long destinationAccountId, BigDecimal amount);

}
