package com.MyGame;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Srikanth Adapa
 */
public class MyGameActivity extends Activity {

    MyGameSurfaceView myGameSurfaceView1;
    MyForeground myForeground;

    //MyAccelerometer myAccelerometer;

    GestureLibrary gestureLibrary = null;
    GestureOverlayView gestureOverlayView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myGameSurfaceView1 = (MyGameSurfaceView) findViewById(R.id.myview1);
        myForeground = (MyForeground) findViewById(R.id.myforeground);

        //Set myForeground using transparent background
        myForeground.setZOrderOnTop(true);
        myForeground.getHolder().setFormat(PixelFormat.TRANSPARENT);

        //myAccelerometer = new MyAccelerometer(this);

        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestures);
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        gestureLibrary.load();
        gestureOverlayView.addOnGesturePerformedListener(gesturePerformedListener);

    }

    OnGesturePerformedListener gesturePerformedListener
            = new OnGesturePerformedListener() {

        @Override
        public void onGesturePerformed(GestureOverlayView view, Gesture gesture) {
            ArrayList<Prediction> prediction = gestureLibrary.recognize(gesture);

            if (prediction.size() > 0) {
                String gesturePerformed = prediction.get(0).name;
                if (gesturePerformed.equals("up")) {
                    myForeground.canvasUp();
                    Toast.makeText(MyGameActivity.this,
                            "up", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("down")) {
                    myForeground.canvasDown();
                    Toast.makeText(MyGameActivity.this,
                            "down", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("left")) {
                    myForeground.canvasLeft();
                    Toast.makeText(MyGameActivity.this,
                            "left", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("right")) {
                    myForeground.canvasRight();
                    Toast.makeText(MyGameActivity.this,
                            "right", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("clockwise")) {
                    myForeground.canvasClockwise();
                    Toast.makeText(MyGameActivity.this,
                            "clockwise", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("anti-clockwise")) {
                    myForeground.canvasAntiClockwise();
                    Toast.makeText(MyGameActivity.this,
                            "anti-clockwise", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("enlarge")) {
                    myForeground.canvasEnlarge();
                    Toast.makeText(MyGameActivity.this,
                            "enlarge", Toast.LENGTH_LONG).show();
                } else if (gesturePerformed.equals("reduce")) {
                    myForeground.canvasReduce();
                    Toast.makeText(MyGameActivity.this,
                            "reduce", Toast.LENGTH_LONG).show();
                }
            }

        }

    };

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myGameSurfaceView1.MyGameSurfaceView_OnResume();
        myForeground.MyGameSurfaceView_OnResume();

        //myAccelerometer.registerListener();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myGameSurfaceView1.MyGameSurfaceView_OnPause();
        myForeground.MyGameSurfaceView_OnPause();

        //myAccelerometer.unregisterListener();
    }

    void updateAccelerometer(float tx, float ty) {
        int w = myForeground.getWidth();
        int h = myForeground.getHeight();

        float x = ((w / 2) * tx) + (w / 2);
        float y = ((h / 2) * ty) + (h / 2);
        myForeground.updateAccelerometer(x, y);
    }

}