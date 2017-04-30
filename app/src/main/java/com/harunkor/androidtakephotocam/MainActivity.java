package com.harunkor.androidtakephotocam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=(ImageView) findViewById(R.id.imageView);

        Intent takepictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepictureIntent.resolveActivity(getPackageManager())!=null)
        {

            startActivityForResult(takepictureIntent,1);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==1 && resultCode==RESULT_OK)
       {


           Bundle extras= data.getExtras();
           Bitmap bitmap=(Bitmap) extras.get("data"); // resmi aldığı yer...


           mImageView.setImageBitmap(fixOrientation(bitmap));

       }


    }


    public Bitmap fixOrientation(Bitmap mBitmap) {
        if (mBitmap.getWidth() > mBitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            mBitmap = Bitmap.createBitmap(mBitmap , 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
            return mBitmap;
        }
        return null;
    }




}
