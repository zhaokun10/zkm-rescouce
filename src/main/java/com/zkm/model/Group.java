package com.zkm.model;

public class Group {
    private Integer id;

    private Integer userId;

    private String groupName;

    private String avatar;

    private Integer groupGroupId;

    public Group(Integer id, Integer userId, String groupName, String avatar, Integer groupGroupId) {
        this.id = id;
        this.userId = userId;
        this.groupName = groupName;
        this.avatar = avatar;
        this.groupGroupId = groupGroupId;
    }

    public Group() {
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getGroupGroupId() {
        return groupGroupId;
    }

    public void setGroupGroupId(Integer groupGroupId) {
        this.groupGroupId = groupGroupId;
    }
}