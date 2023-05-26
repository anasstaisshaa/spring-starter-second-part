package com.dmdev.spring.http.rest;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.dto.UserCreateEditDto;
import com.dmdev.spring.dto.UserFilter;
import com.dmdev.spring.dto.UserReadDto;
import com.dmdev.spring.service.CompanyService;
import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserReadDto> findAll(UserFilter filter, Pageable pageable){
        return userService.findAll(filter);
    }

    @GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("id") Long id){
        return userService.findAvatar(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id){
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Valid @RequestBody UserCreateEditDto user){
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id, @RequestBody @Valid UserCreateEditDto user){
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        if (!userService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
