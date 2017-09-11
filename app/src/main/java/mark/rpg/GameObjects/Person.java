package mark.rpg.GameObjects;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mark.rpg.Effects.Effect;
import mark.rpg.R;

/**
 * Created by Марк on 14.04.2017.
 */

public abstract class Person implements Serializable {
    //Base stats
    int level;
    int maxHealth;
    int health;
    int minAttack;
    int maxAttack;

    // Эффекты,увоторы
    double dodgeChance=0.1;
    double resist=0.1;
    ArrayList<Effect> listOfEffects;

    //Графика / информация
    int personImageId;
    String name="???";




    Person(int lvl,int maxH, int minA, int maxA){
        level=lvl;
        this.maxHealth=maxH;
        this.minAttack=minA;
        this.maxAttack=maxA;
        health=maxH;

        personImageId= R.drawable.sword_small;

    }

    //Getters & Setters
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {

        return level;
    }

    public void setHealth(int health) {
        if (health<0) this.health=0;
        else
        this.health = health;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setResist(double resist) {
        this.resist = resist;
    }

    public int getMaxHealth() {

        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getResist() {
        return resist;
    }

    public List<Effect> getListOfEffects() {
        return listOfEffects;
    }

    public void setListOfEffects(ArrayList<Effect> listOfEffects) {
        this.listOfEffects = listOfEffects;
    }

    public int getPersonImageId() {
        return personImageId;
    }

    public void setPersonImageId(int personImageId) {
        this.personImageId = personImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int hit(Person person){
        if(person.isDodged(this)) return 0;
        Random rnd=new Random();
        double plusDamage=Math.round((maxAttack-minAttack)*(rnd.nextDouble()));
        int damage=minAttack+(int)plusDamage;

        person.setHealth(person.getHealth()-damage);

        return damage;


    }

    public int hitWithLog(Person person, TextView tv){
        if(person.isDodged(this)) {
            tv.append("\n"+person.getName()+" увернулся!");
            return 0;
        }
        Random rnd=new Random();
        double plusDamage=Math.round((maxAttack-minAttack)*(rnd.nextDouble()));
        int damage=minAttack+(int)plusDamage;

        person.setHealth(person.getHealth()-damage);
        tv.append("\n"+getName()+" наносит "+Integer.toString(damage)+" урона");
        return damage;


    }

    public boolean isAlive(){
        if (getHealth()==0) return false;
        else return true;
    }

    public boolean isDodged(Person person){
        Random rnd=new Random();
        if(person.getLevel()>=level) {
            if(rnd.nextDouble()<dodgeChance) return true;
            else return false;
        }
        if(rnd.nextDouble()<dodgeChance*2) return true;
        else return false;
    }

    // my
    public void addEffect(Effect effect){
        listOfEffects.add(effect);
        
    }


    public void allEffectsTick(){
        for (Effect e:listOfEffects) {

            e.tick(this);
        }
    }


    public String printNameLevelInfo(){
        return name+" "+Integer.toString(getLevel())+"lvl";
    }

}
