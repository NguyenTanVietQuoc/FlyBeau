package com.s4you.FlyBeau.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.model.Home;
import com.s4you.FlyBeau.utils.lazylist.BitmapBorderTransformation;
import com.s4you.FlyBeau.utils.lazylist.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NamNgo on 12/04/2016.
 */
public class HomeAdpater extends BaseAdapter {

    Context context;
    private String[] data;
    private static LayoutInflater inflater = null;

    public com.s4you.FlyBeau.utils.lazylist.ImageLoader imageLoader;
    ArrayList<Home> lstHome;

    public HomeAdpater(Context context, ArrayList<Home> lstHome) {
        //activity = a;
        this.context = context;
        this.lstHome = lstHome;
        imageLoader = new ImageLoader(this.context.getApplicationContext());
    }

    class Holder {
        ImageView iv, avatar;
        TextView tvGroupName, tvFrom, tvDes, tvTime;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lstHome.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            inflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.imgHome);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.tvGroupName = (TextView) convertView.findViewById(R.id.tvGroupName);
            holder.tvFrom = (TextView) convertView.findViewById(R.id.tvFrom);
            holder.tvDes = (TextView) convertView.findViewById(R.id.tvDescrip);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvGroupName.setText(lstHome.get(position).name);
        holder.tvFrom.setText("from " + lstHome.get(position).group);
        holder.tvDes.setText(lstHome.get(position).descrip);
        holder.tvTime.setText(lstHome.get(position).time + " hours ago");
//        Picasso.with(context)
//                .load(lstHome.get(position).avatarSRC)
//                .transform(new RoundedTransformation(100, 0))
//                .fit().centerCrop()
//                .into(holder.avatar);
        Picasso.with(context)
                .load(lstHome.get(position).avatarSRC)
                .transform(new BitmapBorderTransformation(0, 100, Color.BLUE))
                .fit().centerCrop()
                .into(holder.avatar);
        //imageLoader.DisplayImage(lstVideo.get(position).url, holder.iv);
        holder.iv.setImageResource(lstHome.get(position).imageSRC);
        return convertView;
    }


}