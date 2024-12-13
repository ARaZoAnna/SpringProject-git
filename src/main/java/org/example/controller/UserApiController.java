package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.AddUserRequest;
import org.example.domain.UserRequest;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    @Autowired
    private final UserService userService;

    @PostMapping("/user")
    public String signup(@RequestBody Map m){
        System.out.println(m.toString());
        AddUserRequest UserRequest = new AddUserRequest();
        UserRequest.setUsername((String) m.get("username"));
        UserRequest.setPassword((String) m.get("password"));
        userService.save(UserRequest); //회원 가입 메서드 호출
        return "redirect:/login"; // 회원 가입이 완료된 이후에 로그인 페이지로 이동
    }
}

