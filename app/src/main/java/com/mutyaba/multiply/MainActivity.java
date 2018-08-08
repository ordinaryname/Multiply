package com.mutyaba.multiply;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity implements Confirmation.ConfirmationListener, Validation.ValidationListener, ValidationError.ValidationErrorListener, WrongAnswer.WrongAnswerListener {

    public ImageButton deleteBtn, startOverBtn;
    public TextView calcView;
    public Button currentButton;
    public SparseArray<Button> intArray;
    public Boolean[] gridBools;
    public Boolean cViewSwitch;
    public int i1, i2;
    public Chronometer chronometer;

    private  View.OnClickListener btnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            NumberSelected(b);
        }
    };

    private View.OnClickListener calcOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cViewSwitch = true;
            v.setBackgroundColor(Color.parseColor("#eeeeee"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cViewSwitch = false;

        intArray = new SparseArray<>();
        gridBools = new Boolean[35];
        chronometer = findViewById(R.id.chronometerView);
        deleteBtn = findViewById(R.id.delete);
        startOverBtn = findViewById(R.id.startover);
        calcView = findViewById(R.id.calcView);

        calcView.setOnClickListener(calcOnClick);

        PutIntoIntArray();
        InitializeGrid();

        for(int i = 0; i < intArray.size(); i++) {
            intArray.valueAt(i).setOnClickListener(btnOnClick);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#cccccc"));
    }

    private void InitializeGrid() {
        SetGridBools();

        for(int i = 0; i < intArray.size(); i++) {
            String e1 = Integer.toString(ThreadLocalRandom.current().nextInt(1, 6));
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                intArray.valueAt(i).setText(e1);
            }
        }

        UpdateGrid();

        for(int i = 0; i < intArray.size(); i++) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                if(gridBools[i]) {
                    intArray.valueAt(i).setText("");
                    intArray.valueAt(i).setBackgroundColor(Color.parseColor("#eeeeee"));
                } else {
                    intArray.valueAt(i).setBackgroundColor(Color.WHITE);
                }
            }
        }
        chronometer.start();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    private void SetGridBools() {
        i1 = 0;
        i2 = 0;
        for(int i = 0; i < gridBools.length; i++) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                int j = ThreadLocalRandom.current().nextInt(0, 2);
                if (j == 0 && i1 < 11) {
                    gridBools[i] = false;
                    i1++;
                } else if (j != 0 && i2 < 12) {
                    gridBools[i] = true;
                    i2++;
                } else {
                    gridBools[i] = false;
                    i1++;
                }
            } else {
                gridBools[i] = false;
            }
        }
        while(i2 < 12) {
            AddBools();
        }
    }

    private void AddBools() {
        for(int i = 0; i < gridBools.length; i++) {
            if (i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                int j = ThreadLocalRandom.current().nextInt(0, 2);
                if (j != 0 && i2 < 12) {
                    gridBools[i] = true;
                    i2++;
                }
            }
        }
    }

    private void PutIntoIntArray() {
        intArray.append(R.id.b1_1, (Button)findViewById(R.id.b1_1)); intArray.append(R.id.b1_2, (Button)findViewById(R.id.b1_2)); intArray.append(R.id.b1_3, (Button)findViewById(R.id.b1_3)); intArray.append(R.id.b1_4, (Button)findViewById(R.id.b1_4));
        intArray.append(R.id.b1_5, (Button)findViewById(R.id.b1_5)); intArray.append(R.id.b1_6, (Button)findViewById(R.id.b1_6)); intArray.append(R.id.b2_1, (Button)findViewById(R.id.b2_1)); intArray.append(R.id.b2_2, (Button)findViewById(R.id.b2_2));
        intArray.append(R.id.b2_3, (Button)findViewById(R.id.b2_3)); intArray.append(R.id.b2_4, (Button)findViewById(R.id.b2_4)); intArray.append(R.id.b2_5, (Button)findViewById(R.id.b2_5)); intArray.append(R.id.b2_6, (Button)findViewById(R.id.b2_6));
        intArray.append(R.id.b3_1, (Button)findViewById(R.id.b3_1)); intArray.append(R.id.b3_2, (Button)findViewById(R.id.b3_2)); intArray.append(R.id.b3_3, (Button)findViewById(R.id.b3_3)); intArray.append(R.id.b3_4, (Button)findViewById(R.id.b3_4));
        intArray.append(R.id.b3_5, (Button)findViewById(R.id.b3_5)); intArray.append(R.id.b3_6, (Button)findViewById(R.id.b3_6)); intArray.append(R.id.b4_1, (Button)findViewById(R.id.b4_1)); intArray.append(R.id.b4_2, (Button)findViewById(R.id.b4_2));
        intArray.append(R.id.b4_3, (Button)findViewById(R.id.b4_3)); intArray.append(R.id.b4_4, (Button)findViewById(R.id.b4_4)); intArray.append(R.id.b4_5, (Button)findViewById(R.id.b4_5)); intArray.append(R.id.b4_6, (Button)findViewById(R.id.b4_6));
        intArray.append(R.id.b5_1, (Button)findViewById(R.id.b5_1)); intArray.append(R.id.b5_2, (Button)findViewById(R.id.b5_2)); intArray.append(R.id.b5_3, (Button)findViewById(R.id.b5_3)); intArray.append(R.id.b5_4, (Button)findViewById(R.id.b5_4));
        intArray.append(R.id.b5_5, (Button)findViewById(R.id.b5_5)); intArray.append(R.id.b5_6, (Button)findViewById(R.id.b5_6)); intArray.append(R.id.b6_1, (Button)findViewById(R.id.b6_1)); intArray.append(R.id.b6_2, (Button)findViewById(R.id.b6_2));
        intArray.append(R.id.b6_3, (Button)findViewById(R.id.b6_3)); intArray.append(R.id.b6_4, (Button)findViewById(R.id.b6_4)); intArray.append(R.id.b6_5, (Button)findViewById(R.id.b6_5));
    }

    private void NumberSelected(Button h) {
        for(int i = 0; i < intArray.size(); i++) {
            if(gridBools[i]) {
                intArray.valueAt(i).setBackgroundColor(Color.parseColor("#eeeeee"));
            } else {
                intArray.valueAt(i).setBackgroundColor(Color.WHITE);
            }
        }

        if(cViewSwitch && !h.getText().toString().equals("")) {
            float a;
            h.setBackgroundColor(Color.parseColor("#ddeeee"));
            String num = calcView.getText().toString();
            if(num.equals("")) {
                calcView.setText(h.getText().toString());
            } else if(num.substring(num.length() - 1).equals("*")){
                a = (Float.valueOf(num.substring(0, num.length() - 1)) * Integer.parseInt(h.getText().toString()));
                calcView.setText(Float.toString(a));
            } else if(num.substring(num.length() - 1).equals("/")){
                a = (Float.valueOf(num.substring(0, num.length() - 1)) / Integer.parseInt(h.getText().toString()));
                calcView.setText(Float.toString(a));
            }
        } else {
            h.setBackgroundColor(Color.parseColor("#dddddd"));
        }
        currentButton = h;
    }

    public void N1(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("1");
            }
        }
    }

    public void N2(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("2");
            }
        }
    }

    public void N3(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("3");
            }
        }
    }

    public void N4(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("4");
            }
        }
    }

    public void N5(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("5");
            }
        }
    }

    public void UpdateGrid() {
        int[] a = new int[10];
        a[0] =  Integer.parseInt(intArray.valueAt(0).getText().toString()) * Integer.parseInt(intArray.valueAt(1).getText().toString()) * Integer.parseInt(intArray.valueAt(2).getText().toString()) * Integer.parseInt(intArray.valueAt(3).getText().toString()) * Integer.parseInt(intArray.valueAt(4).getText().toString());
        a[1] =  Integer.parseInt(intArray.valueAt(6).getText().toString()) * Integer.parseInt(intArray.valueAt(7).getText().toString()) * Integer.parseInt(intArray.valueAt(8).getText().toString()) * Integer.parseInt(intArray.valueAt(9).getText().toString()) * Integer.parseInt(intArray.valueAt(10).getText().toString());
        a[2] =  Integer.parseInt(intArray.valueAt(12).getText().toString()) * Integer.parseInt(intArray.valueAt(13).getText().toString()) * Integer.parseInt(intArray.valueAt(14).getText().toString()) * Integer.parseInt(intArray.valueAt(15).getText().toString()) * Integer.parseInt(intArray.valueAt(16).getText().toString());
        a[3] =  Integer.parseInt(intArray.valueAt(18).getText().toString()) * Integer.parseInt(intArray.valueAt(19).getText().toString()) * Integer.parseInt(intArray.valueAt(20).getText().toString()) * Integer.parseInt(intArray.valueAt(21).getText().toString()) * Integer.parseInt(intArray.valueAt(22).getText().toString());
        a[4] =  Integer.parseInt(intArray.valueAt(24).getText().toString()) * Integer.parseInt(intArray.valueAt(25).getText().toString()) * Integer.parseInt(intArray.valueAt(26).getText().toString()) * Integer.parseInt(intArray.valueAt(27).getText().toString()) * Integer.parseInt(intArray.valueAt(28).getText().toString());
        intArray.valueAt(5).setText(Integer.toString(a[0]));
        intArray.valueAt(11).setText(Integer.toString(a[1]));
        intArray.valueAt(17).setText(Integer.toString(a[2]));
        intArray.valueAt(23).setText(Integer.toString(a[3]));
        intArray.valueAt(29).setText(Integer.toString(a[4]));

        a[5] =  Integer.parseInt(intArray.valueAt(0).getText().toString()) * Integer.parseInt(intArray.valueAt(6).getText().toString()) * Integer.parseInt(intArray.valueAt(12).getText().toString()) * Integer.parseInt(intArray.valueAt(18).getText().toString()) * Integer.parseInt(intArray.valueAt(24).getText().toString());
        a[6] =  Integer.parseInt(intArray.valueAt(1).getText().toString()) * Integer.parseInt(intArray.valueAt(7).getText().toString()) * Integer.parseInt(intArray.valueAt(13).getText().toString()) * Integer.parseInt(intArray.valueAt(19).getText().toString()) * Integer.parseInt(intArray.valueAt(25).getText().toString());
        a[7] =  Integer.parseInt(intArray.valueAt(2).getText().toString()) * Integer.parseInt(intArray.valueAt(8).getText().toString()) * Integer.parseInt(intArray.valueAt(14).getText().toString()) * Integer.parseInt(intArray.valueAt(20).getText().toString()) * Integer.parseInt(intArray.valueAt(26).getText().toString());
        a[8] =  Integer.parseInt(intArray.valueAt(3).getText().toString()) * Integer.parseInt(intArray.valueAt(9).getText().toString()) * Integer.parseInt(intArray.valueAt(15).getText().toString()) * Integer.parseInt(intArray.valueAt(21).getText().toString()) * Integer.parseInt(intArray.valueAt(27).getText().toString());
        a[9] =  Integer.parseInt(intArray.valueAt(4).getText().toString()) * Integer.parseInt(intArray.valueAt(10).getText().toString()) * Integer.parseInt(intArray.valueAt(16).getText().toString()) * Integer.parseInt(intArray.valueAt(22).getText().toString()) * Integer.parseInt(intArray.valueAt(28).getText().toString());
        intArray.valueAt(30).setText(Integer.toString(a[5]));
        intArray.valueAt(31).setText(Integer.toString(a[6]));
        intArray.valueAt(32).setText(Integer.toString(a[7]));
        intArray.valueAt(33).setText(Integer.toString(a[8]));
        intArray.valueAt(34).setText(Integer.toString(a[9]));
    }

    public void Delete(View v) {
        int i = intArray.indexOfValue(currentButton);
        if(gridBools[intArray.indexOfValue(currentButton)]) {
            if(i != 5 && i != 11 && i != 17 && i != 23 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33 && i != 34) {
                currentButton.setText("");
            }
        }
    }

    public void StartOver(View v) {
        DialogFragment dialog = new Confirmation();
        dialog.show(getFragmentManager(), "ConfirmationFragment");
    }

    public void Multiply(View v) {
        if(cViewSwitch) {
            String num = calcView.getText().toString();
            if(!num.equals("")) {
                if (!num.substring(num.length() - 1).equals("*") || !num.substring(num.length() - 1).equals("/")) {
                    num = num + "*";
                    calcView.setText(num);
                }
            }
        }
    }

    public void Divide(View v) {
        if(cViewSwitch) {
            String num = calcView.getText().toString();
            if(!num.equals("")) {
                if (!num.substring(num.length() - 1).equals("*") || !num.substring(num.length() - 1).equals("/")) {
                    num = num + "/";
                    calcView.setText(num);
                }
            }
        }
    }

    public void Clear(View v) {
        calcView.setText("");
    }

    public void CheckGrid(View v) {
        if(IsGridFull()) {
            DialogFragment dialog = new Validation();
            dialog.show(getFragmentManager(), "ValidationFragment");
        } else {
            DialogFragment dialog = new ValidationError();
            dialog.show(getFragmentManager(), "ValidationErrorFragment");
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        InitializeGrid();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    @Override
    public void onValidatePositiveClick(DialogFragment dialog) {
        int[] i = new int[10];
        i[0] = Integer.parseInt(intArray.valueAt(5).getText().toString());
        i[1] = Integer.parseInt(intArray.valueAt(11).getText().toString());
        i[2] = Integer.parseInt(intArray.valueAt(17).getText().toString());
        i[3] = Integer.parseInt(intArray.valueAt(23).getText().toString());
        i[4] = Integer.parseInt(intArray.valueAt(29).getText().toString());
        i[5] = Integer.parseInt(intArray.valueAt(30).getText().toString());
        i[6] = Integer.parseInt(intArray.valueAt(31).getText().toString());
        i[7] = Integer.parseInt(intArray.valueAt(32).getText().toString());
        i[8] = Integer.parseInt(intArray.valueAt(33).getText().toString());
        i[9] = Integer.parseInt(intArray.valueAt(34).getText().toString());

        int[] a = new int[10];
        a[0] =  Integer.parseInt(intArray.valueAt(0).getText().toString()) * Integer.parseInt(intArray.valueAt(1).getText().toString()) * Integer.parseInt(intArray.valueAt(2).getText().toString()) * Integer.parseInt(intArray.valueAt(3).getText().toString()) * Integer.parseInt(intArray.valueAt(4).getText().toString());
        a[1] =  Integer.parseInt(intArray.valueAt(6).getText().toString()) * Integer.parseInt(intArray.valueAt(7).getText().toString()) * Integer.parseInt(intArray.valueAt(8).getText().toString()) * Integer.parseInt(intArray.valueAt(9).getText().toString()) * Integer.parseInt(intArray.valueAt(10).getText().toString());
        a[2] =  Integer.parseInt(intArray.valueAt(12).getText().toString()) * Integer.parseInt(intArray.valueAt(13).getText().toString()) * Integer.parseInt(intArray.valueAt(14).getText().toString()) * Integer.parseInt(intArray.valueAt(15).getText().toString()) * Integer.parseInt(intArray.valueAt(16).getText().toString());
        a[3] =  Integer.parseInt(intArray.valueAt(18).getText().toString()) * Integer.parseInt(intArray.valueAt(19).getText().toString()) * Integer.parseInt(intArray.valueAt(20).getText().toString()) * Integer.parseInt(intArray.valueAt(21).getText().toString()) * Integer.parseInt(intArray.valueAt(22).getText().toString());
        a[4] =  Integer.parseInt(intArray.valueAt(24).getText().toString()) * Integer.parseInt(intArray.valueAt(25).getText().toString()) * Integer.parseInt(intArray.valueAt(26).getText().toString()) * Integer.parseInt(intArray.valueAt(27).getText().toString()) * Integer.parseInt(intArray.valueAt(28).getText().toString());
        a[5] =  Integer.parseInt(intArray.valueAt(0).getText().toString()) * Integer.parseInt(intArray.valueAt(6).getText().toString()) * Integer.parseInt(intArray.valueAt(12).getText().toString()) * Integer.parseInt(intArray.valueAt(18).getText().toString()) * Integer.parseInt(intArray.valueAt(24).getText().toString());
        a[6] =  Integer.parseInt(intArray.valueAt(1).getText().toString()) * Integer.parseInt(intArray.valueAt(7).getText().toString()) * Integer.parseInt(intArray.valueAt(13).getText().toString()) * Integer.parseInt(intArray.valueAt(19).getText().toString()) * Integer.parseInt(intArray.valueAt(25).getText().toString());
        a[7] =  Integer.parseInt(intArray.valueAt(2).getText().toString()) * Integer.parseInt(intArray.valueAt(8).getText().toString()) * Integer.parseInt(intArray.valueAt(14).getText().toString()) * Integer.parseInt(intArray.valueAt(20).getText().toString()) * Integer.parseInt(intArray.valueAt(26).getText().toString());
        a[8] =  Integer.parseInt(intArray.valueAt(3).getText().toString()) * Integer.parseInt(intArray.valueAt(9).getText().toString()) * Integer.parseInt(intArray.valueAt(15).getText().toString()) * Integer.parseInt(intArray.valueAt(21).getText().toString()) * Integer.parseInt(intArray.valueAt(27).getText().toString());
        a[9] =  Integer.parseInt(intArray.valueAt(4).getText().toString()) * Integer.parseInt(intArray.valueAt(10).getText().toString()) * Integer.parseInt(intArray.valueAt(16).getText().toString()) * Integer.parseInt(intArray.valueAt(22).getText().toString()) * Integer.parseInt(intArray.valueAt(28).getText().toString());

        for(int j = 0; j < i.length; j++) {
            if(i[j] != a[j]) {
                DialogFragment badDialog = new WrongAnswer();
                badDialog.show(getFragmentManager(), "WrongAnswerFragment");
                break;
            }
            if(j == i.length - 1) {
                if(i[j] == a[j]) {
                    SaveTime(chronometer.getText().toString());
                    Log.d("Chronometer", chronometer.getText().toString());
                    Toast.makeText(this, "Congratulations!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, StartGame.class);
                    startActivity(intent);
                }
            }
        }

    }

    @Override
    public void onValidateNegativeClick(DialogFragment dialog) {
    }

    public boolean IsGridFull() {
        for(int i = 0; i < intArray.size(); i++) {
            if(intArray.valueAt(i).getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onValidateErrorNeutralClick(DialogFragment dialog) {
    }

    @Override
    public void onWrongAnswerNeutralClick(DialogFragment dialog) {
    }

    public void SaveTime(String pTime) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.mutyaba.multiply.scores", Context.MODE_PRIVATE);
        String currentTimes = sharedPreferences.getString("times", "");
        String currentDate = sharedPreferences.getString("dates", "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        if(currentTimes.equals("")) {
            editor.putString("times", pTime);
            editor.putString("dates", timeStamp);
        } else {
            editor.putString("times", currentTimes + "," + pTime);
            editor.putString("dates", currentDate + "," + timeStamp);
        }
        editor.apply();
    }
}
