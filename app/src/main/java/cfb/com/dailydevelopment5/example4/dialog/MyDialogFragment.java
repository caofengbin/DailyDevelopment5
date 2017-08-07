package cfb.com.dailydevelopment5.example4.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cfb.com.dailydevelopment5.R;

/**
 * 使用 DialogFragment 的基本方法
 * Created by fengbincao on 2017/8/6.
 */

public class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    // Interface Activity implements for notification
    interface OnButtonClickListener {
        void onButtonClick(int buttonId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Style的方法必须放在 onCreate 中，否则后续调用的是无效的
        setStyle(DialogFragment.STYLE_NORMAL, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_my_dialog, container, false);

        View yesButton = theView.findViewById(R.id.btnYes);
        yesButton.setOnClickListener(this);
        yesButton.requestFocus();

        View noButton = theView.findViewById(R.id.btnNo);
        noButton.setOnClickListener(this);

        // 设置对话框相关的属性的方法
        Dialog dialog = getDialog();
        dialog.setTitle(getString(R.string.myDialogFragmentTitle));
        dialog.setCanceledOnTouchOutside(false);

        return theView;
    }

    @Override
    public void onClick(View view) {
        int buttonId = view.getId();

        // 将点击事件通知给主Activity
        OnButtonClickListener parentActivity = (OnButtonClickListener) getActivity();
        parentActivity.onButtonClick(buttonId);

        // Close the dialog fragment
        dismiss();
    }
}
