package mark.rpg;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Марк on 07.07.2017.
 */

public class Inventory implements Serializable{
    int gold;
    ArrayList<Item> items;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
