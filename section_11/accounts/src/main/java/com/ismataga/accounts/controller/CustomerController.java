package com.ismataga.accounts.controller;

import com.ismataga.accounts.dto.CustomerDetailsDto;
import com.ismataga.accounts.service.CustomersService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomersService customersService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazyBank-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})",
                                                                           message = "Mobile number must be 10 digits")
                                                                   String mobileNumber) {
        logger.debug("Eazy Bank correlationId found: {}" , correlationId);
        CustomerDetailsDto customerDetailsDto = customersService.fetchCustomerDetails(correlationId,mobileNumber);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);

    }
}
