package com.s4you.FlyBeau.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.s4you.FlyBeau.R;

/**
 * Created by Zanty on 14/04/2016.
 */
public class SetOnLickListener implements View.OnClickListener {
    Context c;
    public interface OnLickListener{
        public void onClick(View v);
    }

    public SetOnLickListener(View v, Context c) {
        this.c = c;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_user:
                Toast.makeText(c,"Click user",Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_rating:
                Toast.makeText(c,"Click rating",Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_total_picture:
                Toast.makeText(c,"Click picture",Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_total_point:
                Toast.makeText(c,"Click point",Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_upload_images:
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser = Intent.createChooser(pick, "Chọn");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pho});
                ((Activity)c).startActivityForResult(chosser, 999);
                break;
            case R.id.layout_setting:
                Toast.makeText(c,"Click setting",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
