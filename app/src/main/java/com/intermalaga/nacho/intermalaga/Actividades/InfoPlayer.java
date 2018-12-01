package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.intermalaga.nacho.intermalaga.R;

public class InfoPlayer extends AppCompatActivity {

    private TextView nombre, position, category ;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_player);

        Bundle bundle = getIntent().getExtras() ;
        String NamePlayer = bundle.getString("name") ;
        String PositionPlayer = bundle.getString("pos") ;
        String CategoryPlayer = bundle.getString("cate") ;

        btn = findViewById(R.id.InfoDejar);


        nombre = findViewById(R.id.InfoName) ;
        nombre.setText(NamePlayer) ;

        position = findViewById(R.id.InfoPos) ;
        position.setText(PositionPlayer) ;

        category = findViewById(R.id.InfoCat) ;
        category.setText(CategoryPlayer) ;



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                Intent intent = new Intent(InfoPlayer.this, TeamActivity.class) ;

                startActivity(intent) ;
            }
        });
    }
}