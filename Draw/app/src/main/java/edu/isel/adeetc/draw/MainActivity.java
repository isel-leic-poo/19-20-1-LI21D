package edu.isel.adeetc.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private Drawing model;
    private String filename;


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
        view.setModel(model = new Drawing());
        Log.v("WOOOOOOOW", "onCreate()");

        filename = getFilesDir().getAbsolutePath() + "/savedData.txt";
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.v("WOOOOOOOW", "onStop()");

        try (PrintWriter file = new PrintWriter(new FileWriter(filename))) {
            file.println(model.toJSON());
        }
        catch (IOException ioe) {
            // Do something. Help?
            // ERRORS MUST BE HANDLED!!!
            // THIS IS A DEMO, OK?
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("WOOOOOOOW", "onStart()");

        try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
            final String modelContent = file.readLine();
            Log.v("WOOOOOOOW\"", modelContent);
        }
        catch (IOException ioe) {
            // Do something. Help?
            // ERRORS MUST BE HANDLED!!!
            // THIS IS A DEMO, OK?
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("WOOOOOOOW", "onDestroy()");
    }


}
