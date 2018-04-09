package mark.rpg.activityControllers;

/**
 * Created by Work on 04.11.2017.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import mark.rpg.GameObjects.Enemy;
import mark.rpg.GameObjects.Hero;
import mark.rpg.GameStatus;
import mark.rpg.Inventory;
import mark.rpg.R;
import mark.rpg.Spells.FireBall;
import mark.rpg.Spells.HealingSpell;
import mark.rpg.Spells.MeteorSpell;
import mark.rpg.Spells.Spell;
import mark.rpg.animations.AnimatorSetProvider;

public class BattleActivityController extends AppCompatActivity {

    Hero hero;
    GameStatus gameStatus;

    Enemy enemy;

    TextView statisticsLog;
    ImageButton hitButton;
    TextView heroHealth;
    TextView enemyHealth;
    TextView heroMana;
    TextView heroName;
    TextView enemyName;

    TableLayout skillPanel;
    ImageButton firstSkill;
    ImageButton secondSkill;
    ImageButton thirdSkill;
    ImageButton fourthSkill;

    Spell[] spellArr;
    Spell firstSpell;
    Spell secondSpell;

    TextView mc1;
    TextView mc2;
    TextView mc3;
    TextView mc4;

    ProgressBar heroBar;
    ProgressBar enemyBar;
    ProgressBar heroManaBar;
    ImageView heroPicture;
    ImageView enemyPicture;

    Boolean skillPanelIsOpen;

    //Animation heroAttackAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        //heroAttackAnimation= AnimationUtils.loadAnimation(this,R.anim.attack_animation);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();


        Intent intent=getIntent();

        String type=intent.getStringExtra("eType");
        // hero=(Hero)getIntent().getParcelableExtra("Hero");

        //TODO передавать индекс сохранения в интентах  ! Или сделать статик !


        int index=GameStatus.currentGameSaveIndex;
        try {
            gameStatus = new GameStatus().readSave(getApplicationContext(),index);

            hero = gameStatus.getHero();
        }
        catch(Exception e){
            hero= Hero.defaultHeroCreation();
            gameStatus=new GameStatus(hero,new Inventory());
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ошибка чтения(мб при первом запуске)"+e.getClass().toString(),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        Random rnd=new Random();

        // Возможно не соблюдение размероВ
        int ind;

        if(type.equals("w")) {
            ind=rnd.nextInt(Enemy.WEAK_ENEMIES.length);
            enemy=new Enemy(Enemy.WEAK_ENEMIES[ind]);
        }
        if (type.equals("n")) {
            ind=rnd.nextInt(Enemy.ENEMIES.length);
            enemy=new Enemy(Enemy.ENEMIES[ind]);
        }
        if(type.equals("s")) {
            ind=rnd.nextInt(Enemy.STRONG_ENEMIES.length);
            enemy=new Enemy(Enemy.STRONG_ENEMIES[ind]);
        }


       /*hero.setEmptyListOfSpells();
        Spell[] spellArr=new Spell[5];
       hero.getListOfSpells().toArray(Spell[5] spellArr)
       hero.getListOfSpells().add(new HealingSpell());
        hero.getListOfSpells().add(new HealingSpell());
        hero.getListOfSpells().add(new MeteorSpell());
       hero.getListOfSpells().add(new FireBall());
        hero.getListOfSpells().add(new FireBall());*/



        // FireBall fb=new FireBall();
        //hero.getListOfSpells().add(new MeteorSpell());
        //Spell secondSpell=new MeteorSpell();

        spellArr=new Spell[5];
        spellArr[0]=new FireBall();
        spellArr[1]=new FireBall();
        spellArr[2]=new MeteorSpell();
        spellArr[3]=new HealingSpell();
        spellArr[4]=new FireBall();







        hitButton = (ImageButton) findViewById(R.id.attackButton);


        statisticsLog=(TextView)findViewById(R.id.statistics);
       // statisticsLog.setTextSize(12);
        //В форме ето дело


        int i=0;
        // ПАНЕЛЬ СКИЛЛОВ
        skillPanel=(TableLayout)findViewById(R.id.skillPanel);


        firstSkill=(ImageButton)findViewById(R.id.SkillNo1);
        firstSkill.setOnClickListener(onClickListener);
        //firstSkill.setImageResource(hero.getListOfSpells().get(0).getImageId());
        firstSkill.setImageResource(spellArr[0].getImageId());


        secondSkill=(ImageButton)findViewById(R.id.SkillNo2);
        secondSkill.setOnClickListener(onClickListener);
        secondSkill.setImageResource(spellArr[1].getImageId());


        thirdSkill=(ImageButton)findViewById(R.id.SkillNo3);
        thirdSkill.setOnClickListener(onClickListener);
        thirdSkill.setImageResource(spellArr[2].getImageId());

        fourthSkill=(ImageButton)findViewById(R.id.SkillNo4);
        fourthSkill.setOnClickListener(onClickListener);
        fourthSkill.setImageResource(spellArr[3].getImageId());




        mc1=(TextView)findViewById(R.id.mcostNo1);
        mc2=(TextView)findViewById(R.id.mcostNo2);
        mc3=(TextView)findViewById(R.id.mcostNo3);
        mc4=(TextView)findViewById(R.id.mcostNo4);

        mc1.setText(Double.toString(spellArr[0].getBaseManacost()));
        mc2.setText(Double.toString(spellArr[1].getBaseManacost()));
        mc3.setText(Double.toString(spellArr[2].getBaseManacost()));
        mc4.setText(Double.toString(spellArr[3].getBaseManacost()));







        heroName = (TextView) findViewById(R.id.heroName);
        heroName.setText(hero.printNameLevelInfo());

        enemyName = (TextView) findViewById(R.id.enemyName);
        enemyName.setText(enemy.printNameLevelInfo());


        heroHealth = (TextView) findViewById(R.id.heroHealth);
        heroHealth.setTextColor(Color.RED);

        heroMana=(TextView)findViewById(R.id.heroMana);


        enemyHealth = (TextView) findViewById(R.id.enemyHealth);
        enemyHealth.setTextColor(Color.RED);


        heroPicture=(ImageView)findViewById(R.id.heroPicture);
        enemyPicture=(ImageView)findViewById(R.id.enemyPicture);

        heroPicture.setImageResource(hero.getPersonImageId());
        enemyPicture.setImageResource(enemy.getPersonImageId());


        heroBar = (ProgressBar) findViewById(R.id.hpBar);
        // heroBar.setDrawingCacheBackgroundColor(10);
        enemyBar = (ProgressBar) findViewById(R.id.enemyHpBar);
        //enemyBar.setDrawingCacheBackgroundColor(255);
        heroManaBar=(ProgressBar)findViewById(R.id.manaBar) ;

        heroBar.setMax(hero.getMaxHealth());
        enemyBar.setMax(enemy.getMaxHealth());
        heroManaBar.setMax((int)hero.getMaxMana());

        statisticsLog.setMovementMethod(new ScrollingMovementMethod());

        skillPanel.setVisibility(Button.INVISIBLE);
        skillPanelIsOpen = false;

        //layotParamsOfPanelOpenerButton=skillPanelOpener.getLayoutParams();

        int i1=0;
        //  enemyLogTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    @Override
    protected void onResume() {
        super.onResume();
        redraw();
        //ImageButton firstSkill=(ImageButton)findViewById(R.id.firstSkill);
        //firstSkill.
    }

    private void redraw() {

        heroHealth.setText(Integer.toString(hero.getHealth()));
        enemyHealth.setText(Integer.toString(enemy.getHealth()));
        heroMana.setText(Integer.toString((int)hero.getMana()));

        heroBar.setProgress(hero.getHealth());

        enemyBar.setProgress(enemy.getHealth());

        heroManaBar.setProgress((int)hero.getMana());

    }

    public void attackOnClick(View view) {
        redraw();
        hero.hitWithLog(enemy,statisticsLog);
        AnimatorSet set=AnimatorSetProvider.evasionLeftAnimatorSet(heroPicture);
        set.start();

        set=AnimatorSetProvider.evasionRightAnimatorSet(heroPicture);
      //  set.start();


        set=AnimatorSetProvider.fireballAnimatorSet(heroPicture,enemyPicture);
      //  set.start();



        //startAnimation(heroPicture,enemyPicture);
       // heroPicture.startAnimation(heroAttackAnimation);
        checkWinner();
        redraw();
        enemiesTurn();

    }
    public void SkillPanelOpen_OnClick(View view){
        if (!skillPanelIsOpen) {
            //hitButton.setVisibility(Button.INVISIBLE);
           // skillPanelOpener.setLayoutParams(hitButton.getLayoutParams());
            skillPanel.setVisibility(Button.VISIBLE);
        }
        else{
           // hitButton.setVisibility(Button.VISIBLE);
           // skillPanelOpener.setLayoutParams(layotParamsOfPanelOpenerButton);
            skillPanel.setVisibility(Button.INVISIBLE);

        }
        skillPanelIsOpen = !skillPanelIsOpen;
    }

    // TODO сделать листенеров и список спеллов
   /* public void onClickFirstSkill(View view) {

        HealingSpell hs=new HealingSpell();

        hs.effectWithLog(hero,enemy,statisticsLog);
        redraw();
        checkWinner();
        enemiesTurn();
        //Вывод знач хила
    }

    public void onClickSecondSkill(View view) {
        FireBall fb=new FireBall();
        fb.effectWithLog(hero,enemy,statisticsLog);
        redraw();
        checkWinner();
    }
    */

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.SkillNo1:
                    // hero.getListOfSpells().get(0).effectWithLog(hero,enemy,statisticsLog);
                    FireBall fb=new FireBall();
                    spellArr[0].effectWithLog(hero,enemy,statisticsLog);
                    enemiesTurn();
                    break;
                case R.id.SkillNo2:
                    spellArr[1].effectWithLog(hero,enemy,statisticsLog);
                    enemiesTurn();
                    break;
                case R.id.SkillNo3:
                    spellArr[2].effectWithLog(hero,enemy,statisticsLog);
                    enemiesTurn();
                    break;
                case R.id.SkillNo4:
                    spellArr[3].effectWithLog(hero,enemy,statisticsLog);
                    enemiesTurn();
                    break;

            }
        }
    };


    private void enemiesTurn(){
        enemy.hitWithLog(hero, statisticsLog);
        redraw();
        checkWinner();
        hero.manaRegen();
    }

    private void checkWinner(){
        //  WIN
        if (hero.isAlive() && !enemy.isAlive()){
            //hitButton.setVisibility(Button.INVISIBLE);
            statisticsLog.append("\n");;
            statisticsLog.setTextColor(Color.RED);
            statisticsLog.append("ПОБЕДА");

            enemy.getReward(gameStatus.getInventory());

            try {
                gameStatus.writeSave(getApplicationContext());
            }
            catch(Exception e){Toast toast = Toast.makeText(getApplicationContext(),
                    "Ошибка записи"+e.getClass().toString(),
                    Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();}


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Победа!")
                    .setMessage("Добыто ??? золота")
                    .setCancelable(false)
                    .setNeutralButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intentToCE=new Intent(getApplicationContext(),ChooseEnemy.class);
                                    intentToCE.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intentToCE);
                                    dialog.cancel();


                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();
        }
        // LOSE
        if (!hero.isAlive() && enemy.isAlive()){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Поражение!")
                    .setMessage("??")
                    .setCancelable(false)
                    .setNeutralButton("ОК(",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intentToCE=new Intent(getApplicationContext(),ChooseEnemy.class);
                                    intentToCE.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intentToCE);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Поражение",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            redraw();

            hitButton.setEnabled(false);
            skillPanel.setEnabled(false);
        }
        //NOONE win ? //TODO
        /*if (!hero.isAlive()) {

            hitButton.setVisibility(Button.INVISIBLE);
            //скилл панель убрать
        }
        */
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.battleExitTitle))
                .setMessage(getString(R.string.battleExitText))
                .setNegativeButton(getString(R.string.no), null)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                       BattleActivityController.super.onBackPressed();
                    }
                }).create().show();
    }


    private void startAnimation(View hp,View enemy){
        float fireballYStart=hp.getTop();
        float fireballYEnd =enemy.getHeight();

        final ObjectAnimator fireballAnimatorRev = ObjectAnimator
                .ofFloat(hp, "y", fireballYEnd, fireballYStart)
                .setDuration(1000);

        ObjectAnimator fireballAnimator=ObjectAnimator
                .ofFloat(hp,"y",fireballYStart, fireballYEnd)
                .setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
       // animatorSet.play(fireballAnimator).before(fireballAnimatorRev);
        animatorSet.playSequentially(fireballAnimator,fireballAnimatorRev);
        animatorSet.start();


        }



}






