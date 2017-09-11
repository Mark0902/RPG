package mark.rpg.Effects;

import mark.rpg.GameObjects.Person;

/**
 * Created by Марк on 14.04.2017.
 */

public class PoisonEffect extends Effect {
    int damage;

    PoisonEffect(int dur,double res,int damage){
        super(dur,res);
        this.damage=damage;
    }
    @Override
    public void tick(Person person){
        if (duration==0) person.getListOfEffects().remove(this);
        person.setHealth(person.getHealth()-damage);

    }
    @Override
    public String printInfo(){
        return "Pois"+Integer.toString(duration);
    }

}
