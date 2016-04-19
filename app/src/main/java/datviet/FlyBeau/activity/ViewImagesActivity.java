package datviet.FlyBeau.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import datviet.FlyBeau.R;

public class ViewImagesActivity extends Activity {

    int WidthSreen, HeightSreen;

    Button bt_ok, bt_cancel;
    ImageView iv;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);

        iv = (ImageView) findViewById(R.id.iv_upload_images);
        bt_ok = (Button) findViewById(R.id.bt_ok_upload);
        bt_cancel = (Button) findViewById(R.id.bt_cancel_upload);

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
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewImagesActivity.this,"Uploaded",Toast.LENGTH_SHORT).show();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private static Bitmap resize(Bitmap b, int maxWidth, int maxHeight) {
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, b.getWidth(), b.getHeight()), new RectF(0, 0, 1000, 1000), Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }
}
