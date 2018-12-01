package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.intermalaga.nacho.intermalaga.Modelos.Usuario;
import com.intermalaga.nacho.intermalaga.R;


public class RegisterActivity extends AppCompatActivity {

    private Button btnCrear,btnCancelar;
    private EditText correo, nombre, password;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setSubtitle("REGISTRO");

        nombre = findViewById(R.id.name);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.password);

        btnCancelar = findViewById(R.id.cancelar);
        btnCrear = findViewById(R.id.crear);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class) ;

                intent.putExtra("mensaje","REGISTRO cancelado");
                startActivity(intent) ;
            }
        });


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                final String nameUser = nombre.getText().toString().trim() ;
                final String correoUser = correo.getText().toString().trim() ;
                final String pwdUser = password.getText().toString().trim() ;

                if (nameUser.isEmpty() || correoUser.isEmpty() || pwdUser.isEmpty()) {
                    Snackbar.make(vista, "Error vacios", Snackbar.LENGTH_SHORT).show();
                } else {

                    mAuth = FirebaseAuth.getInstance() ;
                    mAuth.createUserWithEmailAndPassword(correoUser,pwdUser)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        db = FirebaseDatabase.getInstance();

                                        DatabaseReference ref = db.getReference("usuario");

                                        FirebaseUser fbUser = mAuth.getCurrentUser();

                                        String uid = fbUser.getUid();

                                        Usuario miUsuario = new Usuario(uid,
                                                nombre.getText().toString(),
                                                correo.getText().toString());

                                        ref.child(uid).setValue(miUsuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(RegisterActivity.this, "Completado.", Toast.LENGTH_LONG).show();
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "completado con exito.", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class) ;

                                                    intent.putExtra("mensaje","Bienvenido/a al Login");
                                                    startActivity(intent) ;
                                                }
                                            }
                                        }) ;

                                        Toast.makeText(RegisterActivity.this, "Se ha creado con éxito.", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Se ha producido un error en el registro.", Toast.LENGTH_LONG).show();
                                    }

                                }
                            }) ;

                }
            }
        });
    }

}
