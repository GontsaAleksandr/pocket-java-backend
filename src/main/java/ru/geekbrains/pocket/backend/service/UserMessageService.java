package ru.geekbrains.pocket.backend.service;

import org.bson.types.ObjectId;
import ru.geekbrains.pocket.backend.domain.User;
import ru.geekbrains.pocket.backend.domain.UserMessage;

import java.util.List;

public interface UserMessageService {

    UserMessage createMessage(UserMessage userMessage);

    UserMessage createMessage(User sender, User recipient, String text);

    void deleteMessage(UserMessage userMessage);

    UserMessage getMessage(ObjectId id);

    List<UserMessage> getMessagesBySender(User sender);

    List<UserMessage> getMessagesByRecipient(User recipient);

    List<UserMessage> getUnreadMessagesFromUser(User sender);

    List<UserMessage> getUnreadMessagesToUser(User recipient);

    UserMessage sendMessageFromTo(User sender, User recipient, String message);

    UserMessage updateMessage(UserMessage userMessage);

}
