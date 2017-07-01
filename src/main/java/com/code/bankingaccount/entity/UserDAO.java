package com.code.bankingaccount.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marinatoledo on 19/02/17.
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long>{

        User findByEmail(String email);

}
