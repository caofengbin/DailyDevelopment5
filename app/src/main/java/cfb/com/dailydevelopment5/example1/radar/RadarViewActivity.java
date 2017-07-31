package cfb.com.dailydevelopment5.example1.radar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cfb.com.dailydevelopment5.R;

public class RadarViewActivity extends AppCompatActivity {

    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_view);

        mRadarView = (RadarView) findViewById(R.id.radarView);
        mRadarView.setEmptyHint("无数据");

        List<Integer> layerColor = new ArrayList<>();
        Collections.addAll(layerColor, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT,
                Color.TRANSPARENT, Color.TRANSPARENT);
        mRadarView.setLayerColor(layerColor);

        List<String> vertexText = new ArrayList<>();
        Collections.addAll(vertexText, "营业收入", "毛利率", "净利润", "每股净资产", "行业平均值");
        mRadarView.setVertexText(vertexText);

        List<Float> values = new ArrayList<>();
        Collections.addAll(values, 3f, 6f, 2f, 7f, 5f);
        RadarData data = new RadarData(values, Color.parseColor("#ED7D31"));

        mRadarView.addRadarData(data);

        List<Float> values2 = new ArrayList<>();
        Collections.addAll(values2, 7f, 1f, 4f, 2f, 8f);
        RadarData data2 = new RadarData(values2, Color.parseColor("#5B9BD5"));
        mRadarView.addRadarData(data2);
    }

    // 在界面的右上角生成一个菜单的方法，非常的实用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 右上角菜单的点击监听事件的实现
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggle_rotation:
                mRadarView.setRotationEnable(!mRadarView.isRotationEnable());
                break;
            case R.id.anime_value:
                mRadarView.animateValue(2000);
                break;
            case R.id.change_layer:
                int randomInt = new Random().nextInt(6) + 1;
                mRadarView.setLayerNumbers(randomInt);
                break;
            case R.id.change_web_mode:
                if (mRadarView.getRadarEdgeMode() == RadarView.EDGE_MODE_POLYGON) {
                    mRadarView.setRadarEdgeMode(RadarView.EDGE_MODE_CIRCLE);
                } else {
                    mRadarView.setRadarEdgeMode(RadarView.EDGE_MODE_POLYGON);
                }
                break;
            case R.id.toggle_line_enable:
                mRadarView.setRadarLineEnable(!mRadarView.isRadarLineEnable());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
