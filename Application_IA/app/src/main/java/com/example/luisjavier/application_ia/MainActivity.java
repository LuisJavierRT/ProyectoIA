package com.example.luisjavier.application_ia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.opencv.android.OpenCVLoader;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private ImageView btnFlash;
    private Camera camera;
    Camera.Parameters params;
    private boolean isFlashOn;
    private boolean hasFlash;
    private ImageView btnCamera;
    private ImageView btnPanels;
    private ImageView btnPanelSolar;

    private static String TAG = "MainActivity";

    static{
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV not loaded");
        }
        else{
            Log.d(TAG,"OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnFlash = (ImageView) findViewById(R.id.btn_flash);
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        isFlashOn = false;

        if (!hasFlash){
            Toast.makeText(this, "Este dispositivo no posee flash",Toast.LENGTH_SHORT).show();
        }


        mScannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();



        btnCamera = (ImageView) findViewById(R.id.btn_panelSolar);
        btnCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                mScannerView = (ZXingScannerView) findViewById(R.id.zxscan);
                mScannerView.setResultHandler(MainActivity.this);
                mScannerView.startCamera();
            }
        });
        btnFlash.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                turnOnFlash();
            }
        });



        btnPanels = (ImageView) findViewById(R.id.btn_panels);
        btnPanels.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, PanelsActivity.class);
                startActivity(intent);
                mScannerView.resumeCameraPreview(MainActivity.this);
            }
        });

        btnPanelSolar = (ImageView) findViewById(R.id.btn_panelSolar);
        btnPanelSolar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, OpenCVCamaraActivity.class);
                startActivity(intent);
                //mScannerView.resumeCameraPreview(MainActivity.this);
            }
        });

    }

    private void getCamera(){
        if (camera == null){
            try{
                camera = Camera.open();
                params = camera.getParameters();

            }
            catch (RuntimeException e){
                Log.e("Error with the camera.", e.getMessage());
            }
        }
    }

    private void turnOnFlash(){
        if(!isFlashOn){
            mScannerView.setFlash(true);
            isFlashOn = true;
        }
        else{
            mScannerView.setFlash(false);
            isFlashOn = false;
        }
    }


    public void QrScanner(View view){

        mScannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mScannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        mScannerView.setResultHandler(MainActivity.this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {


        Toast.makeText(this,result.getText(),Toast.LENGTH_SHORT).show();
        mScannerView.resumeCameraPreview(this);

    }


}
