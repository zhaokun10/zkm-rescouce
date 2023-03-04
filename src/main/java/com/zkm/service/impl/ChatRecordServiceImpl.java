package com.zkm.service.impl;

import com.zkm.mapper.ChatRecordMapper;
import com.zkm.model.ChatRecord;
import com.zkm.model.ReadedModel;
import com.zkm.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatRecordServiceImpl implements ChatRecordService {
    @Autowired
    ChatRecordMapper chatRecordMapper;

    @Override
    public List<ChatRecord> findChatRecordByUserIdAndFriendId(Integer userId, Integer friendId) {
        return chatRecordMapper.findChatRecordByUserIdAndFriendId(userId, friendId);
    }

    @Override
    public int insert(ChatRecord chatRecord) {
        return chatRecordMapper.insert(chatRecord);
    }

    @Override
    public void lastIntoChatWindow(ReadedModel readedModel) {
        ReadedModel readedModel_One = chatRecordMapper.selectOneReadedRecordByUserIdAndFriendIdAndType(readedModel);
        if (Objects.nonNull(readedModel_One)) {
            chatRecordMapper.updateLastIntoChatWindowTime(readedModel);
        } else {
            chatRecordMapper.lastIntoChatWindow(readedModel);
        }
        ReadedModel readedModel_Two = chatRecordMapper.selectOneReadedRecordByUserIdAndFriendIdAndType(readedModel);
        if (readedModel_Two.getType() == 0) {
            chatRecordMapper.updateChatRecordReaded(readedModel_Two.getFriendId(), readedModel_Two.getUserId());
        } else {
            chatRecordMapper.updateChatRecordReaded(readedModel_Two.getFriendId(), readedModel_Two.getUserId());
        }

    }

    @Override
    public ReadedModel findFriendLastIntoChatWindowTime(Integer userId, Integer friendId) {
        return chatRecordMapper.findFriendLastIntoChatWindowTime(userId, friendId);
    }


}
