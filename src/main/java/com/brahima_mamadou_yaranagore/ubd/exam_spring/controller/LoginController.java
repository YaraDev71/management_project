package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.LoginRequest;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.LoginResponse;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){
        this.loginService= loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest) throws Exception {
        log.info("Executing login");

        ResponseEntity<LoginResponse> response = null;
        response = loginService.login(loginRequest);

        return response;
    }
}

