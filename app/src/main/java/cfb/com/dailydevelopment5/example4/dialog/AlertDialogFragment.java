package cfb.com.dailydevelopment5.example4.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import cfb.com.dailydevelopment5.R;

/**
 * 通过对Dialog进行包装的DialogFragment
 * Created by fengbincao on 2017/8/6.
 */

public class AlertDialogFragment extends DialogFragment
        implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the Builder for the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the AlertDialog options
        builder.setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_message)
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setPositiveButton(R.string.text_yes, this)
                .setNegativeButton(R.string.text_no, this);

        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        MyDialogFragment.OnButtonClickListener parentActivity =
                (MyDialogFragment.OnButtonClickListener) getActivity();
        parentActivity.onButtonClick(which);

        dialog.dismiss();
    }
}
