package com.mutyaba.multiply;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class Validation extends DialogFragment {

    public interface ValidationListener {
        void onValidatePositiveClick(DialogFragment dialog);
        void onValidateNegativeClick(DialogFragment dialog);
    }

    ValidationListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (ValidationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Confirmation Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.validate)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onValidatePositiveClick(Validation.this);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onValidateNegativeClick(Validation.this);
                    }
                });
        return builder.create();
    }
}
