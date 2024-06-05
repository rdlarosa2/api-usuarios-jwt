package com.rdelarosa.jwt.api.service;

import java.util.List;

import com.rdelarosa.jwt.api.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void deactivateById(Integer id);
    List<UserVO> findAll();
    UserVO getUserById(Integer userId);
    UserVO getUserByUsername(String username);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;

    UserVO save(UserVO theUser);
    UserVO update(UserVO theUser);
    void updateLastLogin(String username, String token);
}
