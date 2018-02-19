package com.example.jorge.helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CapturingImageActivity extends Activity {

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static final int REQUEST_IMAGE = 100;
    
    Button captureButton;
    ImageView imageView;
    File destination;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capt_image_activity);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        captureButton = (Button)findViewById(R.id.capture);
        captureButton.setOnClickListener(listener);
        
        imageView = (ImageView)findViewById(R.id.image);
        
        destination = new File(Environment.getExternalStorageDirectory(),"image.jpg");
    }
    
    @Override
    //Print the image in the Imageview when the Intent to the camera has finished
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            //Bitmap userImage = (Bitmap)data.getExtras().get("data");
            try {
                FileInputStream in = new FileInputStream(destination);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; //Downsample 2x
                Bitmap userImage = BitmapFactory.decodeStream(in, null, options);
                imageView.setImageBitmap(userImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Add extra to save full-image somewhere
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
            Toast.makeText(CapturingImageActivity.this, Uri.fromFile(destination).toString(), Toast.LENGTH_SHORT).show();

            //REQUEST_IMAGE defines a request code in order to identify it on the onActivityResult
            startActivityForResult(intent, REQUEST_IMAGE);        
        }
    };
}
