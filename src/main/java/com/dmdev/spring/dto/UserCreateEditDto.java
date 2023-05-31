package com.dmdev.spring.dto;

import com.dmdev.spring.database.entity.Role;
import lombok.Value;
import org.postgresql.util.LruCache;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static org.postgresql.util.LruCache.*;

@Value
public class UserCreateEditDto {
    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rowPassword;

    LocalDate birthDate;

    @NotNull
    @Size(min = 3, max = 64)
    String lastname;

    @NotNull
    @Size(min = 3, max = 64)
    String firstname;

    Role role;

    Integer companyId;

    MultipartFile image;
}
