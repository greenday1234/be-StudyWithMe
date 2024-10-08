package project.study_with_me.auth.jwt.utils;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class SecurityUtil {

    /**
     * DB 에 저장된 MemberId 와 SecurityContext 에 저장된 MemberId 값을 비교할 때 사용
     */
    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        // SecurityContext 에 authentication 이 없으면 anonymousUser 가 자동 등록됨
        if (authentication.getName().equals("anonymousUser")) {
            return null;
        }

        return Long.parseLong(authentication.getName());
    }
}
