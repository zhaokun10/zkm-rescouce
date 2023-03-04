package com.zkm.service.impl;

import com.zkm.mapper.UserZkmMapper;
import com.zkm.model.UserZkm;
import com.zkm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserZkmMapper userZkmMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserZkm user = userZkmMapper.findOneByUsernameUserZkm(username);
        if (Objects.nonNull(user)) {
            user.setOnline(1);
            userZkmMapper.updateOnlineInt(user);
            return user;
        } else {
            return null;
        }
    }



    @Override
    public int registerUser(UserZkm userZkm) {
        // 判断用户邮箱是否进行过注册
        UserZkm user = userZkmMapper.findOneByUserEmail(userZkm.getEmail());
        if(Objects.isNull(user)){
            return userZkmMapper.insert(userZkm);
        }else {
            return 0;
        }
    }

    @Override
    public UserZkm findAllUserInfoByUsername(String username) {
        return userZkmMapper.findOneByUsernameUserZkm(username);
    }

    @Override
    public UserZkm findAllInfoByUsername(String username) {
        return userZkmMapper.findAllInfoByUsername(username);
    }

    @Override
    public int logout(String username) throws Exception {
        UserZkm user = userZkmMapper.findOneByUsernameUserZkm(username);
        if (Objects.isNull(user)) {
           throw new Exception("请联系管理员");
        }
        user.setOnline(0);
        return userZkmMapper.updateOnlineInt(user);
    }

    @Override
    public List<UserZkm> findAllUserByUsername(String username) {
        return userZkmMapper.findAllUserByUsername(username);
    }
}
