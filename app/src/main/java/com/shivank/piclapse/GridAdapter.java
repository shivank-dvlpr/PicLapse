package com.shivank.piclapse;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<Uri> images;
    ArrayList<String> imgNames;

    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<Uri> images, ArrayList<String> imgNames){

        this.context = context;
        this.images = images;
        this.imgNames = imgNames;
    }



    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view== null){
            view = inflater.inflate(R.layout.grid_images,viewGroup,false);
        }

        ImageView img = view.findViewById(R.id.grid_img);
        TextView txt = view.findViewById(R.id.grid_txt);

        Glide.with(context)
                        .load(images.get(i))
                        .centerCrop()
                        .into(img);
        txt.setText(imgNames.get(i));


        return view;
    }
}
