package com.kot.apps.androidcamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        AlertDialog.Builder alert = new AlertDialog.Builder(CameraActivity.this);
        alert.setTitle("Uwaga!");
        alert.setMessage("komunikat");
//ok
        alert.setPositiveButton("camera", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//jeśli jest dostępny aparat
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 200); // 200 - stała wartość, która później posłuży do identyfikacji tej akcji
                }
            }

        });

//no
        alert.setNegativeButton("galeria", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 201); // 201 - stała wartość, która później posłuży do identyfikacji tej akcji
            }
        });
//
        alert.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                ImageView podglad = findViewById(R.id.podglad);

                Bundle extras = data.getExtras();
                Bitmap b = (Bitmap) extras.get("data");
                podglad.setImageBitmap(b);

                AlertDialog.Builder alert = new AlertDialog.Builder(CameraActivity.this);
                alert.setTitle("Uwaga!");
//nie może mieć setMessage!!!
                String[] opcje = {"tanie","średnie","drogie"};
                alert.setItems(opcje, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if("tanie"==opcje[which])
                        {
                            Log.d("D", "tanie");
                        }
                    }
                });
//
                alert.show();

            }
        }
        else if (requestCode == 201) {
            if (resultCode == RESULT_OK) {

                AlertDialog.Builder alert = new AlertDialog.Builder(CameraActivity.this);
                alert.setTitle("Uwaga!");
                alert.setMessage("komunikat");
//ok
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ImageView podglad = findViewById(R.id.podglad);

                        Bundle extras = data.getExtras();
                        Bitmap b = (Bitmap) extras.get("data");
                        podglad.setImageBitmap(b);
                    }

                });

//no
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();

            }
        }
    }
}