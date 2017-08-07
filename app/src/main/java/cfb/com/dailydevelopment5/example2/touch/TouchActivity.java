package cfb.com.dailydevelopment5.example2.touch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import cfb.com.dailydevelopment5.R;

public class TouchActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_DOWN");
                break;
//                Log.i(TAG, "[dispatchTouchEvent] -> ACTION_DOWN, return true");
//                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_MOVE");
                break;
//                Log.i(TAG, "[dispatchTouchEvent] -> ACTION_MOVE, return true");
//                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_CANCEL");
                break;
            default:
                break;
        }
        boolean superReturn = super.dispatchTouchEvent(ev);
        Log.i(TAG, "[dispatchTouchEvent] return super. = " + superReturn);
        return superReturn;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[onTouchEvent] -> ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[onTouchEvent] -> ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[onTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[onTouchEvent] -> ACTION_CANCEL");
                break;
            default:
                break;
        }
        boolean superReturn = super.onTouchEvent(event);
        Log.i(TAG, "[onTouchEvent] return super. = " + superReturn);
        return superReturn;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}

