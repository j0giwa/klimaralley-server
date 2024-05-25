package de.thowl.klimaralley.server.web.schema.auth;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Schema for user registration")
public class RegisterSchema {

    @Schema(description = "User's first name", example = "Joe")
    private String firstname;

    @Schema(description = "User's last name", example = "Shmoe")
    private String lastname;

    @Schema(description = "Username for logging in", example = "joe_shmoe")
    private String username;

    @Schema(description = "User's email address", example = "joe.shmoe@example.com")
    private String email;

    @Schema(description = "Password for the account (Requires: one uppercase letter, one lowercase letter, one number, one special character)", example = "SecurePassword123!")
    private String password;

    @Schema(description = "Password verification (needs to be identical with the password)", example = "SecurePassword123!")
    private String verifyPassword;

}
