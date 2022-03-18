package com.yes.yes.utils;

import com.yes.yes.utils.exceptions.AlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class EntityRegistry {
    private static final HashMap<String, RegistryEntry> entities = new HashMap<>();

    public static void register(RegistryEntry entry) throws AlreadyExistsException {
        if (entities.containsKey(entry.getName())) {
            throw new AlreadyExistsException("Name already registered");
        } else {
            entities.put(entry.getName(), entry);
        }
    }

    public static RegistryEntry getEntry(String name) {
        return entities.get(name);
    }

    public static ArrayList<String> getKeys() {
        return entities.keySet().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}

