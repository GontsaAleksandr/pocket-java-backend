package ru.geekbrains.pocket.backend.resource;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import ru.geekbrains.pocket.backend.controller.rest.UserRestController;
import ru.geekbrains.pocket.backend.domain.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//http://spring-projects.ru/guides/tutorials-bookmarks/
//ResourceSupport - тип Spring HATEOAS, он аккумулирует объекты Link, которые описывают ресурсы.
public class UserResources extends ResourceSupport {

    private final Resources<Resource<User>> users;

    public UserResources(List<User> users) {
        List<Resource<User>> userResources = users.stream()
                .map(user -> new Resource<>(user,
                        linkTo(methodOn(UserRestController.class).getUserByName2(user.getUsername())).withSelfRel(),
                        linkTo(methodOn(UserRestController.class).getAllUsers()).withRel("users")))
                .collect(Collectors.toList());

        this.users = new Resources<>(userResources,
                linkTo(methodOn(UserRestController.class).getAllUsers()).withSelfRel());

    }

    public Resources<Resource<User>> getUser() {
        return users;
    }
}