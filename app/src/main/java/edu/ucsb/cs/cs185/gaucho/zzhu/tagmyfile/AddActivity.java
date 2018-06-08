package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
