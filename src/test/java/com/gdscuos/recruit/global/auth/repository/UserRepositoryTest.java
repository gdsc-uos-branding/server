package com.gdscuos.recruit.global.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.global.common.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Email로 User를 조회할 수 있다.")
    void getUserByUserEmail() {
        //given
        String userEmail = "test@gmail.com";
        //when
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);
        //then
        assertThat(user.getUsername()).isEqualTo("TEST");
    }
}
