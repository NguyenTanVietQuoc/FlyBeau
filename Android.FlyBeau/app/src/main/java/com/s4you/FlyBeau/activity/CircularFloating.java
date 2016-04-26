package com.s4you.FlyBeau.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.utils.CircularFloatingActionMenu.FloatingActionButton;
import com.s4you.FlyBeau.utils.CircularFloatingActionMenu.FloatingActionMenu;
import com.s4you.FlyBeau.utils.CircularFloatingActionMenu.SubActionButton;

/**
 * Created by Zanty on 14/04/2016.
 */
public class CircularFloating {
    int Duration = 400;
    Context c;
    FloatingActionButton rightLowerButton;
    FloatingActionMenu rightLowerMenu;
    public CircularFloating(Context c){
        this.c = c;
    }
    public void createCircularFloating(){
        final ImageView fabIconNew = new ImageView(c);
        fabIconNew.setImageDrawable(c.getResources().getDrawable(R.mipmap.ic_action_new_light));
        rightLowerButton = new FloatingActionButton.Builder((Activity) c)
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
        rightLowerMenu = new FloatingActionMenu.Builder((Activity) c)
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
    public void setVisibleFloatingMenu(boolean b){
        if(b){
            //animationMenuOpen();
            rightLowerButton.setVisibility(View.VISIBLE);
        }
        else {
            //animationMenuClose();
            rightLowerMenu.close(true);
            rightLowerButton.setVisibility(View.GONE);
        }
    }
    public void animationMenuClose(){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X,0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y,0f);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, -180);
        PropertyValuesHolder pvhsX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0);
        PropertyValuesHolder pvhsY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0);
        PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 0);

        final ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(rightLowerButton, pvhX, pvhY, pvhR, pvhsX, pvhsY, pvhA);
        animation.setDuration(Duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());


        animation.setStartDelay(30);
        animation.start();
    }
    public void animationMenuOpen(){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X,0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y,0f);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 180);
        PropertyValuesHolder pvhsX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1);
        PropertyValuesHolder pvhsY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1);
        PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 1);

        final ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(rightLowerButton, pvhX, pvhY, pvhR, pvhsX, pvhsY, pvhA);
        animation.setDuration(Duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());


        animation.setStartDelay(30);
        animation.start();
    }
}
