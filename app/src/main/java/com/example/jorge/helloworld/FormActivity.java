package com.example.jorge.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends Activity implements View.OnClickListener  {

	public static final String PREFS_NAME = "MyPrefsFile";
	
    EditText email, message;
    CheckBox age;
    Button submit;
    
    SharedPreferences formStore;
    
    boolean submitSuccess = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        
        //Recover resources of the layout
        email = (EditText)findViewById(R.id.email);
        message = (EditText)findViewById(R.id.message);
        age = (CheckBox)findViewById(R.id.age);
        submit = (Button)findViewById(R.id.submit);
        
        //Set listener for the button and implement the Listener into this class
        submit.setOnClickListener(this);
        
        //Retrieve or create the preferences object
        //File defined in FormActivity.xml
        //formStore = getPreferences(Activity.MODE_PRIVATE);
        
        //File defined in PREFS_NAME
        formStore = getSharedPreferences(PREFS_NAME, 0);
   
        
    }
    
    @Override
    public void onResume() {
        super.onResume();
        //Set a Toast to notify that it is onResume
        Context context = getApplicationContext();
        CharSequence text = "onResume!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        
        //Restore the form data
        email.setText(formStore.getString("email", ""));
        message.setText(formStore.getString("message", ""));
        age.setChecked(formStore.getBoolean("age", false));
    }
    
    @Override
    public void onPause() {
        super.onPause();
        
        //Set a Toast to notify that it is onResume
        Context context = getApplicationContext();
        CharSequence text = "onPause!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        
        if(submitSuccess) {
            //Editor calls can be chained together
            formStore.edit().clear().commit();
        } else {
            //Store the form data
            SharedPreferences.Editor editor = formStore.edit();
            editor.putString("email", email.getText().toString());
            editor.putString("message", message.getText().toString());
            editor.putBoolean("age", age.isChecked());
            editor.commit();
        }
    }

    @Override
    public void onClick(View v) {
        
        //...Submit the message to a service...
        
        //Mark the operation successful
        submitSuccess = true;
        
        //When the application is closed the text is removed.
        
        //Close
        finish();
    }
    
}
