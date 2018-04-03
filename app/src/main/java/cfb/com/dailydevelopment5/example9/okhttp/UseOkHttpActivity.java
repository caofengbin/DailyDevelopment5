package cfb.com.dailydevelopment5.example9.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

import cfb.com.dailydevelopment5.R;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UseOkHttpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "UseOkHttpActivity";

    private String[] mainItems;
    private ListView mMainListView;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_ok_http);

        mainItems = getResources().getStringArray(R.array.ok_http_use_string_array);
        mMainListView = (ListView) findViewById(R.id.okhttp_activity_listView);
        itemAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mainItems);

        mMainListView.setAdapter(itemAdapter);
        mMainListView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                useOkHttpCache();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private void useOkHttpCache() {
        //缓存文件夹
        File cacheFile = new File(getExternalCacheDir().toString(), "cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        final Cache cache = new Cache(cacheFile, cacheSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .build();
                //官方的一个示例的url
                String url = "http://publicobject.com/helloworld.txt";

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call1 = client.newCall(request);
                Response response1 = null;
                try {
                    //第一次网络请求
                    response1 = call1.execute();
                    Log.i(TAG, "testCache: response1 :" + response1.body().string());
                    Log.i(TAG, "testCache: response1 cache :" + response1.cacheResponse());
                    Log.i(TAG, "testCache: response1 network :" + response1.networkResponse());
                    response1.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call call12 = client.newCall(request);

                try {
                    //第二次网络请求
                    Response response2 = call12.execute();
                    Log.i(TAG, "testCache: response2 :" + response2.body().string());
                    Log.i(TAG, "testCache: response2 cache :" + response2.cacheResponse());
                    Log.i(TAG, "testCache: response2 network :" + response2.networkResponse());
                    Log.i(TAG, "testCache: response1 equals response2:" + response2.equals(response1));
                    response2.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
