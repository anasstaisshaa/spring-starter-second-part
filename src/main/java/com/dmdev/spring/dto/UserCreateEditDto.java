package com.dmdev.spring.dto;

import com.dmdev.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String lastname;
    String firstname;
    Role role;
    Integer companyId;
}
