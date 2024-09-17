package com.ismataga.accounts;

import com.ismataga.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Management System",
                version = "1.0",
                description = "API for managing accounts and customers.",
                contact = @Contact(
                        name = "Ismat Aghamirzayev",
                        email = "ismataga.aghamirzayev@gmail.com",
                        url = "https://github.com/ismataga/accounts-management-system"
                ),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Find more info here",
                url = "https://swagger.io"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
