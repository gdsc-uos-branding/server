package com.gdscuos.recruit.domain.applicant.api;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntroductionApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("정상적인 Introduction Get 요청")
    public void introductionGetApiTest() {
        //given
        List<String> teamStringList = Arrays.asList("FRONTEND", "BACKEND", "DATA_ML", "DESIGN",
                "DATA_ML", "MOBILE");

        //when
        Collections.shuffle(teamStringList);

        //then
        ExtractableResponse<Response> response = get(
                "/api/introduction/" + teamStringList.get(0))
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Introduction 없는 Team에 대해 요청 시 예외 발생")
    public void introductionGetApiExceptionTest() {
        //given
        List<String> teamStringList = Arrays.asList("CORE", "COMMON", "LEAD");

        //when
        Collections.shuffle(teamStringList);

        //then
        ExtractableResponse<Response> response = get(
                "/api/introduction/" + teamStringList.get(0))
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(400);
    }


}
