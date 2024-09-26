package com.example.JPAhands.repositories;

import com.example.JPAhands.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface Userrepository extends JpaRepository<User, UUID> {
    List<User> findAllByTenantId(UUID tenantId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username, logged_in = :loggedIn, time_zone = :timeZone WHERE id = :userId", nativeQuery = true)
    int updateUserDetails(@Param("userId") UUID userId, @Param("username") String username, @Param("loggedIn") int loggedIn, @Param("timeZone") String timeZone);
}
