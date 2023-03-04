package com.zkm.service.impl;

import com.zkm.mapper.FriendMapper;
import com.zkm.model.UserZkm;
import com.zkm.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Override
    public List<UserZkm> getAllFriendByUserId(Integer userId) {
        List<UserZkm> userZkmList = friendMapper.getAllFriendByUserId(userId);
        return userZkmList;
    }
}
