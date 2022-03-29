package com.sparta.week03.domain;

import com.sparta.week03.Service.UserService;
import com.sparta.week03.dto.SignupRequestDto;
import com.sparta.week03.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    @DisplayName("회원이 요청한 관심상품 객체 생성")
    class CreateUser {

        private Long userId;
        private String username;
        private String password;
        private String passwordCheck;
        private String email;

        @BeforeEach
        void setup() {
            userId = 100L;
            username = "asddd";
            password = "1233";
            passwordCheck = "1233";
            email = "asd@asd.com";
        }

        @Test
        @DisplayName("정상 케이스")
        void createUser_Normal() {
            // given
            SignupRequestDto requestDto = new SignupRequestDto(
                    username,
                    password,
                    passwordCheck,
                    email
            );

            // when
            User user = new User(requestDto, userId);

            // then
//            assertNull(user.getId());
//            assertEquals(userId, user.getId());
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
//            assertEquals(passwordCheck, user.getPassword());
            assertEquals(email, user.getEmail());
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {
            @Nested
            @DisplayName("아이디 오류")
            class Title {
                @Test
                @DisplayName("아이디 이름이 4자리 이하")
                void fail1() {
                    // given
                    username = "ac";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            passwordCheck,
                            email
                    );

                    // when
//                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                        new User(requestDto, userId);
//                    });
                    Optional<User> found = Optional.empty();
                    String message = null;
                    try {
                        UserService.UserIDCheck(password, username, passwordCheck, found);
                    } catch (IllegalArgumentException e) {
                        message = e.getMessage();
                    }
                    // then
                    assertEquals("아이디는 최소 3자 이상이어야 합니다.", message);
                }

                @Test
                @DisplayName("이상한 문자")
                void fail2() {
                    // given
                    String username = "@#$$$$";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            passwordCheck,
                            email
                    );

                    // when
                    Optional<User> found = Optional.empty();
                    String message = null;
                    try {
                        UserService.UserIDCheck(password, username, passwordCheck, found);
                    } catch (IllegalArgumentException e) {
                        message = e.getMessage();
                    }

                    // then
                    assertEquals("ID는 알파벳 대문자 소문자와 숫자만 입력 가능합니다", message);
                }
            }

            @Nested
            @DisplayName("PASSWORD")
            class Image {
                @Test
                @DisplayName("3자리 이상")
                void fail1() {
                    // given
                    password = "12";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            passwordCheck,
                            email
                    );

                    // when
                    Optional<User> found = Optional.empty();
                    String message = null;
                    try {
                        UserService.UserIDCheck(password, username, passwordCheck, found);
                    } catch (IllegalArgumentException e) {
                        message = e.getMessage();
                    }

                    // then
                    assertEquals("비밀번호는 4글자 이상이어야합니다", message);
                }

                @Test
                @DisplayName("확인 비먼이 다름")
                void fail2() {
                    // given
                    password = "1233";
                    passwordCheck = "12333";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            passwordCheck,
                            email
                    );

                    // when
                    Optional<User> found = Optional.empty();
                    String message = null;
                    try {
                        UserService.UserIDCheck(password, username, passwordCheck, found);
                    } catch (IllegalArgumentException e) {
                        message = e.getMessage();
                    }

                    // then
                    assertEquals("비밀번호가 동일하지 않습니다.", message);
                }
            }
        }
    }
}
