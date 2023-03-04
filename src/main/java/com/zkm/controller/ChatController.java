package com.zkm.controller;

import com.zkm.common.result.JsonResult;
import com.zkm.common.result.ResultTool;
import com.zkm.model.ReadedModel;
import com.zkm.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    @Autowired
    ChatRecordService chatRecordService;

    @GetMapping("/all/chatRecord/{userId}/{friendId}")
    JsonResult findChatRecordByUserIdAndFriendId(@PathVariable Integer userId, @PathVariable Integer friendId) {
        return ResultTool.success(chatRecordService.findChatRecordByUserIdAndFriendId(userId, friendId));
    }

    @PostMapping("/insert/lastReadTime")
    JsonResult lastIntoChatWindow(@RequestBody ReadedModel readedModel){
        chatRecordService.lastIntoChatWindow(readedModel);
        return ResultTool.success();
    }


}
