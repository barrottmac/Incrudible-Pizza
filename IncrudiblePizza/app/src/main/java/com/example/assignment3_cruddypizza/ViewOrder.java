package com.example.assignment3_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOrder extends AppCompatActivity {
    private static final String TEXT_LANGUGE_KEY="textLanguage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Button placeOrder,
                editOrder,
                deleteOrder;

        placeOrder=findViewById(R.id.placeOrder_vo);
        editOrder=findViewById(R.id.editOrder);
        deleteOrder=findViewById(R.id.deleteOrder);

        TextView viewOrdersView =findViewById(R.id.viewOrdersTextView);
        viewOrdersView.setMovementMethod(new ScrollingMovementMethod());

        Resources res = getResources();
        String[] strings;

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        String language= prefs.getString(TEXT_LANGUGE_KEY, "English");

        DBAdapter db = new DBAdapter(this);
        Cursor c ;
        try {
            db.open();
            c = db.getAllOrders();
            StringBuilder stringBuilder = new StringBuilder("");
            while (c.moveToNext()) {

                stringBuilder.append(String.format("Order/Ordre #: %s Name/Nom: %s  \nSize/Taille:%s \ntopping/garniture 1: %s \ntopping/garniture 2: %s \ntopping/garniture 3: %s \ntopping/garniture 4: %s \ntopping/garniture 5: %s \ntopping/garniture 6: %s \n %s\n\n", c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), (c.getString(9))));
                viewOrdersView.setText(stringBuilder);
            }

            db.close();
        }
        catch(Exception e){
            Log.e("Error","A database error occurred");
        }
        placeOrder.setOnClickListener(view -> {
            Intent intent= new Intent(ViewOrder.this, PlaceOrder.class);
            startActivity(intent);

        });

        editOrder.setOnClickListener(view -> {
            Intent intent= new Intent(ViewOrder.this, ChangeOrder.class);
            startActivity(intent);
        });

        deleteOrder.setOnClickListener(view -> {
            Intent intent= new Intent(ViewOrder.this, ChangeOrder.class);
            startActivity(intent);
        });


        if (language.equals("French")) {

            strings = res.getStringArray(R.array.French);

            placeOrder.setText(strings[0]);
            editOrder.setText(strings[4]);
            deleteOrder.setText(strings[5]);

        }
        else if(language.equals("English")){


            strings = res.getStringArray(R.array.English);

            placeOrder.setText(strings[0]);
            editOrder.setText(strings[4]);
            deleteOrder.setText(strings[5]);

        }

    }
    public void DisplayContact(Cursor c)

    {

        Toast.makeText(this,

                "id: " + c.getString(0) + "\n" +

                        "Name: " + c.getString(1) + "\n" +

                        "Email: " + c.getString(2),

                Toast.LENGTH_LONG).show();

    }
}