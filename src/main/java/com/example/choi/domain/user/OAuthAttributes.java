package com.example.choi.domain.user;



import lombok.Builder;
import lombok.Getter;
import java.util.Map;



@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }// of() : OAuth2User에서 반환하는 사용자정보는 Map이라서 값을 하나하나 변환해야 한다

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        //네이버일때
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }


       // 카카오일때
        if("kakao".equals(registrationId)){
            return ofKakao("id", attributes);
        }


        // 구글일때
        return ofGoole(userNameAttributeName, attributes);
    }


    // 구글
    private static OAuthAttributes ofGoole(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)

        .nameAttributeKey(userNameAttributeName)
        .build();
    }

    //카카오
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    //네이버
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        // JSON형태이기 떄문에 Map을 통해서 데이터를 가져온다.
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


// 첫 가입 시점에 User 엔티티를 생성
    public User toEntity() {
        return User.builder()

        .name(name)
        .email(email)
        .picture(picture)
        .role(Role.USER)
        //가입할때 기본원한을 GUEST
        .build();
    }
}
