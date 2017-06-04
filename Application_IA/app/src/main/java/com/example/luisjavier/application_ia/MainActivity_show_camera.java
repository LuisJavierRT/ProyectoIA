package com.example.luisjavier.application_ia;

//import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 5/24/2017.
 */

public class MainActivity_show_camera extends AppCompatActivity implements CvCameraViewListener2 {
    // Used for logging success or failure messages
    private static final String TAG = "OCVSample::Activity";

    // Loads camera view of OpenCV for us to use. This lets us see using OpenCV
    private CameraBridgeViewBase mOpenCvCameraView;

    // Used in Camera selection from menu (when implemented)
    private boolean              mIsJavaCamera = true;
    private MenuItem             mItemSwitchCamera = null;

    // These variables are used (at the moment) to fix camera orientation from 270degree to 0degree
    Mat mRgba;
    Mat mRgbaF;
    Mat mRgbaT;



    public String[] panel1 = {"Panel 1", "90%", "3401","verde"};
    public String[] panel2 = {"Panel 2", "71%", "2304","rojo"};
    public String[] panel3 = {"Panel 3", "80%", "3802","verde"};
    public String[] panel4 = {"Panel 4", "24%", "101","rojo"};
    public String[] panel5 = {"Panel 5", "67%", "2211","verde"};
    public String[] panel6 = {"Panel 6", "54%", "901","verde"};
    public String[] panel7 = {"Panel 7", "64%", "1901","verde"};
    public String[] panel8 = {"Panel 8", "74%", "2101","rojo"};
    public String[] panel9 = {"Panel 9", "44%", "1301","verde"};
    public String[] panel10 = {"Panel 10", "34%", "1041","verde"};
    public String[] panel11 = {"Panel 11", "94%", "4341","verde"};
    public String[] panel12 = {"Panel 12", "91%", "4021","verde"};

    //public String[][] paneles = {{"verde","rojo","verde","rojo"},{"rojo","verde","rojo","verde"},{"verde","rojo","verde","rojo"}};
    public String[][][] paneles = {{panel4,panel3,panel2,panel1},{panel8,panel7,panel6,panel5},{panel12,panel11,panel10,panel9}};

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };



    public MainActivity_show_camera() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.show_camera);
        getSupportActionBar().hide();


        mOpenCvCameraView = (JavaCameraView) findViewById(R.id.show_camera_activity_java_surface_view);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);



    }

    public void textToast(String textToDisplay) {
        Context context = getApplicationContext();
        CharSequence text = textToDisplay;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 50, 50);
        toast.show();
    }
    int touchx;
    int touchy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchx = (int)event.getX();
        touchy = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int real_x = (touchx * 640) / 720;
                int real_y = (touchy * 480) / 1280;

                if (real_x <= 155){
                    if (real_y >= 120 && real_y<=185) {
                        //textToast("POS 00: " + paneles[0][0]);

                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);

                        i.putExtra("panelInfo",paneles[0][0]);

                        startActivity(i);

                    }
                    else if(real_y > 185 && real_y<=240) {
                        //textToast("POS 01: " + paneles[0][1]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[0][1]);
                        startActivity(i);
                    }
                    else if(real_y > 240 && real_y<=300) {
                        //textToast("POS 02: " + paneles[0][2]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[0][2]);
                        startActivity(i);
                    }
                    else if(real_y > 300 && real_y<=365) {
                        //textToast("POS 03: " + paneles[0][3]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[0][3]);
                        startActivity(i);
                    }
                }
                else if (real_x <= 350 && real_x > 190){
                    if (real_y >= 120 && real_y<=185) {
                        //textToast("POS 10: " + paneles[1][0]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[1][0]);
                        startActivity(i);
                    }
                    else if(real_y > 185 && real_y<=240) {
                        //textToast("POS 11: " + paneles[1][1]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[1][1]);
                        startActivity(i);
                    }
                    else if(real_y > 240 && real_y<=300) {
                        //textToast("POS 12: " + paneles[1][2]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[1][2]);
                        startActivity(i);
                    }
                    else if(real_y > 300 && real_y<=365) {
                        //textToast("POS 13: " + paneles[1][3]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[1][3]);
                        startActivity(i);
                    }
                }
                else if (real_x <= 545 && real_x > 390){
                    if (real_y >= 120 && real_y<=185) {
                        //textToast("POS 20: " + paneles[2][0]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[2][0]);
                        startActivity(i);
                    }
                    else if(real_y > 185 && real_y<=240) {
                        //textToast("POS 21: " + paneles[2][1]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[2][1]);
                        startActivity(i);
                    }
                    else if(real_y > 240 && real_y<=300) {
                        //textToast("POS 22: " + paneles[2][2]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[2][2]);
                        startActivity(i);
                    }
                    else if(real_y > 300 && real_y<=365) {
                        //textToast("POS 23: " + paneles[2][3]);
                        Intent i = new Intent(getApplicationContext(), InfoPanelActivity.class);
                        i.putExtra("panelInfo",paneles[2][3]);
                        startActivity(i);
                    }
                }

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {

        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mRgbaF = new Mat(height, width, CvType.CV_8UC4);
        mRgbaT = new Mat(width, width, CvType.CV_8UC4);
    }



    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {

        // TODO Auto-generated method stub
        mRgba = inputFrame.rgba();
        // Rotate mRgba 90 degrees
        /*
        Core.transpose(mRgba, mRgbaT);

        Imgproc.resize(mRgbaT, mRgbaF, mRgbaF.size(), 0,0, 0);

        Core.flip(mRgbaF, mRgba, 1 );
        */
        int posX = 25;
        int posY = 5;
        Scalar color;
        for (int x = 0; x<3; x++){
            for (int y = 0; y<4; y++){
                if (paneles[x][y][3]=="verde")
                    color = new Scalar(0,255,0);
                else
                    color = new Scalar(255,0,0);
                Imgproc.rectangle(mRgba, new Point(posX, posY), new Point(posX+142, posY+142), color, 20);
                posY+=175;
            }
            posY = 5;
            posX+=220;
        }

        return mRgba; // This function must return
    }
}