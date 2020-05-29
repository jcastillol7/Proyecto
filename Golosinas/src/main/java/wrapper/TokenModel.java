/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

/**
 *
 * @author pjarrecis
 */
public class TokenModel {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public TokenModel() {
    }

    public TokenModel(String accessToken, String tokenType, String tokenRefresh, int expiresin, String scope) {
        this.access_token = accessToken;
        this.token_type = tokenType;
        this.refresh_token = tokenRefresh;
        this.expires_in = expiresin;
        this.scope = scope;
    }

    

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public String getTokenRefresh() {
        return refresh_token;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.refresh_token = tokenRefresh;
    }

    public int getExpiresin() {
        return expires_in;
    }

    public void setExpiresin(int expiresin) {
        this.expires_in = expiresin;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
    
}
