package ru.geekbrains.pocket.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.pocket.backend.domain.Role;
import ru.geekbrains.pocket.backend.exception.RoleNotFoundException;
import ru.geekbrains.pocket.backend.repository.RoleRepository;
import ru.geekbrains.pocket.backend.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String rolename) {
        return Optional.of(roleRepository.findByName(rolename).orElseThrow(
                () -> new RoleNotFoundException("User with username = " + rolename + " not found"))).get();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
