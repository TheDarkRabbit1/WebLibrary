package com.example.weblibrary.data.user;

import com.example.weblibrary.data.user.userRoles.UserRoles;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    @NotNull
    private UserRoles role;
}
