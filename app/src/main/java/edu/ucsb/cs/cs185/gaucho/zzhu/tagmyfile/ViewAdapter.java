package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.PersonViewHolder>{

    ViewAdapter(){

    }

    @Override
    public ViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewAdapter.PersonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView fileName;
        ImageView iconPhoto;
        public PersonViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            fileName = (TextView)itemView.findViewById(R.id.file_name);
            iconPhoto = (ImageView)itemView.findViewById(R.id.icon_photo);
        }
    }
}
