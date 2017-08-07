package cfb.com.dailydevelopment5.example4.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cfb.com.dailydevelopment5.R;

/**
 * 测试使用 Dialog Fragment
 */
public class DialogFragmentActivity extends Activity implements MyDialogFragment.OnButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        Button button = (Button) findViewById(R.id.btnRegularDialogFragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegularDialogFragment();
            }
        });

        Button button2 = (Button) findViewById(R.id.btnAlertDialogAsFragment);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAlertDialogAsFragment();
            }
        });
    }

    void handleRegularDialogFragment() {
        MyDialogFragment theDialog = new MyDialogFragment();
        theDialog.show(getFragmentManager(), null);
    }

    void handleAlertDialogAsFragment() {
        AlertDialogFragment theDialog = new AlertDialogFragment();
        theDialog.show(getFragmentManager(), null);
    }

    @Override
    public void onButtonClick(int buttonId) {
        String message = "Unknown selection";

        switch (buttonId) {
            case R.id.btnYes:
                message = "第一个DialogFragment你选择了: " + getString(R.string.text_yes);
                break;
            case R.id.btnNo:
                message = "第一个DialogFragment你选择了: " + getString(R.string.text_no);
                break;
            case DialogInterface.BUTTON_POSITIVE:
                message = "On the Alert dialog Fragment you selected: " + getString(R.string.text_yes);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                message = "On the Alert dialog Fragment you selected: " + getString(R.string.text_no);
                break;
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
