package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.intermalaga.nacho.intermalaga.Helpers.InterMalagaDB;
import com.intermalaga.nacho.intermalaga.Modelos.Usuario;
import com.intermalaga.nacho.intermalaga.R;

import java.io.Serializable;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnEresJugador ;
    private EditText email, password ;
    private TextView registro;

    private FirebaseAuth fbAuth ;
    private FirebaseDatabase fbDB ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email        = findViewById(R.id.email) ;
        password    = findViewById(R.id.password) ;
        btnLogin    = findViewById(R.id.login_button) ;
        btnEresJugador = findViewById(R.id.eres_jugador) ;
        registro = findViewById(R.id.register);

        fbAuth = FirebaseAuth.getInstance() ;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View vista) {

                String usuario= email.getText().toString().trim() ;
                String clave   = password.getText().toString().trim() ;

                if (!usuario.isEmpty() && !clave.isEmpty()) {

                    final FirebaseAuth mAuth = FirebaseAuth.getInstance() ;

                    mAuth.signInWithEmailAndPassword(usuario, clave)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {

                                        FirebaseDatabase db = FirebaseDatabase.getInstance() ;

                                        DatabaseReference ref = db.getReference("usuario") ;

                                        ref.child(mAuth.getCurrentUser().getUid())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                        if (dataSnapshot.exists()) {

                                                            Usuario usuario = dataSnapshot.getValue(Usuario.class) ;

                                                            Intent intent = new Intent(LoginActivity.this, TeamActivity.class) ;

                                                            Bundle bundle = new Bundle() ;
                                                            bundle.putSerializable("usuario", (Serializable) usuario) ;

                                                            intent.putExtras(bundle) ;

                                                            startActivity(intent) ;

                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                    } else if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "El usuario y/o la contraseña no coinciden", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }) ;


                } else {
                    Toast.makeText(LoginActivity.this, "El usuario y/o la contraseña no pueden estar vacíos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEresJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(LoginActivity.this, TeamActivity.class) ;
                startActivity(intent) ;

            }
        });


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class) ;
                startActivity(intent) ;

            }
        });


    }
}
