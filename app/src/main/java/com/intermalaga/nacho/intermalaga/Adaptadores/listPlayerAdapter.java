package com.intermalaga.nacho.intermalaga.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.intermalaga.nacho.intermalaga.R;
import com.intermalaga.nacho.intermalaga.Actividades.TeamActivity;
import com.intermalaga.nacho.intermalaga.Modelos.PlayerList;

import java.util.List;

public class listPlayerAdapter extends RecyclerView.Adapter<listPlayerAdapter.listPlayerHolder> {

    private int layout ;
    private Context contexto ;
    private List<PlayerList> data = null ;

    public listPlayerAdapter(Context contexto, int layout, List<PlayerList> data) {
        this.layout = layout;
        this.contexto = contexto;
        this.data = data;
    }

    @NonNull
    @Override
    public listPlayerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(this.layout, null) ;

        listPlayerHolder holder = new listPlayerHolder(vista) ;

        //
        return holder ;
    }


    @Override
    public void onBindViewHolder(@NonNull listPlayerHolder listPlayerHolder, int position) {
        listPlayerHolder.bindItem(this.data.get(position), position);
    }


    @Override
    public int getItemCount() {
        return this.data.size() ;
    }


    public class listPlayerHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {

        private TextView nombre ;

        public listPlayerHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.playerName) ;

            itemView.setOnCreateContextMenuListener(this) ;
        }

        public void bindItem(PlayerList item, int position) {
            //
            nombre.setText(item.getNombre()) ;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //
            MenuInflater inflater = ((TeamActivity) contexto).getMenuInflater() ;
            inflater.inflate(R.menu.player_options, menu) ;
        }
    }
}
