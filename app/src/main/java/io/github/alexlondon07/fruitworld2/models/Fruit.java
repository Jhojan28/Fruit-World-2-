package io.github.alexlondon07.fruitworld2.models;

import java.io.Serializable;

/**
 * Created by alexlondon07 on 1/31/18.
 */

public class Fruit implements Serializable {

    private String name, description;
    private int image, icon;
    private int quantity;

    // Valores accesibles estáticamente
    public final int LIMIT_QUANTITY = 10;

    public Fruit() {
    }

    public Fruit(String name, String description, int image, int icon, int quantity) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.icon = icon;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Metódo para aumentar el contador de Cantidad de Cada Fruta
     * @param quantity
     */
    public void AddQuanity(int quantity){
        if(this.quantity < LIMIT_QUANTITY){
            this.quantity += quantity;
        }
    }

}
