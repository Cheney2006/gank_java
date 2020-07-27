package com.cheney.gankjava.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cheney.gankjava.R;

public class ProgressFragment extends DialogFragment {

    public static final String TAG = ProgressFragment.class.getSimpleName();

    private String message;

    private boolean cancelable;

    public ProgressFragment(String message, boolean cancelable) {
        this.message = message;
        this.cancelable = cancelable;
    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(requireContext());

        return progressDialog;
    }

    private class ProgressDialog extends Dialog {

        private TextView messageTv;

        public ProgressDialog(@NonNull Context context) {
            super(context, R.style.CustomDialog);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_progress);
            getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);
        }

        private void initView() {
            messageTv = findViewById(R.id.message_tv);
            setCancelable(cancelable);
            if (message != null) {
                messageTv.setText(message);
            }
        }


    }
}
