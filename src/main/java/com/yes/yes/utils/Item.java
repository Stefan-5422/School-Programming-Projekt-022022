package com.yes.yes.utils;

import javafx.scene.transform.Scale;

import java.util.Arrays;

public class Item extends javafx.scene.Group implements Cloneable {
    final Part[][] parts = new Part[4][4];


    public void setPart(int layer, int section, Part part) throws IllegalArgumentException {
        if (layer < 0 || layer > 4 && section < 0 || section > 4) {
            throw new IllegalArgumentException("Argument out of range");
        }

        if (parts[layer][section] != null) {
            this.getChildren().remove(parts[layer][section]);
        }

        parts[layer][section] = part;

        if (part == null) return;

        part.rotate(section);
        part.getTransforms().add(new Scale(-layer * 0.25f + 1f, -layer * 0.25f + 1f));


        this.getChildren().add(part);
    }

    public Part getPart(int layer, int section) throws IllegalArgumentException {
        if (layer < 0 || layer > 4 && section < 0 || section > 4) {
            throw new IllegalArgumentException("Argument out of range");
        }
        return parts[layer][section];
    }

    public void rotate(int quarters) {
        quarters = (quarters + 2) % 4;

        for (int i = 0; i < quarters; i++) {
            for (int l = 0; l < 4; l++) {
                Part[] rot = new Part[4];

                rot[0] = parts[l][1];
                rot[1] = parts[l][2];
                rot[2] = parts[l][3];
                rot[3] = parts[l][0];

                parts[l] = rot;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Part part = parts[i][j];
                if (part != null) {
                    part.rotate(quarters);
                }
            }
        }
    }


    @Override
    public Item clone() {
        Item clone = new Item();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Class<?>[] types = new Class<?>[0];

                try {
                    Part part = this.getPart(i, j);

                    if (part == null)
                        continue;

                    Part clonePart = part.getClass().getConstructor(types).newInstance();

                    clonePart.setColor(part.getColor());
                    clone.setPart(i, j, clonePart);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Arrays.deepEquals(parts, item.parts);
    }
}
