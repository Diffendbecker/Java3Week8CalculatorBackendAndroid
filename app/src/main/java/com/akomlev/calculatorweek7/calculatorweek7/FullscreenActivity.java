package com.akomlev.calculatorweek7.calculatorweek7;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            //mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
            //        | View.SYSTEM_UI_FLAG_FULLSCREEN
            //        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            //        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            //        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonPlus, buttonMinus, buttonDivide,
            buttonMultiply, buttonAc, buttonEqual, buttonDot;

    EditText editTextCalc;

    float mValueOne, mValueTwo;

    boolean plus, minus, multiply, divide;
    boolean isFirstSymbol = true;

    private boolean IfAnyAction()
    {
        if(plus || minus || multiply || divide) {
            return true;
        }
        else
        {
            return false;
        }
    }

    Boolean keepNumbers = false;
    private void SetCorrectText(String buttonText)
    {
        if(isFirstSymbol && !buttonText.equals("0"))
        {
            editTextCalc.setText(buttonText);
            isFirstSymbol = false;
        }
        else if (IfAnyAction() && !keepNumbers)
        {
            editTextCalc.setText(buttonText);
            keepNumbers = true;
        }
        else
        {
            editTextCalc.setText(editTextCalc.getText() + buttonText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonAc = findViewById(R.id.buttonAc);
        buttonEqual = findViewById(R.id.buttonEquals);
        editTextCalc = findViewById(R.id.edt1);
        buttonDot = findViewById(R.id.buttonDot);

        buttonEqual.setBackgroundColor(getResources().getColor(R.color.orange));
        ViewCompat.setBackgroundTintList(buttonEqual, ContextCompat.getColorStateList(this, R.color.orange));
        editTextCalc.setCursorVisible(false);
        editTextCalc.setText("0");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCorrectText("9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textValue = editTextCalc.getText().toString();
                if(!textValue.equals("0"))
                {
                    SetCorrectText("0");
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextCalc == null) {
                    editTextCalc.setText("");
                } else {
                    mValueOne = Float.parseFloat(editTextCalc.getText() + "");
                    plus = true;
                    keepNumbers = false;
                    //editTextCalc.setText(null);
                }
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(editTextCalc.getText() + "");
                minus = true;
                keepNumbers = false;
                //editTextCalc.setText(null);
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(editTextCalc.getText() + "");
                multiply = true;
                keepNumbers = false;
                //editTextCalc.setText(null);
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(editTextCalc.getText() + "");
                divide = true;
                keepNumbers = false;
                //editTextCalc.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestService restService = new RestService();

                mValueTwo = Float.parseFloat(editTextCalc.getText() + "");
                String response = "0";

                if (plus == true) {
                    try {
                        String value1Str = Float.toString(mValueOne);
                        String value2Str = Float.toString(mValueTwo);
                        response = restService.SendRestRequest(value1Str, value2Str, "add");
                        editTextCalc.setText(response);
                    } catch (Exception e) {
                        Reset();
                        e.printStackTrace();
                    }

                    SetTextAfterAction(response);
                    plus = false;
                }

                if (minus == true) {
                    try {
                        String value1Str = Float.toString(mValueOne);
                        String value2Str = Float.toString(mValueTwo);
                        response = restService.SendRestRequest(value1Str, value2Str, "subtract");
                        editTextCalc.setText(response);
                    } catch (Exception e) {
                        Reset();
                        e.printStackTrace();
                    }

                    SetTextAfterAction(response);
                    minus = false;
                }

                if (multiply == true) {
                    try {
                        String value1Str = Float.toString(mValueOne);
                        String value2Str = Float.toString(mValueTwo);
                        response = restService.SendRestRequest(value1Str, value2Str, "multiply");
                        editTextCalc.setText(response);
                    } catch (Exception e) {
                        Reset();
                        e.printStackTrace();
                    }

                    SetTextAfterAction(response);
                    multiply = false;
                }

                if (divide == true) {

                    try {
                        String value1Str = Float.toString(mValueOne);
                        String value2Str = Float.toString(mValueTwo);
                        response = restService.SendRestRequest(value1Str, value2Str, "divide");
                        editTextCalc.setText(response);
                    } catch (Exception e) {
                        Reset();
                        e.printStackTrace();
                    }

                    SetTextAfterAction(response);
                    divide = false;
                }
            }
        });

        buttonAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextCalc.getText().toString().contains("."))
                {
                    editTextCalc.setText(editTextCalc.getText() + ".");
                }

            }
        });
        //mControlsView = findViewById(R.id.fullscreen_content_controls);
        //mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        //mContentView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        toggle();
        //    }
    //});

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    private void SetTextAfterAction(String response)
    {
        Float responseFloat = Float.parseFloat(response);
        Integer responseRound = Math.round(responseFloat);

        if(responseFloat-responseRound==0) {
            editTextCalc.setText(responseRound + "");
        }
        else {
            editTextCalc.setText(responseFloat + "");
        }
    }

    private void Reset()
    {
        editTextCalc.setText("0");
        plus = false;
        minus = false;
        multiply = false;
        divide = false;
        mValueOne = 0;
        mValueTwo = 0;
        isFirstSymbol = true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
