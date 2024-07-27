package com.minimarket_api.services;

import com.minimarket_api.persistence.entities.PermissionEntity;
import com.minimarket_api.persistence.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<PermissionEntity> findAll() {
        return this.permissionRepository.findAll();
    }

    public void saveAll(Set<PermissionEntity> permissions) {
        this.permissionRepository.saveAll(permissions);
    }
}
