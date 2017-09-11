package mark.rpg.GameObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mark.rpg.R;
import mark.rpg.Spells.Spell;

/**
 * Created by Марк on 05.04.2017.
 */

public class Hero extends Person implements Parcelable,Serializable {
    int experience;
    double maxMana;
    double mana;
    double critChance=0.1;
    ArrayList<Spell> listOfSpells;


    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getMana() {

        return mana;
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {

        if(health<0)
            this.health = 0;
        else this.health=health;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getCritChance() {
        return critChance;
    }

    public ArrayList<Spell> getListOfSpells() {
        return listOfSpells;
    }

    public void setListOfSpells(ArrayList<Spell> listOfSpells) {
        this.listOfSpells = listOfSpells;
    }

    public void setEmptyListOfSpells(){
        this.listOfSpells.clear();
    }

    public Hero(int lvl, int maxhealth, double mana, int minAttack, int maxAttack, double critChance) {
        super(lvl,maxhealth,minAttack,maxAttack);
        this.maxMana=mana;
        this.mana=maxMana;
        this.critChance = critChance;
        personImageId= R.drawable.batman48;
        this.name="UserName";

    }
    @Override
    public int hit(Person person){

        if(person.isDodged(this)) {
            return 0;
        }
        Random rnd=new Random();
        double plusDamage=Math.round((maxAttack-minAttack)*(rnd.nextDouble()));
        int damage=minAttack+(int)plusDamage;
        if (rnd.nextDouble()<=critChance) damage=(int)Math.round(damage*1.5);
        person.setHealth(person.getHealth()-damage);
        return damage;

    }

    @Override
    public int hitWithLog(Person person, TextView tv){

        if(person.isDodged(this)) {
            tv.append("\n"+person.getName()+" увернулся!");
            return 0;
        }
        Random rnd=new Random();
        double plusDamage=Math.round((maxAttack-minAttack)*(rnd.nextDouble()));
        int damage=minAttack+(int)plusDamage;
        if (rnd.nextDouble()<=critChance) damage=(int)Math.round(damage*1.5);
        person.setHealth(person.getHealth()-damage);
        tv.append("\n"+getName()+" наносит "+Integer.toString(damage)+" урона");
        return damage;

    }





    public void manaRegen(){
        if(this.getMana()+this.getMaxMana()*0.05>this.getMaxMana()) this.setMana(maxMana);
        else mana+=this.getMaxMana()*0.05;
    }




    // PARSING

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Person's
        dest.writeInt(level);
        dest.writeInt(maxHealth);
        //dest.writeInt(health);
        dest.writeInt(minAttack);
        dest.writeInt(maxAttack);

        dest.writeDouble(dodgeChance);
        dest.writeDouble(resist);

        dest.writeList(listOfEffects);
        dest.writeInt(personImageId);
        dest.writeString(name);

        //Hero's

        dest.writeInt(experience);
        dest.writeDouble(maxMana);
        dest.writeDouble(mana);
        dest.writeDouble(critChance);
        dest.writeList(listOfSpells);





    }
    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        // распаковываем объект из Parcel
        public Hero createFromParcel(Parcel in) {
           // Log.d(LOG_TAG, "createFromParcel");
            return new Hero(in);
        }

        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private Hero(Parcel parcel) {
        //Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        super(parcel.readInt(),parcel.readInt(),parcel.readInt(),parcel.readInt());
        health=maxHealth;
        dodgeChance = parcel.readDouble();
        resist = parcel.readDouble();


        parcel.readList(listOfEffects,List.class.getClassLoader());
        personImageId = parcel.readInt();
        name=parcel.readString();

        experience=parcel.readInt();
        maxMana=parcel.readDouble();
        mana=parcel.readDouble();
        critChance=parcel.readDouble();
        parcel.readList(listOfSpells,List.class.getClassLoader());



    }


    // Hero(int h,int minA,int maxA,double critChance)
}
