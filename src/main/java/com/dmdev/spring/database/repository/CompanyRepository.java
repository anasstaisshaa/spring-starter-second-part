package com.dmdev.spring.database.repository;

import com.dmdev.spring.bpp.Auditing;
import com.dmdev.spring.bpp.Transaction;
import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select c from Company c " +
            "join fetch c.locales cl " +
            "where c.name =:name2")
    Optional<Company> findByName(@Param("name2") String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);


}
