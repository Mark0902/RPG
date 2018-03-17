package mark.rpg.activityControllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.R;

/**
 * Created by Work on 04.03.2018.
 */

public class HeroInfoController extends AppCompatActivity {

    GameStatus gameStatus;
    Hero hero;

    TextView textViewForStats;
    TextView textViewForName;
    TextView textViewForLvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_info_activity);
        try {
            gameStatus = new GameStatus().readSave(getApplicationContext(),GameStatus.currentGameSaveIndex);

            hero = gameStatus.getHero();
        }
        catch (Exception e){
            int i=1;
        }
        textViewForStats=(TextView)findViewById(R.id.infoText);
        textViewForStats.setText("");

        textViewForName=(TextView)findViewById(R.id.infoHeroName);
        textViewForName.setText(hero.getName().toString());

        textViewForLvl=(TextView)findViewById(R.id.infoHeroLvl);
        textViewForLvl.setText(hero.getLevel()+" Уровень");
        printInfo();
    }

    private void printInfo(){
        textViewForStats.append(getString(R.string.infoHealth)+":     "+hero.getHealth()+"/"+hero.getMaxHealth()+"\n");
        textViewForStats.append(getString(R.string.infoMana)+":     "+hero.getMana()+"/"+hero.getMaxMana()+"\n");
        textViewForStats.append(getString(R.string.infoDamage)+":     "+hero.getMinAttack()+" - "+hero.getMaxAttack()+"\n");
        textViewForStats.append(getString(R.string.infoDodge)+":     "+hero.getDodgeChance()+"\n");
        textViewForStats.append(getString(R.string.infoCrit)+":     "+hero.getCritChance()+"\n");
        textViewForStats.append(getString(R.string.infoResist)+":     "+hero.getResist()+"\n");

    }

    public void backButton_onClick(View view){
        Intent intent=new Intent(this,ChooseEnemy.class);
        startActivity(intent);
    }



}
