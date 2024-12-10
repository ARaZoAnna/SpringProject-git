package org.example.repository;

import lombok.Getter;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.UserDetail;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public Optional<UserDetail> findByEmail(String username);
    public Long save(UserDetail userDetail);
}
