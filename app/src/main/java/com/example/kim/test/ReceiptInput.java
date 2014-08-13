package com.example.kim.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Kim on 8/8/2014.
 */
public abstract class ReceiptInput extends Activity{
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    protected Button button1;
    protected Button button2;
    protected ImageView img_logo;

    protected Intent pictureActionIntent = null;
    protected Bitmap bitmap;

    protected String selectedImagePath;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {}
}
