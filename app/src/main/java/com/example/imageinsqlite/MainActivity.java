package com.example.imageinsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView1;
    EditText editText;
    ImageView imageView;
    Button button;
    Database db;
    String namedb;
    Bitmap imagedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        textView1 = findViewById(R.id.enter);
        editText = findViewById(R.id.name);
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.button);
        db = new Database(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile);
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearray);
                byte[] img = bytearray.toByteArray();

                boolean insert = db.insertdata(name,img);

                if(insert == true){
                    Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data not Saved Successfully", Toast.LENGTH_SHORT).show();
                }

                imagedb = db.getImage(name);
                namedb = db.getname(name);

                imageView.setImageBitmap(imagedb);
                textView1.setText("The name entered by you is \n\n"+namedb);

            }
        });
    }
}