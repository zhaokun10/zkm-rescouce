package com.zkm.service.impl;

import com.zkm.model.UserZkm;
import com.zkm.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public List<UserZkm> findGroupUserByGroupId(Integer groupId) {
        return null;
    }
}
