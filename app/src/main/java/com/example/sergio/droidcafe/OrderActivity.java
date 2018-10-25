package com.example.sergio.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

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
    }

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
}
