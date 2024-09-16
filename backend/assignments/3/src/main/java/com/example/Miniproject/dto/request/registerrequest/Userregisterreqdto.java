package com.example.Miniproject.dto.request.registerrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userregisterreqdto {
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String emailId;
}
