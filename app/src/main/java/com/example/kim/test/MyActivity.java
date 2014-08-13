package com.example.kim.test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import android.provider.MediaStore.Images.Media;

/**
 * Updated by Jeremy on 06/08/2014.
 */
public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

/*        img_logo = (ImageView)findViewById(R.id.imageView);

        button1= (Button)findViewById(R.id.camera);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callCamera();
            }
        });
        
        button2= (Button)findViewById(R.id.gallery);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callGallery();
            }
        });*/

    }
}
