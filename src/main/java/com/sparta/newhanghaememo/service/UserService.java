package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.LoginRequestDto;
import com.sparta.newhanghaememo.dto.SignupRequestDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.entity.UserRoleEnum;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC"; //이건 관리자 토큰
    //사용자 토큰 create token함수를 통해서 생성된다

    @Transactional
    public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        //Optional<User> found = userRepository.findByUsername(username);
        User found = userRepository.findByUsername(username);
        if (found!=null) {
            SuccessResponseDto successResponseDto =new SuccessResponseDto("중복된 사용자가 존재합니다.",400);
            return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.BAD_REQUEST);
            //throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 회원 권한 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        SuccessResponseDto successResponseDto =new SuccessResponseDto("회원가입 성공",200);
        return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);
    }


    //login시 이름과 비밀번호확인
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        /*// 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );*/
        User user = userRepository.findByUsername(username);
        if(user==null){
            SuccessResponseDto successResponseDto =new SuccessResponseDto("등록된 사용자가 없습니다.",400);
            return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            SuccessResponseDto successResponseDto =new SuccessResponseDto("회원을 찾을 수 없습니다.",400);
            return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.BAD_REQUEST);
            //throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //클라이언트에게 돌아갈 때 header부분에 AUTHORIZATION_HEADER와
        //토큰을 추가해서 보낸다 Authorization : Bearer <JWT>
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),user.getRole()));
        SuccessResponseDto successResponseDto =new SuccessResponseDto("로그인 성공",200);
        return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);
    }

}
