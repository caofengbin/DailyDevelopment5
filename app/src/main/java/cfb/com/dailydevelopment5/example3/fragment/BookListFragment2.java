package cfb.com.dailydevelopment5.example3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment5.R;

/**
 * 展示书本title的Fragment
 * 这种直接使用ListFragment的方式创建列表非常值得借鉴推广使用
 * Created by fengbincao on 2017/8/6.
 */

public class BookListFragment2 extends ListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] bookTitles = getResources().getStringArray(R.array.bookTitles);
        ArrayAdapter<String> bookTitlesAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, bookTitles);

        setListAdapter(bookTitlesAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Access the Activity and cast to the interface
        OnSelectedBookChangeListener listener =
                (OnSelectedBookChangeListener)
                        getActivity();

        // Notify the Activity of the selection
        listener.onSelectedBookChanged(position);
    }
}
