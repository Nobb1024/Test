package com.example.kim.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kim on 8/8/2014.
 */
public class Gallery extends ReceiptInput{
    public void callGallery() {
        if (bitmap != null)
        {
            bitmap.recycle();
        }
        pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
        pictureActionIntent.setType("image/*");
        pictureActionIntent.putExtra("return-data", true);
        startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                // our BitmapDrawable for the thumbnail
                BitmapDrawable bmpDrawable;
                // try to retrieve the image using the data from the intent
                Cursor cursor = getContentResolver().query(data.getData(),
                        null, null, null, null);
                if (cursor != null) {

                    cursor.moveToFirst();

                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    String fileSrc = cursor.getString(idx);
                    bitmap = BitmapFactory.decodeFile(fileSrc); // load
                    img_logo.setImageBitmap(bitmap);
                } else {

                    bmpDrawable = new BitmapDrawable(getResources(), data
                            .getData().getPath());
                    img_logo.setImageDrawable(bmpDrawable);
                }

            } else {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Cancelled",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
