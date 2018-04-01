package mark.rpg.activityControllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.Inventory;
import mark.rpg.R;

public class ChooseEnemy extends AppCompatActivity {
    Hero hero;
    GameStatus gs;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //TODO сделать ивенты, грабить корованы
        setContentView(R.layout.activity_choose_enemy);
        hero =  Hero.defaultHeroCreation();

        index=1;
        try {
            gs = new GameStatus().readSave(getApplicationContext(), index);

            hero = gs.getHero();
        } catch (Exception e) {
            hero =  Hero.defaultHeroCreation();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ExceptioN: " + e.getClass().toString(),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            gs=new GameStatus(hero,new Inventory());
        }
        gs.timeHealing(hero);
        try {
            gs.writeSave(getApplicationContext(), index);
        }
        catch (Exception e1){}





        try {
            hero = gs.readSave(getApplicationContext(), 1).getHero();
        }
        catch (Exception e) {
            Inventory inv = new Inventory();
            gs=new GameStatus(hero, inv);
            try {
                gs.writeSave(getApplicationContext(), 1);
            } catch (Exception e1) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "ExceptioN:" + e1.getClass().toString(),
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }


    }
    public void onWeakClick(View view) {
        Intent intent=new Intent(this,BattleActivityController.class);
        intent.putExtra("eType","w");
        //gameContinuationIntent.putExtra("Hero",hero);
        startActivity(intent);

    }

    public void onNormalClick(View view) {
        Intent intent=new Intent(this,BattleActivityController.class);
        intent.putExtra("eType","n");
       // gameContinuationIntent.putExtra("Hero",hero);
        startActivity(intent);

    }

    public void onStrongClick(View view) {
        Intent intent=new Intent(this,BattleActivityController.class);
        intent.putExtra("eType","s");
       // gameContinuationIntent.putExtra("Hero",hero);
        startActivity(intent);
    }
    public void heroInfoButton_OnClick(View view){
        Intent intent=new Intent(this,HeroInfoController.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.leaveGameQuestion)
                .setNegativeButton(getString(R.string.no), null)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //SomeActivity - имя класса Activity для которой переопределяем onBackPressed();
                        ChooseEnemy.super.finish();
                    }
                }).create().show();
    }
}
