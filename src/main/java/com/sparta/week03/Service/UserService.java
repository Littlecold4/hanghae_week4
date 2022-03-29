package com.sparta.week03.Service;


import com.sparta.week03.domain.User;
import com.sparta.week03.domain.UserRoleEnum;
import com.sparta.week03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(com.sparta.week03.dto.SignupRequestDto requestDto)throws IllegalArgumentException {
// 회원 ID 중복 확인
        String password = requestDto.getPassword();
        String username = requestDto.getUsername();
        String passwordChcek = requestDto.getPasswordCheck();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        if(username.length()<3){
            throw new IllegalArgumentException("아이디는 최소 3자 이상이어야 합니다.");
        }
        if(password.length()<4){
            throw new IllegalArgumentException("비밀번호는 4글자 이상이어야합니다");
        }
        if(!Pattern.matches("^[a-zA-Z0-9]*$",username)){
            throw new IllegalArgumentException("ID는 알파벳 대문자 소문자와 숫자만 입력 가능합니다");
        }
        if(password.contains(username)){
            throw new IllegalArgumentException("비밀번호에는 아이디가 포함될 수 없습니다.");
        }
        if(!password.equals(passwordChcek)){
            throw new IllegalArgumentException("비밀번호가 동일하지 않습니다.");
        }

// 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}