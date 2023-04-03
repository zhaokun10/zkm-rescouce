package com.zkm.service;

import com.zkm.model.UserZkm;

import java.util.List;

public interface UserService {
    int registerUser(UserZkm userZkm);

    UserZkm findAllUserInfoByUsername(String username);
    UserZkm findAllInfoByUsername(String username);

    int logout(String username) throws Exception;

    List<UserZkm> findAllUserByUsername(String username);

    UserZkm findUserInfoByUserId(Integer userId);

    int updateUserInfo(UserZkm userZkm);
}
