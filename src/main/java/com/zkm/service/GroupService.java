package com.zkm.service;

import com.zkm.model.UserZkm;

import java.util.List;

public interface GroupService {
    List<UserZkm> findGroupUserByGroupId(Integer groupId);
}
