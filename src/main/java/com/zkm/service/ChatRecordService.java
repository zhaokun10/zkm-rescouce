package com.zkm.service;

import com.zkm.model.ChatRecord;
import com.zkm.model.ReadedModel;

import java.util.List;

public interface ChatRecordService {

    List<ChatRecord> findChatRecordByUserIdAndFriendId(Integer userId, Integer friendId);

    int insert(ChatRecord chatRecord);

    void lastIntoChatWindow(ReadedModel readedModel);

    ReadedModel findFriendLastIntoChatWindowTime(Integer userId, Integer friendId);

}
