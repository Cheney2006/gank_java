package com.cheney.gankjava.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cheney.gankjava.R;

public class ProgressFragment extends DialogFragment {

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
        }

        private void initView() {
            messageTv = findViewById(R.id.message_tv);
        }


    }
}
