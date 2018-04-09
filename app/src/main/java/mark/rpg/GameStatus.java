package mark.rpg;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;


import mark.rpg.GameObjects.Hero;


/**
 * Created by Марк on 07.07.2017.
 */

public class GameStatus implements Serializable {
    Hero hero;
    Inventory inventory;
    Date timeofLastSave;

    public static int currentGameSaveIndex=1;


    //  Доступ к локациям,квестам
    // public


    public GameStatus(Hero hero, Inventory inventory) {
        this.hero = hero;
        this.inventory = inventory;
    }
    public GameStatus() {

    }

    public void writeSave(Context context)throws IOException{

        String FILENAME = "game";
        FileOutputStream fos = context.openFileOutput(FILENAME+ Integer.toString(currentGameSaveIndex)+".data", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        timeofLastSave=new Date();
        oos.writeObject(this);
        oos.close();

        fos.close();

        //Ошибка предусмотреть



    }
    public  GameStatus readSave(Context context,int index)throws IOException, ClassNotFoundException{
        String FILENAME = "game";
        File file = context.getFileStreamPath(FILENAME+ Integer.toString(index)+".data");
        if (!file.exists()) {
            return null;
        }
        FileInputStream fos = context.openFileInput(FILENAME+ Integer.toString(index)+".data");
        ObjectInputStream in =  new ObjectInputStream(fos);
        return (GameStatus)in.readObject();


    //Ошибка ??
    }

    public static boolean deleteSave(Context context,int index){

        String FILENAME = "game";
        File file = context.getFileStreamPath(FILENAME+ Integer.toString(index)+".data");
        if (!file.exists()) {
            return false;
        }
        try {
            file.delete();
        }
        catch (Exception ex){
            //
            return false;
        }
        return true;
    }
    //TODO разобраЦа как сделать не в миллисекунах и убрать костыль //LocalDateTime added in API 26
    public void timeHealing(Hero hero){


       // Date timeDif=new Date(0,0,0,0,0,0);
        //Date hours=new Date(0,0,0,24,0,0);


        //Костыль
        if (timeofLastSave==null) return;
        Date dNow=new Date();
        double minDiff;
        if(dNow.getMinutes()-timeofLastSave.getMinutes()>0) {
            minDiff = ((double) dNow.getMinutes() / 60) - ((double) timeofLastSave.getMinutes() / 60);
        }
        else minDiff=((double) dNow.getMinutes() / 60) - ((double) timeofLastSave.getMinutes() / 60);

        double hoursDiff=dNow.getHours()-timeofLastSave.getHours()+minDiff;

        //TODO расчет нормальный если разница сутки и более
        if (this.timeofLastSave.getDay() - dNow.getDay() != 0) {
                hero.setHealth(hero.getMaxHealth());
                hero.setMana(hero.getMaxMana());
                return;
        }


                /*timeDifference.setTimeInMillis(curTime.getTimeInMillis() - this.timeofLastSave.getTimeInMillis());
                if (timeDifference.getTimeInMillis() > 60 * 60 * 1000) {
                    hero.setHealth(hero.getMaxHealth());
                    hero.setMana(hero.getMaxMana());
                } else {
                */
                    double percent = hoursDiff;
                    int addHp = hero.getMaxHealth() * (int) percent;
                    double addMp = hero.getMaxMana() * percent;

                    if (hero.getHealth() + addHp > hero.getMaxHealth()) {
                        hero.setHealth(hero.getMaxHealth());
                        hero.setMana(hero.getMaxMana());
                    } else {
                        hero.setHealth(hero.getHealth() + addHp);
                        hero.setMana(hero.getMana() + addMp);
                    }

    }


    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }



}

