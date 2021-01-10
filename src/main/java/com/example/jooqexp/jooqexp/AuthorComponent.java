package com.example.jooqexp.jooqexp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("spring.datasource")
@NoArgsConstructor
public class AuthorComponent {

    @NonFinal
    String url = "jdbc:postgresql://localhost:15432/postgres";
    @NonFinal
    String username = "postgres";
    @NonFinal
    String password = "postgres";

}
