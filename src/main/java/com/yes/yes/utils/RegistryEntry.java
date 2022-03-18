package com.yes.yes.utils;

public record RegistryEntry(String name, String displayName, Class<?> entity) {

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
