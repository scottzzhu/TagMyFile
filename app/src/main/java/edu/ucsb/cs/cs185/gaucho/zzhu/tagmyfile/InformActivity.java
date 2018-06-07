package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        TextView title = (TextView)findViewById(R.id.title);
        TextView desc = findViewById(R.id.desc);
        TextView path = findViewById(R.id.path);
        ImageView infoPhoto = (ImageView)findViewById(R.id.info_photo);
        LinearLayout tagList = findViewById(R.id.tag_list);

        Intent intent = getIntent();
        MyFile message = intent.getExtras().getParcelable("file");
        title.setText(message.name);
        desc.setText(message.description);
        path.setText(message.path);
        infoPhoto.setImageResource(message.photoId);
        Log.d("infoPhoto", ""+message.photoId);





    }
}
