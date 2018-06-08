package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.arch.persistence.room.Entity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.PersonViewHolder>{

    private List<MyFile> files;
    Context context;

    ViewAdapter(List<MyFile> files, Context context){
        this.context = context;
        this.files = files;
        Log.d("fi", Integer.toString(getItemCount()));
    }

    @Override
    public ViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewAdapter.PersonViewHolder holder, final int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", "Clicked "+position);
                Intent intent = new Intent(context,InformActivity.class);
                MyFile tmpFile = files.get(position);
                intent.putExtra("file", tmpFile);
                context.startActivity(intent);
            }
        });
        holder.fileName.setText(files.get(position).name);
        holder.iconPhoto.setImageResource(files.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return files.size();
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
