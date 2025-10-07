package com.prodapt.restfulapp.dto;

//import com.simplyfly.airticketbooking.enums.Role;
import com.prodapt.restfulapp.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
    private Role role;
}
