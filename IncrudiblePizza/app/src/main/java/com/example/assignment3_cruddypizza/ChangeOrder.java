package com.example.assignment3_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeOrder extends AppCompatActivity {
    private static final String TEXT_LANGUGE_KEY="textLanguage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_order);

        Button placeOrder,
                editOrder,
                deleteOrder,
                viewOrders;

        EditText orderNumber;

        orderNumber=findViewById(R.id.editTextOrderNumber);

        placeOrder=findViewById(R.id.placeOrder_ed);
        editOrder=findViewById(R.id.submitEditOrder);
        deleteOrder=findViewById(R.id.submitDeleteOrder);
        viewOrders=findViewById(R.id.viewOrder_ed);

        Resources res = getResources();
        String[] strings;

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        String language= prefs.getString(TEXT_LANGUGE_KEY, "English");

        placeOrder.setOnClickListener(view -> {
            Intent intent= new Intent(ChangeOrder.this, PlaceOrder.class);
            startActivity(intent);

        });

        viewOrders.setOnClickListener(view -> {
            Intent intent= new Intent(ChangeOrder.this, ViewOrder.class);
            startActivity(intent);

        });

        editOrder.setOnClickListener(view -> {
            int orderNum;
            orderNum= Integer.parseInt(orderNumber.getText().toString());
            //Enter Code here to edit in the database
            Intent intent= new Intent(ChangeOrder.this, EditOrder.class);
            Bundle extras = new Bundle();//create bundle object
            extras.putInt("rowID", orderNum);//fill bundle
            intent.putExtras(extras);
            startActivity(intent);
        });
        DBAdapter db = new DBAdapter(this);
        deleteOrder.setOnClickListener(view -> {
            //Enter Code here to delete from database
           //delete a contact - DELETE
            try {
                db.open();
                int orderNum;
                orderNum = Integer.parseInt(orderNumber.getText().toString());
                if (db.deleteOrder(orderNum))

                    Toast.makeText(this, "Delete successful", Toast.LENGTH_LONG).show();

                else

                    Toast.makeText(this, "Delete failed", Toast.LENGTH_LONG).show();


                db.close();
            }
            catch(Exception e){
                Log.e("Error","A database error occurred");
            }
        });





        if (language.equals("French")) {

            strings = res.getStringArray(R.array.French);

            placeOrder.setText(strings[0]);
            viewOrders.setText(strings[1]);
            editOrder.setText(strings[4]);
            deleteOrder.setText(strings[5]);

        }
        else if(language.equals("English")){


            strings = res.getStringArray(R.array.English);

            placeOrder.setText(strings[0]);
            viewOrders.setText(strings[1]);
            editOrder.setText(strings[4]);
            deleteOrder.setText(strings[5]);

        }

    }
}