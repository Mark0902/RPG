package mark.rpg.Spells;

import android.widget.TextView;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;

/**
 * Created by Марк on 06.07.2017.
 */

public abstract  class Spell{
    double baseManacost;
    int imageId;

    public void setBaseManacost(double baseManacost) {
        this.baseManacost = baseManacost;
    }

    public void setImage(int imageId) {
        this.imageId = imageId;
    }

    public double getBaseManacost() {

        return baseManacost;
    }

    Spell(double mana, int im){
        baseManacost=mana;
        imageId=im;
    }

    public int getImageId() {
        return imageId;
    }

    public void effectWithLog(Hero hero, Enemy enemy, TextView log){}
}

