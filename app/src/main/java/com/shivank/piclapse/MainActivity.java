package com.shivank.piclapse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton fab_addImage;
    ArrayList<Uri> images;
    ArrayList<String> imgNames;


    GridView gridView;
    GridAdapter gridAdapter;

    ImageView addImg;

    Intent pickImgIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickImgIntent = new Intent();
        images = new ArrayList<Uri>();
        imgNames = new ArrayList<String>();

        //addImg = findViewById(R.id.addImg);
        gridView = findViewById(R.id.grid_view);
        fab_addImage = (FloatingActionButton) findViewById(R.id.fab_addImage);




        gridAdapter = new GridAdapter(MainActivity.this,images,imgNames);
        gridView.setAdapter(gridAdapter);


        fab_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickImgIntent.setType("image/*");
                pickImgIntent.setAction(Intent.ACTION_GET_CONTENT);
                pickImgIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(pickImgIntent,"Select Images"),1);


            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "Clicked On "+ imgNames.get(i), Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == 1 && data != null && data.getClipData() != null){
            int data1 = data.getClipData().getItemCount();
            //Uri uri = data.getData();

            //TODO: Fix Images Sequence Names
            for (int i = 0;i < data1; i++){
                images.add(data.getClipData().getItemAt(i).getUri());
                imgNames.add(i+1+"");
            }


            gridAdapter.notifyDataSetChanged();



        }

    }
}