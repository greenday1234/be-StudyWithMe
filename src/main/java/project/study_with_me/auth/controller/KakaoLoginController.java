package project.study_with_me.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.auth.dto.request.KakaoLoginReissueDto;
import project.study_with_me.auth.dto.request.KakaoLoginRequestDto;
import project.study_with_me.auth.dto.response.KakaoLoginResponseDto;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.auth.service.KakaoLoginService;

@Tag(name = "Auth", description = "Login")
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @Operation(summary = "Kakao OAuth Login(회원 가입 및 로그인)")
    @RequestMapping("/kakao/login")
    public ResponseEntity<KakaoLoginResponseDto> loginKakao(@RequestBody KakaoLoginRequestDto kakaoLoginRequestDto) {
        return ResponseEntity.ok(kakaoLoginService.getAccessTokenFromKakao(kakaoLoginRequestDto.getCode()));
    }

    @Operation(summary = "Kakao OAuth Logout")
    @PostMapping("/kakao/logout")
    public ResponseEntity<Boolean> logoutKakao() {
        return ResponseEntity.ok(kakaoLoginService.kakaoOAuthLogout(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "Kakao (토큰 재발급)", description = "firstJoin(true=회원가입, false=재로그인)")
    @PostMapping("/kakao/reissue")
    public ResponseEntity<KakaoLoginResponseDto> reissue(@RequestBody KakaoLoginReissueDto kakaoLoginReissueDto) {
        return ResponseEntity.ok(kakaoLoginService.reissue(kakaoLoginReissueDto));
    }

    @Operation(summary = "로그인 확인")
    @GetMapping("/login/state")
    public ResponseEntity<Boolean> getLoginState() {
        return ResponseEntity.ok(kakaoLoginService.getLoginState(SecurityUtil.getCurrentMemberId()));
    }
}
