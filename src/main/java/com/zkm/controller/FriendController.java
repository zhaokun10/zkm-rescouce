package com.zkm.controller;

import com.zkm.common.result.JsonResult;
import com.zkm.common.result.ResultTool;
import com.zkm.model.FriendNotificationModel;
import com.zkm.service.AddFriendNotificationService;
import com.zkm.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    AddFriendNotificationService addFriendNotificationService;

    @GetMapping("/all")
    JsonResult getAllFriendByUserId(@RequestParam Integer userId) {
        return ResultTool.success(friendService.getAllFriendByUserId(userId));
    }

    @GetMapping("/all/add/friend/notification")
    JsonResult getAllAddFriendNotificationByFriendId(@RequestParam Integer friendId) {
        return ResultTool.success(addFriendNotificationService.getAllAddFriendNotificationByFriendId(friendId));
    }

    @PostMapping("/addFriendsRequest")
    JsonResult sendAddRequest(@RequestBody FriendNotificationModel friendNotificationModel){
        addFriendNotificationService.insertAddFriendRequest(friendNotificationModel);
        return ResultTool.success();
    }

    @PostMapping("/agreeFriendsRequest/{friendId}")
    JsonResult agreeFriendsRequest(@RequestParam Integer userId, @PathVariable Integer friendId){
        addFriendNotificationService.agreeFriendRequest(userId,friendId);
        return ResultTool.success();
    }
    @PostMapping("/disagreeFriendsRequest/{friendId}")
    JsonResult disagreeFriendsRequest(@RequestParam Integer userId, @PathVariable Integer friendId){
        addFriendNotificationService.disagreeFriendRequest(userId,friendId);
        return ResultTool.success();
    }
}
