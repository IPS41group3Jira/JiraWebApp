package com.gptp.jirawebapp.components.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleDto, Long> {

    @Query(value = "SELECT id FROM role WHERE role.name = 'Owner'", nativeQuery = true)
    Optional<Long> findOwnerRoleId();

    @Query(value = "SELECT id FROM role WHERE role.name = 'Member'", nativeQuery = true)
    Optional<Long> findMemberRoleId();

    @Query(value = "SELECT id FROM role WHERE role.name = 'Participant'", nativeQuery = true)
    Optional<Long> findParticipantRoleId();
}
