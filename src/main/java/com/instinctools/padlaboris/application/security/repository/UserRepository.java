package com.instinctools.padlaboris.application.security.repository;

import com.instinctools.padlaboris.application.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for working with the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
}
