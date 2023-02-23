package com.sparta.newhanghaememo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j //로그를 남기는 방법
        //로그란 문제의 원인을 파악하기 위한 문제가 발생했을 때 당시의 정보, println은 일시 로그
@RequiredArgsConstructor //서비스 생성할 때 겸해서 같이 같이온다(repository)
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);
        //회원가입할 때는 토큰이 필요하지 않으므로 분기처리 필요
        //if(token != null)
            if(jwtUtil.validateToken(token)){
                Claims info = jwtUtil.getUserInfoFromToken(token);
                setAuthentication(info.getSubject()); //username을 매개변수로 받음
            }
            /*else{
                jwtExceptionHandler(response, "토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());
                return;
            }*/
        filterChain.doFilter(request,response);
    }
    //Authentication -> context -> SecurityContextHolder
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        //인증 객체를 만들고 context에 넣는다 -> 더 큰 개념인 홀더에도 넣는다
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    //따로 Dto형태가 아님에도 반환될 수 있도록 하는법, 컨트롤러 이전에 실행되는 거라 RestAPIExceptionhanlder적용X
    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new SuccessResponseDto(msg,statusCode));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
