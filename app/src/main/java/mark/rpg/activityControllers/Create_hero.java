package mark.rpg.activityControllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mark.rpg.CreateHeroFragment;
import mark.rpg.R;

public class Create_hero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hero);
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.containerCreateHero);
        if (fragment == null) {
            fragment = new CreateHeroFragment();
            fm.beginTransaction()
                    .add(R.id.containerCreateHero, fragment)
                    .commit();
        }
    }
    public void onClick(View view){
        Intent intent=new Intent(this,ChooseEnemy.class);
        intent.putExtra("eType","w");
        //intent.putExtra("Hero",hero);
        startActivity(intent);

    }
}
