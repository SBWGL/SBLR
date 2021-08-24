package springboot.rl.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String,Object> attributes;// oauth2User.getAttributes()

    public KakaoUserInfo(Map<String,Object> attributes) {
        this.attributes=attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAcount = (Map<String, Object>) attributes.get("kakao_account");
        return (String) kakaoAcount.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        return (String) properties.get("nickname");
    }
}
