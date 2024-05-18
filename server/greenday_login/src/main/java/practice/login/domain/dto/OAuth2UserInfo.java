package practice.login.domain.dto;

public interface OAuth2UserInfo {
    // 사용자 정보를 제공하는 OAuth2 공급자(Provider)의 이름을 반환하는 메서드
    String getProvider();

    // 사용자 정보를 제공하는 OAuth2 공급자에서 사용자의 고유 ID를 반환하는 메서드history
    String getProviderId();

    // 사용자 정보를 제공하는 OAuth2 공급자에서 사용자의 이메일을 반환하는 메서드
    String getEmail();

    // 사용자 정보를 제공하는 OAuth2 공급자에서 사용자의 이름을 반환하는 메서드
    String getName();
}
