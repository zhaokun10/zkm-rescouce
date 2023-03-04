package com.zkm.service.impl;

import com.zkm.mapper.AddFriendNotificationMapper;
import com.zkm.mapper.FriendMapper;
import com.zkm.model.FriendNotificationModel;
import com.zkm.model.UserZkm;
import com.zkm.service.AddFriendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddFriendNotificationServiceImpl implements AddFriendNotificationService {

    @Autowired
    AddFriendNotificationMapper addFriendNotificationMapper;

    @Autowired
    FriendMapper friendMapper;

    /**
     * 得到所有id添加好友通知朋友
     *
     * @param friendId 朋友id
     * @return {@link List}<{@link FriendNotificationModel}>
     */
    @Override
    public List<UserZkm> getAllAddFriendNotificationByFriendId(Integer friendId) {
        return addFriendNotificationMapper.getAllAddFriendNotificationByFriendId(friendId);
    }

    @Override
    public void insertAddFriendRequest(FriendNotificationModel friendNotificationModel) {
        addFriendNotificationMapper.insertAddFriendRequest(friendNotificationModel);
    }

    @Override
    public void insertAddGroupRequest(FriendNotificationModel friendNotificationModel) {
        addFriendNotificationMapper.insertAddGroupRequest(friendNotificationModel);
    }

    @Override
    public void agreeFriendRequest(Integer userId, Integer friendId) {
        addFriendNotificationMapper.agreeFriendRequest(userId, friendId);
        friendMapper.insert(userId, friendId);
        friendMapper.insert(friendId, userId);
    }

    @Override
    public void disagreeFriendRequest(Integer userId, Integer friendId) {
        addFriendNotificationMapper.disagreeFriendRequest(userId, friendId);
    }
}
