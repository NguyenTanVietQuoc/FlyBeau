package datviet.FlyBeau.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.IOException;

import datviet.FlyBeau.R;

/**
 * Created by Zanty on 14/04/2016.
 */
public class CircularFloating {
    Context c;
    public CircularFloating(Context c){
        this.c = c;
    }
    public void createCircularFloating(){
        final ImageView fabIconNew = new ImageView(c);
        fabIconNew.setImageDrawable(c.getResources().getDrawable(R.mipmap.ic_action_new_light));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder((Activity) c)
                .setContentView(fabIconNew)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder((Activity) c);
        ImageView rlIcon1 = new ImageView(c);
        ImageView rlIcon2 = new ImageView(c);
        ImageView rlIcon3 = new ImageView(c);

        rlIcon3.setImageDrawable(c.getResources().getDrawable(R.mipmap.ic_action_gotop));
        rlIcon2.setImageDrawable(c.getResources().getDrawable(R.mipmap.ic_action_camera));
        rlIcon1.setImageDrawable(c.getResources().getDrawable(R.mipmap.ic_action_gallery));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder((Activity) c)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .attachTo(rightLowerButton)
                .enableAnimations()
                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
        rlIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ((Activity) c).startActivityForResult(pho, 999);
            }
        });
        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                ((Activity) c).startActivityForResult(pick, 999);
            }
        });
        rlIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,"Click icon 1",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
