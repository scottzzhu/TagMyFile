package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class InformActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        TextView title = (TextView)findViewById(R.id.title);
        TextView desc = findViewById(R.id.desc);
        TextView path = findViewById(R.id.path);
        ImageView infoPhoto = (ImageView)findViewById(R.id.info_photo);
        LinearLayout listList = findViewById(R.id.list_list);

        Intent intent = getIntent();
        MyFile message = intent.getExtras().getParcelable("file");

        title.setText(message.name);
        desc.setText(message.description);
        path.setText(message.path);
        infoPhoto.setImageResource(message.photoId);
        Log.d("infoPhoto", ""+message.photoId);

        ArrayList<String> myTags = new ArrayList<>(message.tagList);
        Collections.sort(myTags.subList(0, myTags.size()));
        int maxWidth = getWindowManager().getDefaultDisplay().getWidth() - 200;
        int curWidth = 0;
        LinearLayout tagList = new LinearLayout(this);
        tagList.setOrientation(LinearLayout.HORIZONTAL);
        tagList.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        for(String i: myTags) {

            final Button tmpButton = new Button(this);
            Log.d("ButtonText", i);
            tmpButton.setText(i);
            tmpButton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            tmpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity.class);
                    Button b = (Button) view;
                    String message = b.getText().toString();
                    intent.putExtra("search", message);
                    startActivity(intent);
                }
            });
            LinearLayout LL = new LinearLayout(this);
            LL.setOrientation(LinearLayout.HORIZONTAL);
            LL.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            LL.setLayoutParams(new ListView.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            );

            tmpButton.measure(0,0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tmpButton.getMeasuredWidth(),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //params.setMargins(5, 0, 5, 0);  // YOU CAN USE THIS
            //LL.addView(TV, params);
            LL.addView(tmpButton, params);
            LL.measure(0, 0);
            curWidth += tmpButton.getMeasuredWidth();
            Log.d("listwidth", ""+curWidth);
            if(curWidth < maxWidth) {
                tagList.addView(LL);
            }
            else {
                curWidth = tmpButton.getWidth();
                listList.addView(tagList);
                tagList = new LinearLayout(this);
                tagList.setOrientation(LinearLayout.HORIZONTAL);
                tagList.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                tagList.addView(LL);
            }
        }
        listList.addView(tagList);





    }
}
