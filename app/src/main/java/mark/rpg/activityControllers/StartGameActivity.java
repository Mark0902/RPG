package mark.rpg.activityControllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.R;
import mark.rpg.Spells.FireBall;

/**
 * Created by Work on 05.03.2018.
 */

public class StartGameActivity extends AppCompatActivity {

    GameStatus[] gameStatuses;
    Hero curHero;
    Button[] saveSlotsButtons;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        gameStatuses=new GameStatus[3];
        saveSlotsButtons=new Button[3];
        saveSlotsButtons[0]=(Button)findViewById(R.id.saveSlot_1);
        saveSlotsButtons[0].setOnClickListener(onClickListener);
        saveSlotsButtons[1]=(Button)findViewById(R.id.saveSlot_2);
        saveSlotsButtons[1].setOnClickListener(onClickListener);
        saveSlotsButtons[2]=(Button)findViewById(R.id.saveSlot_3);
        saveSlotsButtons[2].setOnClickListener(onClickListener);
        try {
            gameStatuses[0] = new GameStatus().readSave(getApplicationContext(), 1);
            if(gameStatuses[0]!=null) {
                curHero = gameStatuses[0].getHero();
              //  saveSlotsButtons[0].append("\n" + curHero.printNameLevelInfo()); // Не работало так хмммм
                saveSlotsButtons[0].setText(saveSlotsButtons[0].getText()+"1"+"\n" + curHero.printNameLevelInfo());
            }
            else{
                saveSlotsButtons[0].setText(saveSlotsButtons[0].getText()+"1"+"\n"+getString(R.string.emptySaveSlot));
            }


            gameStatuses[1] = new GameStatus().readSave(getApplicationContext(), 2);
            if(gameStatuses[1]!=null) {
                curHero = gameStatuses[1].getHero();
                saveSlotsButtons[1].setText(saveSlotsButtons[1].getText()+"2"+"\n" + curHero.printNameLevelInfo());
            }
            else{
                saveSlotsButtons[1].setText(saveSlotsButtons[1].getText()+"2"+"\n"+getString(R.string.emptySaveSlot));
            }

            gameStatuses[2] = new GameStatus().readSave(getApplicationContext(), 3);
            if(gameStatuses[2]!=null) {
                curHero = gameStatuses[2].getHero();
                saveSlotsButtons[2].setText(saveSlotsButtons[2].getText()+"3"+"\n" + curHero.printNameLevelInfo());
            }
            else{
                saveSlotsButtons[2].setText(saveSlotsButtons[2].getText()+"3"+"\n"+getString(R.string.emptySaveSlot));
            }
        }
        catch (Exception e) {
            int i = 1;
        }

        intent=new Intent(this,ChooseEnemy.class);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveSlot_1:
                    GameStatus.currentGameSaveIndex=1;
                    startActivity(intent);
                    break;
                case R.id.saveSlot_2:
                    GameStatus.currentGameSaveIndex=2;
                    startActivity(intent);
                    break;
                case R.id.saveSlot_3:
                    GameStatus.currentGameSaveIndex=3;
                    startActivity(intent);
                    break;
            }
        }
    };

}
