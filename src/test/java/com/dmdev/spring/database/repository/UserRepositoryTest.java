package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.dto.PersonalInfo;
import com.dmdev.spring.dto.PersonalInfo2;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkProjection(){
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable(){
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Page<User> result = userRepository.findAllBy(pageable);
        result.forEach(user -> System.out.println(user.getCompany().getName()));

        while (result.hasNext()){
            result = userRepository.findAllBy(result.nextPageable());
            result.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort(){
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstname)
                .and(sortBy.by(User::getLastname));

        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop(){

        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate(){
        User ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.getById(1L);
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries(){
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}