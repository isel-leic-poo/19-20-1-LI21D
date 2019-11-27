package edu.isel.adeetc.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    private void inflate() {
        final RelativeLayout layout = new RelativeLayout(this);
        final DrawView drawView = new DrawView(this);
        drawView.setId(Globals.DRAW_VIEW_ID);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        drawView.setLayoutParams(params);
        layout.addView(drawView);

        setContentView(layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflate();

        final DrawView view = findViewById(Globals.DRAW_VIEW_ID);
        view.setModel(new Drawing());
    }
}
