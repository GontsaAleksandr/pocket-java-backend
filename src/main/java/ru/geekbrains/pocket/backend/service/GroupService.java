package ru.geekbrains.pocket.backend.service;

import org.bson.types.ObjectId;
import ru.geekbrains.pocket.backend.domain.Group;
import ru.geekbrains.pocket.backend.domain.Project;
import ru.geekbrains.pocket.backend.domain.User;

import java.util.List;

public interface GroupService {

    Group createGroup(Group group);

    void deleteGroup(Group group);

    Group getGroup(ObjectId id);

    List<Group> getGroups(User creator);

    List<Group> getGroups(String name);

    List<Group> getOpenedGroups();

    List<Group> getClosedGroups();

    String setGroupToClose();

    String setGroupToOpen();

    Group updateGroup(Group group);

}
