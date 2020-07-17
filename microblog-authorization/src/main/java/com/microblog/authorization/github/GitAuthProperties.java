package com.microblog.authorization.github;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GitAuthProperties {

    private String clientId = "Iv1.d95f744fae8ffe21";
    private String clientSecret = "75870ada9f4882fa12d84271714d409c1aa66d3e";
    private String accessTokenUrl = "https://github.com/login/oauth/access_token";
    private String accessTokenRedirectUri = "http://localhost:8081/api/auth/callback";

    private String getUserInfoUrl = "https://api.github.com/user";


}
