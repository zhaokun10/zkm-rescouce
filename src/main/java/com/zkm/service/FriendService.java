package com.zkm.service;

import com.zkm.model.UserZkm;

import java.util.List;

public interface FriendService {
  List<UserZkm> getAllFriendByUserId(Integer userId);
}
