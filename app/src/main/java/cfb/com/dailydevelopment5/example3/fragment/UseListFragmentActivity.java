package cfb.com.dailydevelopment5.example3.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cfb.com.dailydevelopment5.R;

public class UseListFragmentActivity extends AppCompatActivity implements OnSelectedBookChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list_fragment);
    }

    @Override
    public void onSelectedBookChanged(int bookIndex) {
        // 选中特定书本后的监听事件
        FragmentManager fragmentManager = getFragmentManager();

        // Get the book description fragment
        BookDescFragment bookDescFragment = (BookDescFragment)
                fragmentManager.findFragmentById(R.id.fragmentDescription);

        // Check validity of fragment reference
        bookDescFragment.setBook(bookIndex);
    }
}
