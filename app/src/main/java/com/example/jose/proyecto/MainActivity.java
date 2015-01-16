package com.example.jose.proyecto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {
    private LinearLayout lyt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPhoto=(Button)findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogPhoto();
            }
        });
    }

    private int SELECT_IMAGE = 237487;
    private int TAKE_PICTURE = 829038;

    private void dialogPhoto(){
        try{
            final CharSequence[] items = {"Seleccionar de la galería", "Hacer una foto"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Seleccionar una foto");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent,2);
                            break;
                        case 1:
                            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1);
                            break;
                    }

                }
            });
        AlertDialog alert = builder.create();
        alert.show();
        } catch(Exception e){}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        TextView lblPhoto=(TextView)findViewById((R.id.lblPhoto));

        try{
            if (requestCode == 2)
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                   /* lblPhoto.setText(getPath(selectedImage));
                    imgPhoto.setImageURI(selectedImage);*/
                    InputStream is;
                    try {
                        is = getContentResolver().openInputStream(selectedImage);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        Bitmap bitmap = BitmapFactory.decodeStream(bis);
                        ImageView iv = (ImageView)findViewById(R.id.imgPhoto);
                        imgPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {}
                }
            if(requestCode == 1)
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    lblPhoto.setText(getPath(selectedImage));
                    imgPhoto.setImageURI(selectedImage);
                }
        } catch(Exception e){}
    }

    private String getPath(Uri uri) {
        String[] projection = { android.provider.MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
