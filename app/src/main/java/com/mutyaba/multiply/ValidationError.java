package com.mutyaba.multiply;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ValidationError extends DialogFragment {

    public interface ValidationErrorListener {
        void onValidateErrorNeutralClick(DialogFragment dialog);
    }

    ValidationErrorListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (ValidationErrorListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Validation Error Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.validate_error)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onValidateErrorNeutralClick(ValidationError.this);
                    }
                });
        return builder.create();
    }
}
