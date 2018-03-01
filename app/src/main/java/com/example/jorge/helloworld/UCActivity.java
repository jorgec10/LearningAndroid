package com.example.jorge.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UCActivity extends Activity {
       private int position = 0;

       //Declaration of the different conversion factors (multipliers)
       private double[] multipliers =
       {
          0.0015625,         // Acres to square miles
          101325.0,          // Atmospheres to Pascals
          100000.0,          // Bars to Pascals
          0,                 // Degrees Celsius to Degrees Fahrenheit (placeholder)
          0,                 // Degrees Fahrenheit to Degrees Celsius (placeholder)
          0.00001,           // Dynes to Newtons
          0.3048,            // Feet/Second to Metres/Second
          0.0284130625,      // Fluid Ounces (UK) to Litres
          0.0295735295625,   // Fluid Ounces (US) to Litres
          746.0,             // Horsepower (electric) to Watts
          735.499,           // Horsepower (metric) to Watts
          1/1016.0469088,    // Kilograms to Tons (UK or long)
          1/907.18474,       // Kilograms to Tons (US or short)
          1/0.0284130625,    // Litres to Fluid Ounces (UK)
          1/0.0295735295625, // Litres to Fluid Ounces (US)
          331.5,             // Mach Number to Metres/Second
          1/0.3048,          // Metres/Second to Feet/Second
          1/331.5,           // Metres/Second to Mach Number
          0.833,             // Miles/Gallon (UK) to Miles/Gallon (US)
          1/0.833,           // Miles/Gallon (US) to Miles/Gallon (UK)
          100000.0,          // Newtons to Dynes
          1/101325.0,        // Pascals to Atmospheres
          0.00001,           // Pascals to Bars
          640.0,             // Square Miles to Acres
          1016.0469088,      // Tons (UK or long) to Kilograms
          907.18474,         // Tons (US or short) to Kilograms
          1/746.0,           // Watts to Horsepower (electic)
          1/735.499          // Watts to Horsepower (metric)
       };

       @Override
       //onCreate is called when:
       //1- Starts the first time the app
       //2- Unblock the phone
       //3- Turn around the screen (landscape vs portrait
      
       public void onCreate(Bundle savedInstanceState)
       {
          super.onCreate(savedInstanceState);
          
          //The most important is set the view (activity) that we want to show to the user
          setContentView(R.layout.activity_uc);

          //Capture objects that we have declared in the interface (layout)
          final EditText etUnits = (EditText) findViewById(R.id.units);
          final Spinner spnConversions = (Spinner) findViewById(R.id.conversions);
          final TextView tvResult =  (TextView) findViewById(R.id.result);
          
          //This introduces the different conversions defined in the "arrays.xml" into the spinner
          ArrayAdapter<CharSequence> aa;
          aa = ArrayAdapter.
                 createFromResource(this, R.array.conversions,
                                    android.R.layout.simple_spinner_item);
          aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
          spnConversions.setAdapter(aa);
          
          //Listener over the same spinner to know the current conversion selected
          AdapterView.OnItemSelectedListener oisl;
          oisl = new AdapterView.OnItemSelectedListener()
          {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view,
                                        int position, long id)
             {
                UCActivity.this.position = position;
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent)
             {
                System.out.println("nothing");
             }
          };
          spnConversions.setOnItemSelectedListener(oisl);
          
          /* Other way to implement a listener is to build the new object inside the call
          spnConversions.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				UCActivity.this.position = position;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				System.out.println("nothing");
				
			}
		});*/

          //First capture the button object and create a listener for the functionality
          //This clears the text field to "" empty
          final Button btnClear = (Button) findViewById(R.id.clear);
          AdapterView.OnClickListener ocl;
          ocl = new AdapterView.OnClickListener()
          {
             @Override
             public void onClick(View v)
             {
                etUnits.setText("");
                tvResult.setText("");
             }
          };
          btnClear.setOnClickListener(ocl);
          btnClear.setEnabled(false);

          //Convert button
          final Button btnConvert = (Button) findViewById(R.id.convert);
          ocl = new AdapterView.OnClickListener()
          {
             @Override
             public void onClick(View v)
             {
                String text = etUnits.getText().toString();
                double input = Double.parseDouble(text);
                double result = 0;
                
                //Now depending on the position apply the different conversion factors
                if (position == 3)
                   result = input*9.0/5.0+32; // Celsius to Fahrenheit
                else
                if (position == 4)
                   result = (input-32)*5.0/9.0; // Fahrenheit to Celsius
                else
                   result = input*multipliers[position];
                
                
                tvResult.setText(" "+result);
             }
          };
          btnConvert.setOnClickListener(ocl);
          btnConvert.setEnabled(false);

          //Button close
          Button btnClose = (Button) findViewById(R.id.close);
          ocl = new AdapterView.OnClickListener()
          {
             @Override
             public void onClick(View v)
             {
            	//Change the status to finish (close the application)
                finish();
             }
          };
          btnClose.setOnClickListener(ocl);

          final Button btnInternal = (Button) findViewById(R.id.internal);
          AdapterView.OnClickListener intOcl = new AdapterView.OnClickListener() {

              @Override
              public void onClick(View view) {

                  Context context = getApplicationContext();
                  CharSequence text = "Internal activity!";
                  int duration = Toast.LENGTH_SHORT;
                  Toast toast = Toast.makeText(context, text, duration);
                  toast.show();

              }
          };
          btnInternal.setOnClickListener(intOcl);
          btnInternal.setEnabled(true);

          final Button btnForm = (Button) findViewById(R.id.form);
          AdapterView.OnClickListener formOcl = new AdapterView.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Context context = getApplicationContext();
                  CharSequence text = "Launching form!";
                  int duration = Toast.LENGTH_SHORT;
                  Toast toast = Toast.makeText(context, text, duration);
                  toast.show();

                  Intent intent = new Intent(UCActivity.this, FormActivity.class);
                  startActivity(intent);

              }
          };
          btnForm.setOnClickListener(formOcl);
          btnForm.setEnabled(true);

          final Button btnExternal = (Button) findViewById(R.id.external);
          btnExternal.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Context context = getApplicationContext();
                  CharSequence text = "Launching external!";
                  int duration = Toast.LENGTH_SHORT;
                  Toast toast = Toast.makeText(context, text, duration);
                  toast.show();

                  Intent intent = new Intent(UCActivity.this, ExternalActivity.class);
                  startActivity(intent);

              }
          });
          btnExternal.setEnabled(true);

          final Button btnNotification = (Button) findViewById(R.id.notification);
          btnNotification.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent intent = new Intent(UCActivity.this, NotificationActivity.class);
                  startActivity(intent);

              }
          });

          final Button btnAlarm = (Button) findViewById(R.id.alarm);
          btnAlarm.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(UCActivity.this, AlarmActivity.class);
                  startActivity(intent);
              }
          });

          final Button btnPicture = (Button) findViewById(R.id.picture);
          btnPicture.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(UCActivity.this, CapturingImageActivity.class);
                  startActivity(intent);
              }
          });

           final Button btnVideo = (Button) findViewById(R.id.video);
           btnVideo.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(UCActivity.this, CapturingVideoActivity.class);
                   startActivity(intent);
               }
           });

           final Button btnList = (Button) findViewById(R.id.list);
           btnList.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(UCActivity.this, UserListActivity.class);
                   startActivity(intent);
               }
           });

          
          //The following textwatcher enables or disables the convert button in function of the text from the Units field
          TextWatcher tw;
          tw = new TextWatcher()
          {
             @Override
             public void afterTextChanged(Editable s)
             {
             }

             @Override
             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after)
             {
             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count)
             {
                if (etUnits.getText().length() == 0)
                {
                   btnClear.setEnabled(false);
                   btnConvert.setEnabled(false);
                }
                else
                {
                   btnClear.setEnabled(true);
                   btnConvert.setEnabled(true);
                }
             }
          };
          etUnits.addTextChangedListener(tw);
       }
    }
    
