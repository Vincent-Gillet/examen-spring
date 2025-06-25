package com.taskmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGetTaskDetailsDTO {
     private List<TaskGetDTO> tasks;
}

