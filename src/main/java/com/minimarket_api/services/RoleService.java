package com.minimarket_api.services;

import com.minimarket_api.persistence.entities.RoleEntity;
import com.minimarket_api.persistence.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> findAll() {
        return this.roleRepository.findAll();
    }

    public List<RoleEntity> findRoleEntitiesByRoleIn(List<String> roleNames) {
        return this.roleRepository.findRoleEntitiesByRoleIn(roleNames);
    }

    public void save(RoleEntity role) {
        this.roleRepository.save(role);
    }

    public void saveAll(List<RoleEntity> roles) {
        this.roleRepository.saveAll(roles);
    }
}
