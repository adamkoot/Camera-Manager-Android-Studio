package com.kot.apps.androidcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        getWiersz();
        addDict();

    }

    public void getWiersz (){
        List<String> array = new ArrayList<>();


        File pic = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );

        File[] files = pic.listFiles(); // tablica plików

        Log.d("xx", String.valueOf(files));

        for (File file : pic.listFiles()){
            array.add(file.getName());
        }


        ListView Lista = findViewById(R.id.lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AlbumActivity.this,       // tzw Context
                R.layout.wiersz,     // nazwa pliku xml naszego wiersza na liście
                R.id.tekst,                // id pola txt w wierszu
                array );                 // tablica przechowująca testowe dane

        Lista.setAdapter(adapter);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.d("############","index: " + array.get(index));

                Intent intent = new Intent(AlbumActivity.this, GalleryActivity.class);
                intent.putExtra("dict", array.get(index));
                startActivity(intent);
            }
        });

        Lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub



                AlertDialog.Builder alert = new AlertDialog.Builder(AlbumActivity.this);
                alert.setTitle("Ateention");
                alert.setMessage("Are you sure to delete this file?");
//ok
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        File pic = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
                        for (File file : pic.listFiles()){

                            if(file.getName().equals(array.get(pos)))
                            {
                                file.delete();
                                getWiersz();

                            }
                        }
                    }

                });

//no
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//
                alert.show();

                return true;
            }
        });
    }

    public void addDict(){
        ImageView Dodaj = findViewById(R.id.add);

        Dodaj.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(AlbumActivity.this);
                alert.setTitle("Attention! ");
                alert.setMessage("Please set name of new dict");

                //tutaj input
                EditText input = new EditText(AlbumActivity.this);
                input.setText("name");
                alert.setView(input);

                //teraz butony jak poprzednio i
                //ok
                alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("xxx", String.valueOf(input.getText()));
                        File pic = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
                        File dir = new File(pic, String.valueOf(input.getText()));
                        //Log.v("xxx", String.valueOf(pic.exists()));
                        dir.mkdir();
                        getWiersz ();
                    }

                });

                //no
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//
                alert.show();
            }
        });
    }

}