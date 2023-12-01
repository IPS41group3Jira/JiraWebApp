package com.gptp.jirawebapp.components.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectDto, Long> {
    @NonNull
    Optional<ProjectDto> findById(@NonNull Long id);

    @Query(value = "SELECT p FROM ProjectDto p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', REPLACE(:like, ' ', '%'), '%'))")
    List<ProjectDto> findByNameSimilarity(@Param("like") String like);
}
