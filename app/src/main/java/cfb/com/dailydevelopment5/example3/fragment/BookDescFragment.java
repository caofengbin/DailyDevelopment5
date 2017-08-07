package cfb.com.dailydevelopment5.example3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cfb.com.dailydevelopment5.R;

/**
 * 展示书本信息描述详情的Fragment
 * Created by fengbincao on 2017/8/6.
 */

public class BookDescFragment extends Fragment {

    String[] mBookDescriptions;
    TextView mBookDescriptionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 加载自定义的布局样式
        View viewHierarchy = inflater.inflate(R.layout.fragment_book_desc, container, false);

        // load array of book descriptions
        mBookDescriptions = getResources().getStringArray(R.array.bookDescriptions);
        // Get reference to book description text view
        mBookDescriptionTextView = (TextView)
                viewHierarchy.findViewById(R.id.bookDescription);

        return viewHierarchy;
    }

    /**
     * 外界调用，展示选中的内容
     * @param bookIndex         选中的序列
     */
    public void setBook(int bookIndex) {
        // Lookup the book description
        String bookDescription = mBookDescriptions[bookIndex];

        // Display it
        mBookDescriptionTextView.setText(bookDescription);
    }
}
