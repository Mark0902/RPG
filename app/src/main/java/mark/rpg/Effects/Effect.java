package mark.rpg.Effects;

import mark.rpg.GameObjects.Person;

/**
 * Created by Марк on 06.07.2017.
 */

public abstract class Effect{
    int duration;
    double resist;
    public Effect(int dur,double res){
        this.duration=dur;
        this.resist=res;
    }
    public void tick(Person person){}
    public void durationIncrease(){
        duration=duration-1;
    }
    public String printInfo(){return "";}
}