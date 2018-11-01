package com.example.sergio.droidcafeinput;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergio.droidcafe.R;

public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    //crep la variable tag accesible desde cualquier instancia
    private static final String TAG =MainActivity.class.getSimpleName();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        // texto que recibe del Main:
        String message = getString(R.string.order_description) +
                intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // asigno al textview el mensaje que recibe del main definiendo antes un textview
        TextView textView = findViewById(R.id.order_textview);//R.id para decirle que id tiene
        textView.setText(message);
        //para los radiobutton definimos el RadioGroup
        RadioGroup radioGroup= findViewById(R.id.radioGroup);
        radioGroup.check(R.id.sameday);
        //creamos el spinner
        spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        // Crear ArrayAdapter usando el string array y el layput del spinner por defecto
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
        //acciion para detectar telefono marcado yamrcar el numero inroducido
        EditText editText = findViewById(R.id.phone_text);
        if (editText != null)
            editText.setOnEditorActionListener
                    (new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                            boolean handled = false;
                            if (actionId == EditorInfo.IME_ACTION_SEND) {
                                dialNumber();
                                handled = true;
                            }
                            return handled;
                        }
                        // If view is found, set the listener for editText.
                    });

    }//fin OnCreate
    
    //############ METODO LLAMAR POR TELEFONO ##############
    private void dialNumber() {
        // Find the editText_main view.
        EditText editText = findViewById(R.id.phone_text);
        String phoneNum = null;
        // If the editText field is not null, 
        // concatenate "tel: " with the phone number string.
        if (editText != null) phoneNum = "tel:" +
                editText.getText().toString();
        // Optional: Log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + phoneNum);
        // Specify the intent.
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse(phoneNum));
        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "No se puede llamar a este nº!");
        }
    }
    //##########################################################
    
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    displayToast(getString(R.string.same_day_text));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.next_day_text));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    private void displayToast(String message) {

        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String spinnerLabel = spinner.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
