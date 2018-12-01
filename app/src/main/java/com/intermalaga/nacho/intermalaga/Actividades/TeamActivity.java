package com.intermalaga.nacho.intermalaga.Actividades;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.intermalaga.nacho.intermalaga.Adaptadores.playerAdapter;
import com.intermalaga.nacho.intermalaga.Modelos.Player;
import com.intermalaga.nacho.intermalaga.Modelos.Usuario;
import com.intermalaga.nacho.intermalaga.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class TeamActivity extends AppCompatActivity {

    private FirebaseAuth fbAuth ;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db ;
    private DatabaseReference ref;
    private FirebaseAuth.AuthStateListener authListener ;
    private RecyclerView recycler ;
    private playerAdapter adapter ;
    private Usuario usrData ;
    private String uid ;

    private FloatingActionButton crearPlayer;

    private List<Player> Player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setSubtitle("Equipo");


        recycler = findViewById(R.id.listTeamActivity);
        crearPlayer = findViewById(R.id.createPlayer);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(manager);

        List<Player> datos = getPlayer();

        Player = new ArrayList<>();
        adapter = new playerAdapter(this, R.layout.team_player_item,
                Player, new playerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Player player, int position) {

                Toast.makeText(getApplicationContext(),
                        player.toString(), Toast.LENGTH_SHORT).show() ;
            }
        }) ;

        recycler.setAdapter(adapter);
        fbAuth = FirebaseAuth.getInstance();

        crearPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                Intent intent = new Intent(TeamActivity.this, CrearPlayers.class) ;

                intent.putExtra("mensaje","Crear player");
                startActivity(intent) ;
            }
        });

    }

    public List<Player> getPlayer() {

        db = FirebaseDatabase.getInstance() ;
        mAuth = FirebaseAuth.getInstance();

        String uid = mAuth.getCurrentUser().getUid();

        ref = db.getReference("jugador/") ;
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Player.clear();

                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    final String playerid = item.getKey() ;

                    ref = db.getReference("jugador/" + playerid );
                    Log.d("prueba", String.valueOf(ref));
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot ds) {


                            Player.add(ds.getValue(Player.class)) ;
                            Log.d("pruebabodas", String.valueOf(Player));
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(TeamActivity.this, "Error en la creacion del array", Toast.LENGTH_SHORT).show();
                        }
                    });
                    adapter.notifyDataSetChanged();

                }
                Log.d("pruebabodasfueradelfor", String.valueOf(Player));
                adapter.notifyDataSetChanged();


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TeamActivity.this, "Error en el acceso a jugador", Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("pruebabodas2", String.valueOf(Player));
        return Player;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.Perfil:
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                break ;
            case R.id.Options:
                Toast.makeText(this, "Opciones", Toast.LENGTH_SHORT).show();
                break ;
            case R.id.TeamPlayerExit:
                Intent intent = new Intent(TeamActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Muchas gracias por usar nuestra APP", Toast.LENGTH_SHORT).show();
                break ;

            default :
                return super.onOptionsItemSelected(item);
        }

        return true ;
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.player_options, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.playerInfo:
                String name = Player.get(adapter.getPosicion()).getName();
                String photo = Player.get(adapter.getPosicion()).getFoto();
                String pos = Player.get(adapter.getPosicion()).getPosicion();
                String cate = Player.get(adapter.getPosicion()).getCategory();

                Bundle bundle = new Bundle();
                bundle.putSerializable("name", name);
                bundle.putSerializable("photo", photo);
                bundle.putSerializable("pos", pos);
                bundle.putSerializable("cate", cate);

                Intent intent3 = new Intent(TeamActivity.this, InfoPlayer.class) ;
                intent3.putExtras(bundle) ;
                startActivity(intent3) ;

                break ;

            case R.id.playerDelete:

                db.getReference("jugador/" + Player.get(adapter.getPosicion()).getIdMDB()).removeValue();
                Toast.makeText(this, "Esto en un futuro desperdirá al jugador", Toast.LENGTH_LONG).show() ;
                adapter.notifyDataSetChanged();
                break ;
        }
        return false;
    }
}

