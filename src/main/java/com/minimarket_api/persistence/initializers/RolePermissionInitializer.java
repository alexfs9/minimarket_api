package com.minimarket_api.persistence.initializers;

import com.minimarket_api.persistence.entities.PermissionEntity;
import com.minimarket_api.persistence.entities.RoleEntity;
import com.minimarket_api.persistence.enums.PermissionEnum;
import com.minimarket_api.persistence.enums.RoleEnum;
import com.minimarket_api.services.PermissionService;
import com.minimarket_api.services.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RolePermissionInitializer {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @PostConstruct
    public void initializeRolesPermissions() {
        if (this.roleService.findAll().isEmpty() && this.permissionService.findAll().isEmpty()) {
            Set<PermissionEnum> adminPermissionsEnum = EnumSet
                    .range(PermissionEnum.CREATE_PRODUCTS, PermissionEnum.READ_ACCOUNT_PURCHASES);
            Set<PermissionEnum> customerPermissionsEnum = EnumSet
                    .range(PermissionEnum.CREATE_SALES, PermissionEnum.UPDATE_MY_ACCOUNT);

            Set<PermissionEntity> adminPermissions = this.PermissionEnumToEntity(adminPermissionsEnum);
            Set<PermissionEntity> customerPermissions = this.PermissionEnumToEntity(customerPermissionsEnum);

            PermissionEntity sharedPermission = new PermissionEntity();
            sharedPermission.setPermission(PermissionEnum.READ_PRODUCTS);

            adminPermissions.add(sharedPermission);
            customerPermissions.add(sharedPermission);

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(RoleEnum.ADMIN);
            adminRole.setPermissions(adminPermissions);

            RoleEntity customerRole = new RoleEntity();
            customerRole.setRole(RoleEnum.CUSTOMER);
            customerRole.setPermissions(customerPermissions);

            this.roleService.saveAll(List.of(adminRole, customerRole));
        }
    }

    private Set<PermissionEntity> PermissionEnumToEntity(@NotBlank Set<PermissionEnum> permissionEnumSet) {
        return permissionEnumSet.stream()
                .map((permissionEnum -> {
                    PermissionEntity permission = new PermissionEntity();
                    permission.setPermission(permissionEnum);
                    return permission;
                }))
                .collect(Collectors.toSet());
    }
}
