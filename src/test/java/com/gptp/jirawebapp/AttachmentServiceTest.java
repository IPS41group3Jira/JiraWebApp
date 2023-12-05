package com.gptp.jirawebapp;


import com.gptp.jirawebapp.components.attachment.AttachmentRepository;
import com.gptp.jirawebapp.components.attachment.AttachmentService;
import com.gptp.jirawebapp.components.issue.IssueDto;
import com.gptp.jirawebapp.components.issue.IssueRepository;
import com.gptp.jirawebapp.components.issue.IssueService;
import com.gptp.jirawebapp.components.user.UserDto;
import com.gptp.jirawebapp.components.user.UserRepository;
import com.gptp.jirawebapp.data.Attachment;
import com.gptp.jirawebapp.data.IssueStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class AttachmentServiceTest {

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AttachmentService attachmentService;


    private IssueDto mockIssueDto() {
        IssueDto issueDto = new IssueDto();
        issueDto.setName("Issue 1");
        issueDto.setDescription("Description 1");
        issueDto.setProjectId(1L);
        issueDto.setCreationDate(new Date());
        issueDto.setDueDate(new Date());
        issueDto.setPriority(1);
        issueDto.setAssigneeId(2L);
        issueDto.setStatus(IssueStatus.IN_PROGRESS);
        return issueDto;
    }

    private UserDto mockUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("some@gmail.com");
        userDto.setPassword("password");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setCreatedProjects(new HashSet<>());
        userDto.setIssues(new HashSet<>());
        userDto.setAttachments(new HashSet<>());
        userDto.setComments(new HashSet<>());
        return userDto;
    }

    @Test

}
