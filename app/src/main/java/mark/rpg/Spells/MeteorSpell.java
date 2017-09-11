package mark.rpg.Spells;

import android.widget.TextView;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;
import mark.rpg.R;

/**
 * Created by Марк on 21.04.2017.
 */

public class MeteorSpell extends Spell {
    public MeteorSpell(){
        super(50, R.drawable.meteor32);
    }
    public void effectWithLog(Hero hero, Enemy enemy, TextView log){
        if(hero.getMana()<this.getBaseManacost())
        {
            log.append("\nнедостаточно маны");
            return;
        }
        hero.setMana(hero.getMana()-baseManacost);
        int damage= 70;
        enemy.setHealth(enemy.getHealth()-damage);
        log.append("\nНанесено "+Integer.toString(damage)+"урона");

    }
}
