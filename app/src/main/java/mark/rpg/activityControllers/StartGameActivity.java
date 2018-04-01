package mark.rpg.activityControllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.R;

/**
 * Created by Work on 05.03.2018.
 */

public class StartGameActivity extends AppCompatActivity {

    GameStatus[] gameStatuses;
    Hero curHero;
    Button[] saveSlotsButtons;
    Intent gameContinuationIntent;
    Intent createNewHeroIntent;
    boolean g1;
    boolean g2;
    boolean g3;

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
                g1=true;
            }
            else{
                saveSlotsButtons[0].setText(saveSlotsButtons[0].getText()+"1"+"\n"+getString(R.string.emptySaveSlot));
                g1=false;
            }


            gameStatuses[1] = new GameStatus().readSave(getApplicationContext(), 2);
            if(gameStatuses[1]!=null) {
                curHero = gameStatuses[1].getHero();
                saveSlotsButtons[1].setText(saveSlotsButtons[1].getText()+"2"+"\n" + curHero.printNameLevelInfo());
                g2=true;
            }
            else{
                saveSlotsButtons[1].setText(saveSlotsButtons[1].getText()+"2"+"\n"+getString(R.string.emptySaveSlot));
                g2=false;
            }

            gameStatuses[2] = new GameStatus().readSave(getApplicationContext(), 3);
            if(gameStatuses[2]!=null) {
                curHero = gameStatuses[2].getHero();
                saveSlotsButtons[2].setText(saveSlotsButtons[2].getText()+"3"+"\n" + curHero.printNameLevelInfo());
                g3=true;
            }
            else{
                saveSlotsButtons[2].setText(saveSlotsButtons[2].getText()+"3"+"\n"+getString(R.string.emptySaveSlot));
                g3=false;
            }
        }
        catch (Exception e) {
            int i = 1;
        }

        gameContinuationIntent =new Intent(this,ChooseEnemy.class);
        gameContinuationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        createNewHeroIntent=new Intent(this,CreateHeroActivity.class);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveSlot_1:
                    if(g1) {
                        GameStatus.currentGameSaveIndex = 1;
                        startActivity(gameContinuationIntent);
                        break;
                    }
                    else{
                        GameStatus.currentGameSaveIndex = 1;
                        startActivity(createNewHeroIntent);
                        break;
                    }
                case R.id.saveSlot_2:
                    if(g2) {
                        GameStatus.currentGameSaveIndex = 2;
                        startActivity(gameContinuationIntent);
                        break;
                    }
                    else {
                        GameStatus.currentGameSaveIndex = 2;
                        startActivity(createNewHeroIntent);
                        break;
                    }
                case R.id.saveSlot_3:
                    if(g3) {
                        GameStatus.currentGameSaveIndex = 3;
                        startActivity(gameContinuationIntent);
                        break;
                    }
                    else{
                        GameStatus.currentGameSaveIndex = 3;
                        startActivity(createNewHeroIntent);
                        break;
                    }
            }
        }
    };
    //TODO УДАЛЕНИЕ СЕЙВОВ
    public void delButton_onClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.delSave1Button:
                //
                break;
            case R.id.delSave2Button:
                //
                break;
            case R.id.delSave3Button:
                //
                break;
        }
    }

}
