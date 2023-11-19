package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagementRepositoryImplTests {
TaskManagementRepository repository;
@BeforeEach
public void repositoryInit(){
    repository=new TaskManagementRepositoryImpl();
}
    @Test
    void memberExists_Should_ReturnFalse_When_MemberDoesNotExist(){
        Assertions.assertFalse(repository.memberExists("TEST"));
    }


}
