package cfb.com.dailydevelopment5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment5.example1.radar.RadarViewActivity;
import cfb.com.dailydevelopment5.example10.marquee.MarqueeViewActivity;
import cfb.com.dailydevelopment5.example2.touch.TouchActivity;
import cfb.com.dailydevelopment5.example3.fragment.UseListFragmentActivity;
import cfb.com.dailydevelopment5.example4.dialog.DialogFragmentActivity;
import cfb.com.dailydevelopment5.example5.drawtext.DrawTextActivity;
import cfb.com.dailydevelopment5.example6.mvp.LoginActivity;
import cfb.com.dailydevelopment5.example7.layout.LayoutParamActivity;
import cfb.com.dailydevelopment5.example8.drag.DragListViewActivity;
import cfb.com.dailydevelopment5.example9.okhttp.UseOkHttpActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] mainItems;
    private ListView mMainListView;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainItems = getResources().getStringArray(R.array.main_view_string_array);
        mMainListView = (ListView) findViewById(R.id.main_activity_listView);
        itemAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mainItems);

        mMainListView.setAdapter(itemAdapter);
        mMainListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                // 雷达图绘制的效果
                startIntent(RadarViewActivity.class);
                break;
            case 1:
                // touch事件分发机制详解
                startIntent(TouchActivity.class);
                break;
            case 2:
                // 使用ListFragment
                startIntent(UseListFragmentActivity.class);
                break;
            case 3:
                // 使用DialogFragment
                startIntent(DialogFragmentActivity.class);
                break;
            case 4:
                // 尝试使用DrawText方法
                startIntent(DrawTextActivity.class);
                break;
            case 5:
                // LayoutParam参数的各种使用方式
                startIntent(LayoutParamActivity.class);
                break;
            case 6:
                // MVP模式简单示例
                startIntent(LoginActivity.class);
                break;
            case 7:
                // 拖拽滑动ListView简单实现
                startIntent(DragListViewActivity.class);
                break;
            case 8:
                // OkHttp网络框架的基本使用方式
                startIntent(UseOkHttpActivity.class);
                break;
            case 9:
                // 跑马灯效果实现
                startIntent(MarqueeViewActivity.class);
                break;
            case 10:

                break;
            case 11:

                break;
        }
    }

    private void startIntent(Class class1) {
        Intent intent = new Intent(MainActivity.this, class1);
        startActivity(intent);
    }
}
