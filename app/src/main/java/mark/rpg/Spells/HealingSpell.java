package mark.rpg.Spells;

import android.widget.TextView;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;
import mark.rpg.R;

/**
 * Created by Марк on 08.04.2017.
 */

public class HealingSpell extends Spell  {
    public HealingSpell(){
        super(20.0, R.drawable.health);
    }
    public HealingSpell(double mana, String im) {
        super(mana, R.drawable.health);
    }


    //ТуДу : Зависимость от статов
    public int heal(Hero hero){
        if (hero.getMana()<20) return 0;
        double healingPower=50;
        hero.setMana(hero.getMana()-baseManacost);
        if(hero.getHealth()+healingPower<hero.getMaxHealth())
        hero.setHealth(hero.getHealth()+(int)Math.round(healingPower));
        else hero.setHealth(hero.getMaxHealth());
        return (int)Math.round(healingPower);
    }

    @Override
    public void effectWithLog(Hero hero, Enemy enemy, TextView log){
        if (hero.getMana()<this.getBaseManacost())
        {
            log.append("\nнедостаточно маны");
            return;
        }
        //TODO зависимость от статов
        double healingPower=50;
        hero.setMana(hero.getMana()-baseManacost);

        if(hero.getHealth()+healingPower<hero.getMaxHealth())
            hero.setHealth(hero.getHealth()+(int)Math.round(healingPower));
        else hero.setHealth(hero.getMaxHealth());
        log.append("\nВылечено "+Double.toString(healingPower)+"здоровья");



    }

}
