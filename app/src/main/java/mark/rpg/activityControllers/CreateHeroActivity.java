package mark.rpg.activityControllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mark.rpg.GameObjects.GameClass;
import mark.rpg.GameObjects.Hero;
import mark.rpg.GameObjects.Race;
import mark.rpg.GameStatus;
import mark.rpg.Inventory;
import mark.rpg.R;

public class CreateHeroActivity extends AppCompatActivity {

    Spinner raceSpinner;
    Spinner gameClassSpinner;
    TextView statsTextView;
    EditText nameField;
    Race race=Race.HUMAN;
    GameClass gameClass=GameClass.WARRIOR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hero);
        raceSpinner=(Spinner)findViewById(R.id.raceSpinner);
        gameClassSpinner=(Spinner)findViewById(R.id.gameClassSpinner);
        statsTextView=(TextView)findViewById(R.id.classTitleDUMMY);
        nameField=(EditText)findViewById(R.id.enterHeroName);

        ArrayAdapter<?> raceSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.gameRaces, android.R.layout.simple_spinner_item);
        raceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        raceSpinner.setAdapter(raceSpinnerAdapter);

        ArrayAdapter<?> gameClassSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.gameClasses, android.R.layout.simple_spinner_item);
        gameClassSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gameClassSpinner.setAdapter(gameClassSpinnerAdapter);

        statsTextViewFilling();

        gameClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,View itemSelected, int selectedItemPosition, long selectedId) {

                switch (selectedItemPosition){
                    case 0:
                        gameClass=GameClass.WARRIOR;
                        //statsTextView.append("");
                        statsTextViewFilling();
                        break;
                    case 1:
                        gameClass=GameClass.ROGUE;
                        statsTextViewFilling();
                        break;
                    case 2:
                        gameClass=GameClass.MAGE;
                        statsTextViewFilling();
                        break;
                    default:
                        statsTextView.append("ERROR");
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                statsTextView.append("ERROR");
            }
        });

        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,View itemSelected, int selectedItemPosition, long selectedId) {

                switch (selectedItemPosition){
                    case 0:
                        race=Race.HUMAN;
                        statsTextViewFilling();
                        break;
                    case 1:
                        race=Race.ELF;
                        statsTextViewFilling();
                        break;
                    case 2:
                        race=Race.DWARF;
                        statsTextViewFilling();
                        break;
                    case 3:
                        race=Race.ORC;
                        statsTextViewFilling();
                        break;
                    default:
                        statsTextView.append("ERROR");
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                statsTextView.append("ERROR");

            }
        });

    }

    private void statsTextViewFilling(){
            statsTextView.setText("");
            statsTextView.append(race.toString() + "\n");
            statsTextView.append(gameClass.toString() + "\n");

    }

    private boolean isHeroNameCorrect(){
        //TODO подумать
        return true;
    }

    public void createHero_onClick(View view){
        String name=nameField.getText().toString();
        if(!isHeroNameCorrect())  return;
        Hero createdHero = Hero.firstLvlHeroCreation(race, gameClass, name);

        GameStatus gameStatus=new GameStatus(createdHero,new Inventory());
        try {
            gameStatus.writeSave(this);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ExceptioN:" + e.getClass().toString(),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        Intent intent=new Intent(this,ChooseEnemy.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
