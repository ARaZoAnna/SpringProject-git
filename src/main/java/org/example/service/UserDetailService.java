package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.UserRequest;
import org.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.domain.UserDetail;
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService{
    //스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스

    @Autowired
    private final UserMapper userMapper;


//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserDetails userDetails = userMapper.findByEmail(username);
//
//        if (userDetails == null) {
//            throw new UsernameNotFoundException("유효하지 않는 로그인 정보입니다.");
//        }
//
//        return userDetails;
//
//
//    //사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
////    @Override
////    public User loadUserByUsername(String username){
////        return userMapper.findByEmail(username)
////                .orElseThrow(()->new IllegalArgumentException(username));
////    }
//
//    }
    @Override
    public UserDetail loadUserByUsername(String email){
        return userMapper.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException(email));
    }
}
