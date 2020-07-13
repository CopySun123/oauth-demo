package com.copysun.microoauth2auth.service;

import com.copysun.microoauth2auth.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author copysun
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private List<UserDto> users;

    @PostConstruct
    public  void initUser(){
        users=new ArrayList<>();
        UserDto userDto1=new UserDto("sun",new BCryptPasswordEncoder().encode("admin"),AuthorityUtils.createAuthorityList("ADMIN"));
        userDto1.setId("16820003");
        UserDto userDto2=new UserDto("copysun",new BCryptPasswordEncoder().encode("test"),AuthorityUtils.createAuthorityList("TEST"));
        userDto2.setId("16820004");
        users.add(userDto1);
        users.add(userDto2);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        List<UserDto> userDtos=users.stream().filter(user->user.getUsername().equals(s)).collect(Collectors.toList());
        if(ObjectUtils.isEmpty(userDtos)){
            throw new UsernameNotFoundException("用户名错误或不存在!");
        }
        return userDtos.get(0);
    }
}
