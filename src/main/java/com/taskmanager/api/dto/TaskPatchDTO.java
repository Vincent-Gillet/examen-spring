package com.taskmanager.api.dto;

import com.taskmanager.api.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPatchDTO {
    private TaskStatus status;
}
