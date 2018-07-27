package com.mutyaba.multiply;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class WrongAnswer extends DialogFragment {

    public interface WrongAnswerListener {
        void onWrongAnswerNeutralClick(DialogFragment dialog);
    }

    WrongAnswerListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (WrongAnswerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Wrong Answer Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.wrong_answer)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onWrongAnswerNeutralClick(WrongAnswer.this);
                    }
                });
        return builder.create();
    }
}
