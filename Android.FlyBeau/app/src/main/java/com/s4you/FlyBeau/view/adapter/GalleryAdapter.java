package com.s4you.FlyBeau.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4you.FlyBeau.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NamNgo on 25/04/2016.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

    Context context;
    ArrayList<String> lstURL;

    public GalleryAdapter(Context context, ArrayList<String> lstURL) {
        this.context = context;
        this.lstURL = lstURL;
    }


    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_android.setText("Hình: " + position);

        Picasso.with(context).load(lstURL.get(position)).resize(120, 60).into(holder.img_android);
    }

    @Override
    public int getItemCount() {
        return lstURL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}//END
