package com.example.kim.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kim on 8/8/2014.
 */
public class Camera extends ReceiptInput{
    public void callCamera() {
        pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pictureActionIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("data")) {

                // retrieve the bitmap from the intent
                bitmap = (Bitmap) data.getExtras().get("data");


                Cursor cursor = getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                new String[]{
                                        MediaStore.Images.Media.DATA,
                                        MediaStore.Images.Media.DATE_ADDED,
                                        MediaStore.Images.ImageColumns.ORIENTATION},
                                MediaStore.Images.Media.DATE_ADDED, null, "date_added ASC"
                        );
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        Uri uri = Uri.parse(cursor.getString(cursor
                                .getColumnIndex(MediaStore.Images.Media.DATA)));
                        selectedImagePath = uri.toString();
                    } while (cursor.moveToNext());
                    cursor.close();
                }

                Log.e("path of the image from camera ====> ",
                        selectedImagePath);
                // update the image view with the bitmap
                img_logo.setImageBitmap(bitmap);
            } else if (data.getExtras() == null) {

                Toast.makeText(getApplicationContext(),
                        "No extras to retrieve!", Toast.LENGTH_SHORT)
                        .show();

                BitmapDrawable thumbnail = new BitmapDrawable(
                        getResources(), data.getData().getPath());

                // update the image view with the newly created drawable
                img_logo.setImageDrawable(thumbnail);

            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Cancelled",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
