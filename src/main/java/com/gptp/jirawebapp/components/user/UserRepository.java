package com.gptp.jirawebapp.components.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDto, Long> {
    UserDto findByEmail(String email);

    @NonNull
    Optional<UserDto> findById(@NonNull Long id);

    @Query(value = "SELECT u FROM UserDto u " +
        "WHERE " + 
        "UPPER(u.firstName||' '||u.lastName) LIKE UPPER(CONCAT('%', REPLACE(:like, ' ', '%'), '%')) OR " +
        "UPPER(u.lastName||' '||u.firstName) LIKE UPPER(CONCAT('%', REPLACE(:like, ' ', '%'), '%'))")
    List<UserDto> findByFullName(@Param("like") String like);
}
