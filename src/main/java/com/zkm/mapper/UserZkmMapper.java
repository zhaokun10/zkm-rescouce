package com.zkm.mapper;

import com.zkm.model.UserZkm;

import java.util.List;

public interface UserZkmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserZkm record);

    int insertSelective(UserZkm record);

    UserZkm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserZkm record);

    int updateByPrimaryKey(UserZkm record);

    UserZkm findOneByUsernameUserZkm(String username);

    UserZkm findAllInfoByUsername(String username);

    UserZkm findOneByUserEmail(String email);

    int updateOnlineInt(UserZkm userZkm);

    List<UserZkm> findAllUserByUsername(String username);
}
