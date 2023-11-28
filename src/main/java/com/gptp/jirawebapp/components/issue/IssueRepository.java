package com.gptp.jirawebapp.components.issue;

import com.gptp.jirawebapp.data.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<IssueDto> findByProjectId(Long projectId);

    List<IssueDto> findByAssigneeId(Long assigneeId);
}
