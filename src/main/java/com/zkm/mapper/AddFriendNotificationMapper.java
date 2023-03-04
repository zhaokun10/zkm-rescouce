package com.zkm.mapper;

import com.zkm.model.FriendNotificationModel;
import com.zkm.model.UserZkm;

import java.util.List;

public interface AddFriendNotificationMapper {

  List<UserZkm> getAllAddFriendNotificationByFriendId(Integer friendId);

   void insertAddFriendRequest(FriendNotificationModel friendNotificationModel);
   void insertAddGroupRequest(FriendNotificationModel friendNotificationModel);
   void agreeFriendRequest(Integer userId , Integer friendId);
   void disagreeFriendRequest(Integer userId , Integer friendId);
}
