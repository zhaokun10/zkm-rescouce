package com.zkm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendNotificationModel {

    private Integer id;

    private Integer userId;

    private Integer friendId;

    private Integer groupId;

    private Integer isAgree;
}
