package com.yes.yes.utils;

import com.yes.yes.utils.exceptions.AlreadyExistsException;

import java.util.HashMap;

public abstract class EntityRegistry {
    static HashMap<String, RegistryEntry> entities = new HashMap<>();

    public static void register(RegistryEntry entry) throws AlreadyExistsException {
        if (entities.containsKey(entry.getName())) {
            throw new AlreadyExistsException("Name already registered");
        } else {
            entities.put(entry.getName(), entry);
        }
    }

    public static RegistryEntry getEntity(String name) {
        return entities.get(name);
    }

}

