package com.ismataga.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDetailsDto {

    @NotEmpty(message = "Name must not be empty")
    @Size(min=3, max=30, message="The length of the name must not exceed 30")
    private String name;

    @NotEmpty(message="Email address can not be empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
    private CardsDto cardsDto;
    private LoansDto loansDto;
}
