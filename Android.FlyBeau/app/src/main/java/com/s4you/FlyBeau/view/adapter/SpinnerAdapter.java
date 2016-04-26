package com.s4you.FlyBeau.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4you.FlyBeau.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Zanty on 25/04/2016.
 */
public class SpinnerAdapter extends BaseAdapter {
    Context context;
    String[] names ;
    String[] icons;

    public SpinnerAdapter(Context context, String[] names, String[] icons) {
        this.context = context;
        this.names = names;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View_Item view_item;
        LayoutInflater inflater = (LayoutInflater) this.context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            view_item = new View_Item();
            convertView = inflater.inflate(R.layout.custom_spinner,parent,false);
            view_item.textview = (TextView) convertView.findViewById(R.id.tv_custom_spinner);
            view_item.imageview = (ImageView) convertView.findViewById(R.id.iv_custom_spinner);
            convertView.setTag(view_item);
        }
        else
            view_item = (View_Item) convertView.getTag();
        view_item.textview.setText(names[position]);
        Picasso.with(context)
                .load(icons[position])
                .error(R.drawable.ic_perm_identity_black_24dp)
                .into(view_item.imageview);
        return convertView;
    }

    public static class View_Item
    {
        public ImageView imageview;
        public TextView textview;
    }

}
