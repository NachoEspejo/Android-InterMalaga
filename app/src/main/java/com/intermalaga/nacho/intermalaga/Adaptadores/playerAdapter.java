package com.intermalaga.nacho.intermalaga.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intermalaga.nacho.intermalaga.R;
import com.intermalaga.nacho.intermalaga.Actividades.TeamActivity;
import com.intermalaga.nacho.intermalaga.Modelos.Player;
import com.squareup.picasso.Picasso;
import java.util.List;

public class playerAdapter extends RecyclerView.Adapter<playerAdapter.playerHolder> {

    private int layout ;
    private List<Player> data ;
    private OnItemClickListener listener ;
    private static Context contexto ;
    private static int posicion ;

    public playerAdapter(Context contexto, int layout, List<Player> data, OnItemClickListener listener) {
        this.listener = listener ;
        this.contexto = contexto ;
        this.layout   = layout;
        this.data     = data;
    }


    @NonNull
    @Override
    public playerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()) ;

        View vista = inflater.inflate(this.layout, null) ;

        playerHolder holder = new playerHolder(vista) ;

        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull playerHolder playerHolder, int pos) {

        playerHolder.bindItem(this.data.get(pos), this.listener) ;
    }

    @Override
    public int getItemCount() {
        return this.data.size() ;
    }

    public int getPosicion() { return posicion;}



    public class playerHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {

        private ImageView photo ;
        private TextView  name, position, category ;

        public playerHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.playerPhoto) ;
            name = itemView.findViewById(R.id.playerName) ;
            position = itemView.findViewById(R.id.playerPosition) ;
            category = itemView.findViewById(R.id.playerCategory);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    posicion = getAdapterPosition() ;
                    return false;
                }
            });


            itemView.setOnCreateContextMenuListener(this) ;
        }

        public void bindItem(final Player item, final OnItemClickListener listener) {

            name.setText(item.getName()) ;

            position.setText(item.getPosicion());

            category.setText(item.getCategory());



        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            MenuInflater inflater = ((TeamActivity) contexto).getMenuInflater() ;
            inflater.inflate(R.menu.player_options, contextMenu) ;
        }


    }


    public interface OnItemClickListener {
        void onItemClick(Player player, int pos) ;
    }

}


