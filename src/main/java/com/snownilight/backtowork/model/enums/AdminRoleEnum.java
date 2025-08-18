package com.snownilight.backtowork.model.enums;

public enum AdminRoleEnum {
    PENDING(1, "PENDING"),
    MANAGER(2, "MANAGER"),
    ADMIN(3, "ADMIN"),
    SUPER_ADMIN(4, "SUPER_ADMIN");

    private final Long id;
    private final String name;

    AdminRoleEnum(int id, String name) {
        this.id = (long) id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AdminRoleEnum fromId(Long id) {
        for (AdminRoleEnum role : AdminRoleEnum.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role ID: " + id);
    }

    public static AdminRoleEnum fromName(String name) {
        for (AdminRoleEnum role : AdminRoleEnum.values()) {
            if (role.getName().equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + name);
    }
}
