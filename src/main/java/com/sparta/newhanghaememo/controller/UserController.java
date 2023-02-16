package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.LoginRequestDto;
import com.sparta.newhanghaememo.dto.SignupRequestDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController //json 방식으로 보려면 필수조건 = ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SuccessResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login") //위에서는 form태그로 넘어왔기 때문에
    // ModelAtrribute형태로 들어와서 requestbody쓰지 않음
    //이제는 ajax에서 body에 값이 넘어오기 때문에 Requestbody를 써야한다
    public SuccessResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return  userService.login(loginRequestDto, response);
    }

}