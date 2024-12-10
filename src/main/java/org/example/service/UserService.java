package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.UserDetail;
import org.example.domain.UserRequest;
import org.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService{
    @Autowired
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Long save(UserRequest dto) {
        System.out.println(dto.toString());
        UserDetail userDetail = UserDetail.builder()
                                .username(dto.getUsername())
                                .password(bCryptPasswordEncoder.encode(dto.getPassword())).build();
        Long result = userMapper.save(userDetail);
        System.out.println("result ; "+result);
        return result;

    }
}
