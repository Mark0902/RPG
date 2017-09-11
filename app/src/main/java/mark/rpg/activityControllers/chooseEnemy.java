package mark.rpg.activityControllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.Calendar;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.Inventory;
import mark.rpg.R;

public class chooseEnemy extends AppCompatActivity {
    Enemy weakEnemy;
    Enemy midleEnemy;
    Enemy strongEnemy;
    Hero hero;
    GameStatus gs;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //TODO сделать ивенты, грабить корованы
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_enemy);
        hero = new Hero(5, 500, 150, 10, 33, 0.1);

        index=1;
        try {
            gs = new GameStatus().readSave(getApplicationContext(), index);

            hero = gs.getHero();
        } catch (Exception e) {
            hero = new Hero(5, 322, 150, 10, 33, 0.1);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Капут ((" + e.getClass().toString(),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            gs=new GameStatus(hero,new Inventory());
        }
        Calendar now;
        now=Calendar.getInstance();
        gs.timeHealing(hero,now);
        try {
            gs.writeSave(getApplicationContext(), index);
        }
        catch (Exception e1){}





       /* try {
            hero = gs.readSave(getApplicationContext(), 1).getHero();
        }
        catch (Exception e) {
            Inventory inv = new Inventory();
            gs=new GameStatus(hero, inv);
            try {
                gs.writeSave(getApplicationContext(), 1);
            } catch (Exception e1) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Капут ((" + e1.getClass().toString(),
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
    */
    }
    public void onWeakClick(View view) {
        Intent intent=new Intent(this,BattleActivity.class);
        intent.putExtra("eType","w");
        //intent.putExtra("Hero",hero);
        startActivity(intent);

    }

    public void onNormalClick(View view) {
        Intent intent=new Intent(this,BattleActivity.class);
        intent.putExtra("eType","n");
       // intent.putExtra("Hero",hero);
        startActivity(intent);

    }

    public void onStrongClick(View view) {
        Intent intent=new Intent(this,BattleActivity.class);
        intent.putExtra("eType","s");
       // intent.putExtra("Hero",hero);
        startActivity(intent);
    }
}
