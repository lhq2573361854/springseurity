package com.tianling.springsecurity.authentication.components;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author TianLing
 * Date 2020/6/4 21:54
 * Description
 */
@Component
@Slf4j
public class MyUserDetailsService  implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("用户名是："+s);
        String encode = passwordEncoder.encode("123456"); // 查出来的密码
        log.info("密码是："+encode);
        return new User(s, encode,
                true, true,
                true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        log.info("用户名是："+s);
        String encode = passwordEncoder.encode("123456"); // 查出来的密码
        log.info("密码是："+encode);
        return new SocialUser(s, encode,
                true, true,
                true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}
