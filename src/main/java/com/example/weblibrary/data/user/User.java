package com.example.weblibrary.data.user;

import com.example.weblibrary.data.user.userRoles.UserRoles;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    @NotNull
    @Size(min = 3, max = 20, message = "should be from 3 to 20 symbols")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "should contain only letters and digits")
    private String username;
    @NotNull
    @Size(min = 4, message = "should be bigger then 4 characters")
    @Pattern(regexp = "^(?=.*[0-9a-zA-Z]).{4,}$", message = "must contain at least one digit or letter")
    private String password;
    @NotNull
    private UserRoles role;
}
