package mark.rpg.Spells;

import android.widget.TextView;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;
import mark.rpg.R;

/**
 * Created by Марк on 08.04.2017.
 */

public class FireBall extends Spell {
    public FireBall() {
        super(15, R.drawable.fireball);
    }
    //ТуДу : От статов зависимость
   public int damage(Hero hero, Enemy enemy){
       if(hero.getMana()<15) return 0;
       hero.setMana(hero.getMana()-baseManacost);
       int damage= 50;
       enemy.setHealth(enemy.getHealth()-damage);
       return damage;
   }
    @Override
    public void effectWithLog(Hero hero,Enemy enemy,TextView log){
        if(hero.getMana()<this.getBaseManacost())
        {
            log.append("\nнедостаточно маны");
            return;
        }
        hero.setMana(hero.getMana()-baseManacost);
        int damage= 50;
        enemy.setHealth(enemy.getHealth()-damage);
        log.append("\nНанесено "+Integer.toString(damage)+"урона");

    }

}
