package com.example.milind.movinghello;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import java.lang.*;
import java.io.*;
import java.util.*;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.milind.movinghello.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    float dX;
    float dY;
    int lastAction;
    private TextView tv;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getActionBar().hide();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(activity_main);
            tv=findViewById(R.id.draggable_view);
            final EditText ed=findViewById(R.id.enter);
            final TextView tv=findViewById(R.id.draggable_view);
            tv.setText(ed.getText().toString());
            tv.setSelected(true);
        //Initialize the Animation object

        //Assign the animation to the TextView
        ed.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   tv.setSelected(true);
               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                   int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                   tv.setTextColor(randomAndroidColor);
                   randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                   findViewById(R.id.bg).setBackgroundColor(randomAndroidColor);
                   randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                   findViewById(R.id.enter).setBackgroundColor(randomAndroidColor);
                   tv.setTextSize(new Random().nextInt(80)+20);
                  // tv.setText(ed.getText().toString());
                   tv.setSelected(true);
                   byte [] strAsByteArray = ed.getText().toString().getBytes();

                   byte [] result =
                           new byte [strAsByteArray.length];

                   // Store result in reverse order into the
                   // result byte[]
                   for (i = 0; i<strAsByteArray.length; i++)
                       result[i] =
                               strAsByteArray[strAsByteArray.length-i-1];
                   tv.setText(new String(result));
               }

               @Override
               public void afterTextChanged(Editable editable) {
                   tv.setSelected(true);
               }
           });
            final View dragView = findViewById(R.id.draggable_view);
            dragView.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    lastAction = MotionEvent.ACTION_DOWN;
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.setY(event.getRawY() + dY);
                    view.setX(event.getRawX() + dX);
                    lastAction = MotionEvent.ACTION_MOVE;
                    break;
                case MotionEvent.ACTION_UP:
                    if (lastAction == MotionEvent.ACTION_DOWN)
                        Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return false;
            }
            return true;
        }
}
