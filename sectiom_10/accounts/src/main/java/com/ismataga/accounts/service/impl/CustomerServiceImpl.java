package com.ismataga.accounts.service.impl;

import com.ismataga.accounts.dto.AccountsDto;
import com.ismataga.accounts.dto.CardsDto;
import com.ismataga.accounts.dto.CustomerDetailsDto;
import com.ismataga.accounts.dto.LoansDto;
import com.ismataga.accounts.entity.Accounts;
import com.ismataga.accounts.entity.Customer;
import com.ismataga.accounts.exception.ResourceNotFoundException;
import com.ismataga.accounts.mapper.AccountsMapper;
import com.ismataga.accounts.mapper.CustomerMapper;
import com.ismataga.accounts.repository.AccountsRepository;
import com.ismataga.accounts.repository.CustomerRepository;
import com.ismataga.accounts.service.CustomersService;
import com.ismataga.accounts.service.client.CardsFeignClient;
import com.ismataga.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId , String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }


        return customerDetailsDto;

    }
}
