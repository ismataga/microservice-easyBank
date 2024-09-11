package com.ismataga.accounts.service.client;

import com.ismataga.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

     @GetMapping(value = "api/fetch", consumes = "application/json")
     ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("eazyBank-correlation-id") String correlationId,
                                               @RequestParam String mobileNumber);
}
