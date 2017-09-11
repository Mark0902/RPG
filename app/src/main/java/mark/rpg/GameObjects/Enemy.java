package mark.rpg.GameObjects;

import mark.rpg.Inventory;
import mark.rpg.Item;
import mark.rpg.R;

/**
 * Created by Марк on 12.04.2017.
 */

//implents Parselable

public class Enemy extends Person {
    double stamina;
    //Шанс уклониться
    double dodgeChance=0.1;


    public static final Enemy[] weakEnemys={new Enemy(1,100,2,7),new Enemy(1,110,3,6),new Enemy(1,120,1,9),new Enemy(1,105,4,8)};
    public static final Enemy[] middleEnemys={new Enemy(1,250,2,7),new Enemy(1,310,6,12),new Enemy(1,420,1,15),new Enemy(1,335,8,14)};
    public static final Enemy[] strongEnemys={new Enemy(1,700,10,30),new Enemy(1,825,10,21),new Enemy(1,600,20,33),new Enemy(1,777,13,19)};



    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    */

    public Enemy(int lvl,int maxHealth, int minAttack, int maxAttack) {
        super(lvl,maxHealth,minAttack,maxAttack);
        personImageId= R.drawable.enemy48;

        health=maxHealth;
    }

    public Enemy(Enemy clone){
        super(clone.getLevel(),clone.getMaxHealth(),clone.getMinAttack(),clone.getMaxAttack());
        personImageId=clone.getPersonImageId();
        health=clone.getHealth();
    }

    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    public double getStamina() {
        return stamina;
    }

    public void getReward(Inventory heroInventory){
        int gold=3;
        Item newItem=new Item();
        heroInventory.setGold(heroInventory.getGold()+gold);
    }






    /*
    // PARSING
    public Enemy(Parcel in) {
        int[] data = new int[3];
        in.readIntArray(data);
        maxHealth = data[0];
        minAttack= data[1];
        maxAttack = data[2];

    }


    public static final Parcelable.Creator<Enemy> CREATOR = new Parcelable.Creator<Enemy>() {

        @Override
        public Enemy createFromParcel(Parcel source) {
            return new Enemy(source);
        }

        @Override
        public Enemy[] newArray(int size) {
            return new Enemy[size];
        }
    };
    */


}
