package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.intermalaga.nacho.intermalaga.R;

public class ListActivity extends AppCompatActivity {

        private ImageView imgTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setSubtitle("Menu Principal");
        getSupportActionBar().setTitle("Inter Málaga");

        imgTeam = findViewById(R.id.team);


        imgTeam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this, TeamActivity.class) ;
                startActivity(intent) ;

            }
        });
    }
}
