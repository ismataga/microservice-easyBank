package com.ismataga.accounts.service;

import com.ismataga.accounts.dto.CustomerDetailsDto;

public interface CustomersService {
   CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
