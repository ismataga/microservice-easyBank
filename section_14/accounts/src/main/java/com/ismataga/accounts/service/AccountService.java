package com.ismataga.accounts.service;

import com.ismataga.accounts.dto.CustomerDto;

public interface AccountService {
    void createAccount(CustomerDto customer);

    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
    boolean updateCommunicationStatus(Long accountNumber);
}
