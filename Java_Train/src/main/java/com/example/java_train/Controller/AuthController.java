package com.example.java_train.Controller;

import com.example.java_train.Models.AuthModel.LoginModel;
import com.example.java_train.Models.AuthModel.RegisterModel;
import com.example.java_train.Models.Response.LoginResponseModel;
import com.example.java_train.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseModel> register(
            @RequestBody RegisterModel registerModel
    ) {
        return ResponseEntity.ok(authService.Register(registerModel));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> login(
            @RequestBody LoginModel  loginModel
    ) {
        return ResponseEntity.ok(authService.Login(loginModel));
    }
}
