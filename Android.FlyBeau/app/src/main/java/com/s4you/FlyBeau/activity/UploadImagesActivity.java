package com.s4you.FlyBeau.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.view.adapter.SpinnerAdapter;

import java.io.IOException;

public class UploadImagesActivity extends AppCompatActivity {

    int WidthSreen, HeightSreen;
    //tool bar
    Toolbar toolbar;
    TextView tvTitle;
    Spinner sp_competition;
    SpinnerAdapter adapter;
    LinearLayout location;

    String[] names = {"Insect", "Flower","Animal"};
    String[] icons = {"http://www.entsoc.org/sites/default/files/images/fdn_logo.jpg",
                        "http://www.trbimg.com/img-51afae9a/turbine/os-os-ocoee-logo-jpg-20130605/600/553x600",
                        "http://www.youthedesigner.com/wp-content/uploads/2009/10/Animal-Logo-Design-21.png"};

    Button bt_next;
    ImageView iv;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimages);

        iv = (ImageView) findViewById(R.id.iv_upload_images);
        bt_next = (Button) findViewById(R.id.bt_next_upload);
        sp_competition = (Spinner) findViewById(R.id.spinner_Competition);
        location = (LinearLayout) findViewById(R.id.updateLocation);

        tvTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new SpinnerAdapter(UploadImagesActivity.this,names,icons);
        sp_competition.setAdapter(adapter);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UploadImagesActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser = Intent.createChooser(pick, "Chọn");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pho});
                startActivityForResult(chosser, 999);
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        WidthSreen = size.x;
        HeightSreen = size.y;

        Bundle bundle = getIntent().getExtras();


        if(bundle.get("data")!=null) {
            bitmap = (Bitmap) bundle.get("data");
            iv.setImageBitmap(bitmap);
        }
        else{
            try {
                Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(bundle.getString("image")));
                //Toast.makeText(this,"Abc: " + bundle.get("image"),Toast.LENGTH_SHORT).show();
                //Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1,(int)(bitmap1.getWidth()*0.8),), true);
                Bitmap bitmap2 = resize(bitmap1,bitmap1.getWidth(),bitmap1.getHeight());
                iv.setImageBitmap(bitmap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadImagesActivity.this,"Uploaded",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Bitmap resize(Bitmap b, int maxWidth, int maxHeight) {
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, b.getWidth(), b.getHeight()), new RectF(0, 0, 1000, 1000), Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                Bundle extras = data.getExtras();
                Bitmap c = (Bitmap) extras.get("data");
                iv.setImageBitmap(c);
            } else {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap bitmap2 = resize(bitmap1,bitmap1.getWidth(),bitmap1.getHeight());
                    iv.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
