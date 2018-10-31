package com.example.sergio.droidcafeinput;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sergio.droidcafe.R;

public class MainActivity extends AppCompatActivity {

    String test=null;
    public static final String EXTRA_MESSAGE =
            "com.example.android.droidcafe.extra.MESSAGE";
    private String mOrderMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                    //Metodo para llamar al order activity
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    //añado para que pase a un intent el extra_message
                    intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                    startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){
           case R.id.action_contact:
               Toast.makeText(this,R.string.action_contact_message,Toast.LENGTH_LONG).show();

               return true;
           case R.id.action_favorites:
               Toast.makeText(this,R.string.action_favorites_message,Toast.LENGTH_LONG).show();
               return true;
           case R.id.action_order:
               Toast.makeText(this,R.string.action_order_message,Toast.LENGTH_LONG).show();
               Intent intent = new Intent(MainActivity.this, OrderActivity.class);
               intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
               startActivity(intent);
               return true;
           case R.id.action_status:
               Toast.makeText(this,R.string.action_status_message,Toast.LENGTH_LONG).show();
               return true;
            default:

       }
        return  super.onOptionsItemSelected(item);
    }



    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void showDonutOrder(View view) {
        //displayToast(getString(R.string.donut_order_message));
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    public void showIceCreamOrder(View view) {
        //displayToast(getString(R.string.ice_cream_order_message));
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    public void showFroyoOrder(View view) {
        //displayToast(getString(R.string.froyo_order_message));
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }


}
