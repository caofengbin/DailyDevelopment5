package cfb.com.dailydevelopment5.example12.picasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cfb.com.dailydevelopment5.R;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        ImageView mImageView = (ImageView) findViewById(R.id.main_image);
        Picasso.with(this)
                .load("http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg")
                .into(mImageView);
    }
}
