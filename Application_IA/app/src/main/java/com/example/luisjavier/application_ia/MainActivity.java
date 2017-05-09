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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private ImageView btnFlash;
    private Camera camera;
    Camera.Parameters params;
    private boolean isFlashOn;
    private boolean hasFlash;
    private ImageView btnCamera;


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

        btnCamera = (ImageView) findViewById(R.id.btn_camera);
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

    /*@Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }*/


    public void QrScanner(View view){

        /*mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        this.setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera*/

        mScannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    /*

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }*/

    @Override
    public void handleResult(Result result) {
        // Do something with the result here

        /*Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        android.app.AlertDialog alert1 = builder.create();
        alert1.show();*/

        Toast.makeText(this,result.getText(),Toast.LENGTH_SHORT).show();
        mScannerView.resumeCameraPreview(this);

    }


}
