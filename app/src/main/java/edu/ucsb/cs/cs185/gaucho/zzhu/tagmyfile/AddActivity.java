package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class AddActivity extends AppCompatActivity {
    HashSet<String> tagSet = new HashSet<>();
    String fileUri;
    Button tagButton;
    Button fileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tagButton = (Button) findViewById(R.id.add_tag_button);
        fileButton = (Button) findViewById(R.id.file_button);


        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Add New Tag...");
                // Set up the input
                final EditText input = new EditText(context);
                builder.setView(input);
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagSet.add(input.getText().toString().toLowerCase().replaceAll("\\s+",""));

                        LinearLayout listList = findViewById(R.id.baseLayout);
                        if(listList.getChildCount()>0)listList.removeAllViews();
                        ArrayList<String> myTags = new ArrayList<>(tagSet);
                        Collections.sort(myTags.subList(0, myTags.size()));
                        int maxWidth = getWindowManager().getDefaultDisplay().getWidth() - 600;
                        int curWidth = 0;
                        LinearLayout tagList = new LinearLayout(context);
                        tagList.setOrientation(LinearLayout.HORIZONTAL);
                        tagList.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        for(String i: myTags) {

                            final Button tmpButton = new Button(context);
                            Log.d("ButtonText", i);
                            tmpButton.setBackground(getDrawable(R.drawable.tagbutton));
                            tmpButton.setText(i);
                            tmpButton.setTextColor(0xFFFFFFFF);
                            tmpButton.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));
                            LinearLayout LL = new LinearLayout(context);
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
                                Log.d("listsize", ""+tagList.getChildCount());
                                tagList.addView(LL);
                            }
                            else {
                                Log.d("listwidth", ""+curWidth);
                                Log.d("listsize", ""+tagList.getChildCount());
                                listList.addView(tagList);
                                Log.d("listwidth", ""+curWidth);
                                Log.d("listsize", ""+tagList.getChildCount());
                                curWidth = tmpButton.getMeasuredWidth();
                                tagList = new LinearLayout(context);
                                tagList.setOrientation(LinearLayout.HORIZONTAL);
                                tagList.setLayoutParams(new ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                tagList.addView(LL);
                                Log.d("listwidth", ""+curWidth);
                                Log.d("listsize", ""+tagList.getChildCount());
                            }
                        }

                        listList.addView(tagList);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
        fileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileUri==null) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(Intent.createChooser(intent, "Please Select File"), 1);
                }
                else {
                    EditText title = (EditText) findViewById(R.id.editText);
                    EditText desc = (EditText) findViewById(R.id.editText2);
                    String titleStr = title.getText().toString();
                    String descStr = desc.getText().toString();
                    MyFile newFile = new MyFile(titleStr, descStr, fileUri, tagSet, R.drawable.ic_insert_drive_file_black_24dp);
                    AppDatabase.getAppDatabase(context).Dao().insertFile(newFile);
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            fileUri = uri.toString();
        }
        if(fileUri != null)fileButton.setText("Add");
    }

}
