package com.para4digm.yumcdpl.client;


import com._4paradigm.prophet.keystone.model.UserEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ProphetClient {

    @Value("${sso.sage.tokenCookieName}")
    String tokenCookieName;

    @Getter
    @Value("${sso.sage.url}")
    String browserUrl;

    @Autowired
    KeyStoneClientFactory factory;

    public boolean ok(HttpServletRequest request) {
        val keystoneClient = factory.create();
        val cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        val cookie = getTokenCookie(cookies);
        if (cookie == null) {
            return false;
        }
        val token = cookie.getValue();
        log.info("logout token {}", token);
        try {
            keystoneClient.getCurrentUser(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void grant(HttpServletResponse response, String username) {
        val userToken = getToken(username);
        log.info("user token {}", userToken);
        val cookie = buildCookie(tokenCookieName, userToken);
        log.info("user cookie {}", cookie);
        response.addCookie(cookie);
    }

    Cookie getTokenCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (tokenCookieName.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    Cookie buildCookie(String name, String value) {
        val cookie = new Cookie(name, value);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setVersion(1);
        return cookie;
    }

    String getToken(@NonNull String username) {
        log.info("get {} token", username);
        val userEntity = new UserEntity();
        userEntity.setUsername(username);
        return factory.create()
                .authenticateSso(userEntity, true)
                .getToken();
    }

}
