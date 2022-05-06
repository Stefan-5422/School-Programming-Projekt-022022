package com.yes.yes.utils;

import javafx.scene.transform.Scale;

public class Item extends javafx.scene.Group implements Cloneable {
    final Part[][] parts = new Part[4][4];

    public void setPart(int layer, int section, Part part) throws IllegalArgumentException {
        if (layer < 0 || layer > 4 && section < 0 || section > 4) {
            throw new IllegalArgumentException("Argument out of range");
        }

        if (parts[layer][section] != null) {
            this.getChildren().remove(parts[layer][section]);
        }

        part.rotate(section);

        part.getTransforms().add(new Scale(-layer * 0.25f + 1f, -layer * 0.25f + 1f));

        parts[layer][section] = part;
        this.getChildren().add(part);
    }

    public Part getPart(int layer, int section) throws IllegalArgumentException {
        if (layer < 0 || layer > 4 && section < 0 || section > 4) {
            throw new IllegalArgumentException("Argument out of range");
        }
        return parts[layer][section];
    }

    public void rotate(int quarters) {
        quarters %= 4;

        for (int i = 0; i < quarters; i++) {
            for (int l = 0; l < 4; l++) {
                Part[] rot = new Part[4];

                rot[0] = parts[l][2];
                rot[1] = parts[l][0];
                rot[2] = parts[l][3];
                rot[3] = parts[l][1];

                parts[l] = rot;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                parts[i][j].rotate(quarters);
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
                    clone.setPart(i, j, this.getPart(i, j).getClass().getConstructor(types).newInstance());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return clone;
    }
}
