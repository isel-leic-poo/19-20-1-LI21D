package edu.isel.adeetc.helloandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private static final int COUNTER_VIEW_ID = 1000;
    private static final int INC_BUTTON_ID = 1001;
    private static final int DEC_BUTTON_ID = 1002;
    private static final int COUNTER_CTRL_ID = 1003;

    public static final String TAG = "COUNTER_APP";

    @SuppressLint("ResourceType")
    private void inflate() {
        final TextView textView = new TextView(this);
        textView.setText("0");
        textView.setTextSize(54);
        textView.setId(COUNTER_VIEW_ID);

        final Button incButton = new Button(this);
        incButton.setText("+");
        incButton.setTextSize(46);
        incButton.setId(INC_BUTTON_ID);

        final Button decButton = new Button(this);
        decButton.setText("-");
        decButton.setTextSize(46);
        decButton.setId(DEC_BUTTON_ID);

        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(textView);
        layout.addView(incButton);
        layout.addView(decButton);

        final CounterControl ctrl = new CounterControl(this);
        ctrl.setId(COUNTER_CTRL_ID);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ctrl.setLayoutParams(params);
        layout.addView(ctrl);

        setContentView(layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate() starts");
        inflate();

        Counter model = new Counter();
        TextView counterView = findViewById(COUNTER_VIEW_ID);
        counterView.setText(model.toString());

        CounterControl ctrl = findViewById(COUNTER_CTRL_ID);
        ctrl.setModel(model);

        final Button incButton = findViewById(INC_BUTTON_ID);
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "incButton clicked");
                model.increment();
            }
        });

        final Button decButton = findViewById(DEC_BUTTON_ID);
        decButton.setOnClickListener(v -> {
            Log.v(TAG, "decButton clicked");
            model.decrement();
        });

        model.addListener(new Counter.OnValueChangedListener() {
            @Override
            public void valueChanged(Counter source) {
                counterView.setText(model.toString());
            }
        });

        Log.v(TAG, "onCreate() ends");
    }
}
