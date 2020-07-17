package com.microblog.authorization.github;

import lombok.Data;

@Data
public class TokenData {

    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshTokenExpiresIn;
    private String scope;
    private String tokenType;


}
