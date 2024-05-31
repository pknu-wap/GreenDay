package com.example.dto;
import lombok.AllArgsConstructor;
import java.util.Map;

@AllArgsConstructor
public class NaverUserDetails implements OAuth2UserInfo {

    private Map<String, Object> attributes;
    // OAuth2 공급자를 나타내는 문자열을 반환함
    @Override
    public String getProvider() {
        return "naver";
    }
    // OAuth2 공급자에서 제공하는 사용자의 고유 ID를 반환함
    @Override
    public String getProviderId() {
        // 소셜 로그인에서 받아온 사용자 정보에서 ID를 추출하여 반환함
        return (String) ((Map) attributes.get("response")).get("id");
    }
    // OAuth2 공급자에서 제공하는 사용자의 이메일을 반환함
    @Override
    public String getEmail() {
        // 소셜 로그인에서 받아온 사용자 정보에서 이메일을 추출하여 반환함
        return (String) ((Map) attributes.get("response")).get("email");
    }
    // OAuth2 공급자에서 제공하는 사용자의 이름을 반환함
    @Override
    public String getName() {
        // 소셜 로그인에서 받아온 사용자 정보에서 이름을 추출하여 반환함
        return (String) ((Map) attributes.get("response")).get("name");
    }
}
