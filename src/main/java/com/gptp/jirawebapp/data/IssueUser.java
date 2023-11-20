package com.gptp.jirawebapp.data;

import com.gptp.jirawebapp.components.user.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueUser {
    @EqualsAndHashCode.Exclude
    @Id
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @EqualsAndHashCode.Exclude
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDto user;
}
