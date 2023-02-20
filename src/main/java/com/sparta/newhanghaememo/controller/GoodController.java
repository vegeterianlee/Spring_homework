package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;
    @PostMapping("/api/heart_memo/{id}")
    // 응답 보내기
    public ResponseEntity<?> goodmemo(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long id) {
        return goodService.good_memo(userDetails.getUser(),id);
    }

    @PostMapping("/api/heart_comment/{id}")
    // 응답 보내기
    public ResponseEntity<?> goodcomment(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long id) {
        return goodService.good_comment(userDetails.getUser(),id);
    }

}
