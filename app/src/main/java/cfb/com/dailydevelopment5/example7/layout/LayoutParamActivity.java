package cfb.com.dailydevelopment5.example7.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import cfb.com.dailydevelopment5.R;

public class LayoutParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_param);

        RelativeLayout rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        View textView = getLayoutInflater().inflate(R.layout.text_view_layout, rl_main, false);
        rl_main.addView(textView);
    }
}
