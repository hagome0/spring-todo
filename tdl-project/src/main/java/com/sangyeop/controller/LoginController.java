package com.sangyeop.controller;

import com.sangyeop.domain.UserRequestDto;
import com.sangyeop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hagome
 * @since 2019-03-29
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    /* 로그인 페이지 */
    @GetMapping("/sign_in")
    public String getSignIn() {
        return "/login/sign_in";
    }

    /* 회원가입 페이지 */
    @GetMapping("/sign_up")
    public String getSignUp() {
        return "/login/sign_up";
    }

    /* 회원가입 유효성 검증 */
    @PostMapping("/sign_up")
    public ResponseEntity<?> postSingUp(@RequestBody @Valid UserRequestDto userRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error:errors) {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("{}",HttpStatus.CREATED);
    }
}

