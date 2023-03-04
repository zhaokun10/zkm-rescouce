package com.zkm.mapper;

import com.zkm.model.ChatRecord;
import com.zkm.model.ReadedModel;

import java.util.List;

public interface ChatRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatRecord record);

    int insertSelective(ChatRecord record);

    ChatRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatRecord record);

    int updateByPrimaryKey(ChatRecord record);

    List<ChatRecord> findChatRecordByUserIdAndFriendId(Integer userId, Integer friendId);

    void lastIntoChatWindow(ReadedModel readedModel);

    ReadedModel findFriendLastIntoChatWindowTime(Integer userId, Integer friendId);

    void updateLastIntoChatWindowTime(ReadedModel readedModel);

    ReadedModel selectOneReadedRecordByUserIdAndFriendIdAndType(ReadedModel readedModel);

    void updateChatRecordReaded(Integer userId, Integer friendId);


}
