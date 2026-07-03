package com.example.dto;


import com.example.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;

    private String email;
    private String phone;

    
    //private String password;
    private UserStatus status;
    private Set<RoleDto> role = new HashSet<>();


}
