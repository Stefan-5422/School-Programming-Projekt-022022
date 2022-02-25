package com.yes.yes.utils;

public class RegistryEntry {
    String name;
    String displayName;
    Class<?> entity;

    public RegistryEntry(String name, String displayName, Class<?> entity) {
        this.name = name;
        this.displayName = displayName;
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<?> getEntity() {
        return entity;
    }
}
