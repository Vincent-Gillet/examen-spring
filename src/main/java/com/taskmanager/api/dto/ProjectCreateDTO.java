package com.taskmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateDTO {
    private Long id;
    private String name;
    private UserDTO creator;
}
