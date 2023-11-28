package com.gptp.jirawebapp.components.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gptp.jirawebapp.components.project.ProjectDto;
import com.gptp.jirawebapp.components.user.UserDto;
import com.gptp.jirawebapp.data.Attachment;
import com.gptp.jirawebapp.components.comment.CommentDto;
import com.gptp.jirawebapp.data.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class SavedIssueDto {
    private Long id;

    private String name;

    private String description;

    private ProjectDto project;

    private Long creatorId;

    private Date creationDate;

    private Date dueDate;

    private Integer priority;

    private UserDto assignee;

    private IssueStatus status;

    @JsonIgnore
    private Set<Attachment> attachments;

    @JsonIgnore
    private Set<CommentDto> comments;
}
