package com.zkm.mapper;

import com.zkm.model.UserZkm;

import java.util.List;

/**
 * Friend
 *
 * @author zhaokun
 * @date 2023/02/27
 */
public interface FriendMapper {

   List<UserZkm> getAllFriendByUserId(Integer userId);

    void insert(Integer userId, Integer friendId);
}
