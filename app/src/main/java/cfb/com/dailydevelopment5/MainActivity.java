package cfb.com.dailydevelopment5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment5.example1.radar.RadarViewActivity;
import cfb.com.dailydevelopment5.example2.touch.TouchActivity;
import cfb.com.dailydevelopment5.example3.fragment.UseListFragmentActivity;
import cfb.com.dailydevelopment5.example4.dialog.DialogFragmentActivity;

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
                startIntent(RadarViewActivity.class);
                break;
            case 1:
                startIntent(TouchActivity.class);
                break;
            case 2:
                startIntent(UseListFragmentActivity.class);
                break;
            case 3:
                startIntent(DialogFragmentActivity.class);
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

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
