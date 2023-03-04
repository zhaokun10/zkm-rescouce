package com.zkm.mapper;

import com.zkm.model.GroupGroup;

public interface GroupGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupGroup record);

    int insertSelective(GroupGroup record);

    GroupGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupGroup record);

    int updateByPrimaryKey(GroupGroup record);
}