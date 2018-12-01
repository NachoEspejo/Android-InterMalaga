package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.intermalaga.nacho.intermalaga.R;
import com.intermalaga.nacho.intermalaga.Modelos.Player;

public class CrearPlayers extends AppCompatActivity {

    private Button btnCrear,btnCancelar;
    private Spinner posPlayer, catPlayer;
    private EditText nomPlayer;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearplayer);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setSubtitle("Contratación de jugador");

        nomPlayer = findViewById(R.id.name);
        posPlayer = findViewById(R.id.pos);
        catPlayer = findViewById(R.id.cate);

        btnCancelar = findViewById(R.id.cancelar);
        btnCrear = findViewById(R.id.crear);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                Intent intent = new Intent(CrearPlayers.this, TeamActivity.class) ;

                intent.putExtra("mensaje","Contrato cancelado");
                startActivity(intent) ;
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                final String namePlayer = nomPlayer.getText().toString().trim() ;
                final String positionPlayer = posPlayer.getSelectedItem().toString().trim() ;
                final String categoryPlayer = catPlayer.getSelectedItem().toString().trim();

                if (namePlayer.isEmpty()) {
                    Snackbar.make(vista, "Debes introducir un nombre", Snackbar.LENGTH_SHORT).show();
                } else {

                    mAuth = FirebaseAuth.getInstance();


                    db = FirebaseDatabase.getInstance();

                    DatabaseReference ref = db.getReference("jugador");

                    FirebaseUser fbUser = mAuth.getCurrentUser();

                    String uid = fbUser.getUid();

                    String key = ref.child("jugador").push().getKey();


                    Player miJugador = new Player(key,
                            namePlayer,
                            positionPlayer,
                            categoryPlayer,
                            uid);

                    ref.child(key).setValue(miJugador).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CrearPlayers.this, "Bienvenido al club, " + namePlayer, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CrearPlayers.this, TeamActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

}
