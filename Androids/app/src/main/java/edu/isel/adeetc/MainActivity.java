package edu.isel.adeetc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import edu.isel.adeetc.androids.R;
import edu.isel.adeetc.androids.view.RobotTile;
import edu.isel.adeetc.poo.TilePanel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TilePanel board = findViewById(R.id.tilePanel);
        board.setTile(0, 0, new RobotTile(this));
    }
}
