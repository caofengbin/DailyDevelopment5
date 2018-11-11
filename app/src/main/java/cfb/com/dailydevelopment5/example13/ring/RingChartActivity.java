package cfb.com.dailydevelopment5.example13.ring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cfb.com.dailydevelopment5.R;

public class RingChartActivity extends AppCompatActivity {

    int i;
    RingChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_chart);

        chart = (RingChart)findViewById(R.id.ringChart);
        chart.addItem("这是比较长的文字这是比较长的文字1", 55f, getResources().getColor(android.R.color.holo_red_light));
        chart.addItem("这是比较长的文字这是比较长的文字2",34f, getResources().getColor(android.R.color.holo_green_light));
        chart.addItem("这是比较长的文字这是比较长的文字3", 12f, getResources().getColor(android.R.color.holo_blue_light));
        chart.addItem("这个是0", 0f, getResources().getColor(android.R.color.holo_orange_dark));
        


    }
}
