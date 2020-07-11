package com.microblog.gate.auth.config;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Component
public class AccessReactiveAuthorizationManager<T> implements ReactiveAuthorizationManager<T> {

    private  List<String> authorities;

    private AccessReactiveAuthorizationManager(String... authorities) {
        this.authorities = Arrays.asList(authorities);
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, T object) {


        Mono result =   mono.filter((a) -> {
            boolean isAuthenticated = a.isAuthenticated();
            log.info("是否认证通过? {}",isAuthenticated);
            return isAuthenticated;

        }).flatMapIterable((a) -> {
            Jwt jwt = (Jwt) a.getCredentials();
            Map<String, Object> claims =  jwt.getClaims();
            JSONArray roleJSONArray = (JSONArray)claims.get("authorities");
            String roleStr = "";
            Collection<SimpleGrantedAuthority>  simpleGrantedAuthorities = new ArrayList<>();
            for(int i = 0; i< roleJSONArray.size(); i++){
                roleStr += roleJSONArray.get(i) + ",";
            }
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleStr);
            simpleGrantedAuthorities.add(grantedAuthority);

            return simpleGrantedAuthorities;
        }).map((g) -> {
            return g.getAuthority();
        }).any((roleStr) -> {
            String[]  roles = roleStr.split(",");
            for (String role:roles){
                if(authorities.contains(role)){
                    if (log.isDebugEnabled()){
                        if(role.startsWith("ROLE")){
                            log.info("拥有角色[{}]",role);
                        }
                        else {
                            log.info("拥有权限[{}]",role);
                        }
                    }

                    return true;
                }
            }
            log.debug("未拥有权限!!!");
            return false;//this.authorities.contains(a);
        }).map((hasAuthority) -> {
            return new AuthorizationDecision(hasAuthority);
        }).defaultIfEmpty(new AuthorizationDecision(false));

        return  result;
    }


    public static <T> AccessReactiveAuthorizationManager<T> hasAuthority(String authority) {
        Assert.notNull(authority, "authority cannot be null");
        return new AccessReactiveAuthorizationManager(new String[]{authority});
    }

    public static <T> AccessReactiveAuthorizationManager<T> hasAnyAuthority(String... authorities) {
        Assert.notNull(authorities, "authorities cannot be null");
        String[] var1 = authorities;
        int var2 = authorities.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String authority = var1[var3];
            Assert.notNull(authority, "authority cannot be null");
        }

        return new AccessReactiveAuthorizationManager(authorities);
    }

    public static <T> AccessReactiveAuthorizationManager<T> hasRole(String role) {
        Assert.notNull(role, "role cannot be null");
        return hasAuthority("ROLE_" + role);
    }

    public static <T> AccessReactiveAuthorizationManager<T> hasAnyRole(String... roles) {
        Assert.notNull(roles, "roles cannot be null");
        String[] var1 = roles;
        int var2 = roles.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String role = var1[var3];
            Assert.notNull(role, "role cannot be null");
        }

        return hasAnyAuthority(toNamedRolesArray(roles));
    }

    private static String[] toNamedRolesArray(String... roles) {
        String[] result = new String[roles.length];

        for(int i = 0; i < roles.length; ++i) {
            result[i] = "ROLE_" + roles[i];
        }

        return result;
    }
}






