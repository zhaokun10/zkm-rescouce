package com.zkm.model;

public class GroupGroup {
    private Integer id;

    private Integer userId;

    private String groupGroupName;

    private Integer groupGroupType;

    public GroupGroup(Integer id, Integer userId, String groupGroupName, Integer groupGroupType) {
        this.id = id;
        this.userId = userId;
        this.groupGroupName = groupGroupName;
        this.groupGroupType = groupGroupType;
    }

    public GroupGroup() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupGroupName() {
        return groupGroupName;
    }

    public void setGroupGroupName(String groupGroupName) {
        this.groupGroupName = groupGroupName == null ? null : groupGroupName.trim();
    }

    public Integer getGroupGroupType() {
        return groupGroupType;
    }

    public void setGroupGroupType(Integer groupGroupType) {
        this.groupGroupType = groupGroupType;
    }
}