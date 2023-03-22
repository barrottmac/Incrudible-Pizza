package com.example.assignment3_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditOrder extends AppCompatActivity {
    private static final String TEXT_LANGUGE_KEY="textLanguage";
    String topping1;
    String topping2;
    String topping3;
    String topping4;
    String topping5;
    String topping6;
    String size;
    String name;
    long rowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);



        DBAdapter db = new DBAdapter(this);

        Button placeOrder,
                viewOrder,
                editOrder;

        placeOrder= findViewById(R.id.eo_PlaceOrderButton);
        viewOrder= findViewById(R.id.eo_ViewOrderButton);
        editOrder= findViewById(R.id.eo_editSubmitButton);

        Resources res = getResources();
        String[] strings;

        Spinner spinnerSizes = (Spinner) findViewById(R.id.eo_sizeSpinner);
        Spinner spinnerTopping0 = (Spinner) findViewById(R.id.eo_toppingSpinner1);
        Spinner spinnerTopping1 = (Spinner) findViewById(R.id.eo_toppingSpinner2);
        Spinner spinnerTopping2 = (Spinner) findViewById(R.id.eo_toppingSpinner3);
        Spinner spinnerTopping3 = (Spinner) findViewById(R.id.eo_toppingSpinner4);
        Spinner spinnerTopping4 = (Spinner) findViewById(R.id.eo_toppingSpinner5);
        Spinner spinnerTopping5 = (Spinner) findViewById(R.id.eo_toppingSpinner6);

        EditText personName=findViewById(R.id.eo_editTextPersonName);


        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        String language= prefs.getString(TEXT_LANGUGE_KEY, "English");

        editOrder.setOnClickListener(view -> {
            if (personName.getText().length()<1&&language.equals("English")){
                Toast.makeText(this,"Please Enter a Name!",Toast.LENGTH_LONG).show();
            }
            else if(personName.getText().length()<1&&language.equals("French")){
                Toast.makeText(this,"Veuillez entrer un nom!",Toast.LENGTH_LONG).show();
            }
            else if (spinnerSizes.getSelectedItem().toString().equals("* Select Pizza Size *")){
                Toast.makeText(this,"Please Enter a Size!",Toast.LENGTH_LONG).show();
            }
            else if (spinnerSizes.getSelectedItem().toString().equals("* Sélectionnez la taille de la pizza *")){
                Toast.makeText(this,"Veuillez entrer une taille!",Toast.LENGTH_LONG).show();
            }
            else if (spinnerTopping0.getSelectedItem().toString().equals("* Select Topping *")){
                Toast.makeText(this,"Please Enter at least 1 Topping!",Toast.LENGTH_LONG).show();
            }
            else if (spinnerTopping0.getSelectedItem().toString().equals("* Sélectionner la garniture *")){
                Toast.makeText(this,"Veuillez entrer au moins 1 garniture!",Toast.LENGTH_LONG).show();
            }
            else {

                Bundle extras=getIntent().getExtras();
                if (extras!=null){
                    rowId=extras.getInt("rowID");

                }

                name = personName.getText().toString();
                size = spinnerSizes.getSelectedItem().toString();
                if(size.equals("Small")||size.equals("Petite")){
                    size="S/P";
                }
                else if (size.equals("Medium")||size.equals("Moyen")){
                    size="M/M";
                }
                else if (size.equals("Large")||size.equals("Grande")){
                    size="L/G";
                }
                else if (size.equals("Extra Large")||size.equals("Plus Grande")){
                    size="XL/XG";
                }

                topping1 = spinnerTopping0.getSelectedItem().toString();
                if(topping1.equals("Cheese")||topping1.equals("Fromage")){
                    topping1="1";
                }
                else if (topping1.equals("Mushroom")||topping1.equals("Champignon")){
                    topping1="2";
                }
                else if (topping1.equals("Pineapple")||topping1.equals("Ananas")){
                    topping1="3";
                }
                else if (topping1.equals("Green Pepper")||topping1.equals("Poivre vert")){
                    topping1="4";
                }
                else if (topping1.equals("Hot Pepper")||topping1.equals("Piment")){
                    topping1="5";
                }
                else if (topping1.equals("Onion")||topping1.equals("Oignon")){
                    topping1="6";
                }
                else if (topping1.equals("Pepperoni")){
                    topping1="7";
                }
                else if (topping1.equals("Sausage")||topping1.equals("Saucisson")){
                    topping1="8";
                }
                else if (topping1.equals("Bacon")||topping1.equals("Lard")){
                    topping1="9";
                }

                topping2 = spinnerTopping1.getSelectedItem().toString();
                if(topping2.equals("Cheese")||topping2.equals("Fromage")){
                    topping2="1";
                }
                else if (topping2.equals("Mushroom")||topping2.equals("Champignon")){
                    topping2="2";
                }
                else if (topping2.equals("Pineapple")||topping2.equals("Ananas")){
                    topping2="3";
                }
                else if (topping2.equals("Green Pepper")||topping2.equals("Poivre vert")){
                    topping2="4";
                }
                else if (topping2.equals("Hot Pepper")||topping2.equals("Piment")){
                    topping2="5";
                }
                else if (topping2.equals("Onion")||topping2.equals("Oignon")){
                    topping2="6";
                }
                else if (topping2.equals("Pepperoni")){
                    topping2="7";
                }
                else if (topping2.equals("Sausage")||topping2.equals("Saucisson")){
                    topping2="8";
                }
                else if (topping2.equals("Bacon")||topping2.equals("Lard")){
                    topping2="9";
                }
                topping3 = spinnerTopping2.getSelectedItem().toString();
                if(topping3.equals("Cheese")||topping3.equals("Fromage")){
                    topping3="1";
                }
                else if (topping3.equals("Mushroom")||topping3.equals("Champignon")){
                    topping3="2";
                }
                else if (topping3.equals("Pineapple")||topping3.equals("Ananas")){
                    topping3="3";
                }
                else if (topping3.equals("Green Pepper")||topping3.equals("Poivre vert")){
                    topping3="4";
                }
                else if (topping3.equals("Hot Pepper")||topping3.equals("Piment")){
                    topping3="5";
                }
                else if (topping3.equals("Onion")||topping3.equals("Oignon")){
                    topping3="6";
                }
                else if (topping3.equals("Pepperoni")){
                    topping3="7";
                }
                else if (topping3.equals("Sausage")||topping3.equals("Saucisson")){
                    topping3="8";
                }
                else if (topping3.equals("Bacon")||topping3.equals("Lard")){
                    topping3="9";
                }
                topping4 = spinnerTopping3.getSelectedItem().toString();
                if(topping4.equals("Cheese")||topping4.equals("Fromage")){
                    topping4="1";
                }
                else if (topping4.equals("Mushroom")||topping4.equals("Champignon")){
                    topping4="2";
                }
                else if (topping4.equals("Pineapple")||topping4.equals("Ananas")){
                    topping4="3";
                }
                else if (topping4.equals("Green Pepper")||topping4.equals("Poivre vert")){
                    topping4="4";
                }
                else if (topping4.equals("Hot Pepper")||topping4.equals("Piment")){
                    topping4="5";
                }
                else if (topping4.equals("Onion")||topping4.equals("Oignon")){
                    topping4="6";
                }
                else if (topping4.equals("Pepperoni")){
                    topping4="7";
                }
                else if (topping4.equals("Sausage")||topping4.equals("Saucisson")){
                    topping4="8";
                }
                else if (topping4.equals("Bacon")||topping4.equals("Lard")){
                    topping4="9";
                }
                topping5 = spinnerTopping4.getSelectedItem().toString();
                if(topping5.equals("Cheese")||topping5.equals("Fromage")){
                    topping5="1";
                }
                else if (topping5.equals("Mushroom")||topping5.equals("Champignon")){
                    topping5="2";
                }
                else if (topping5.equals("Pineapple")||topping5.equals("Ananas")){
                    topping5="3";
                }
                else if (topping5.equals("Green Pepper")||topping5.equals("Poivre vert")){
                    topping5="4";
                }
                else if (topping5.equals("Hot Pepper")||topping5.equals("Piment")){
                    topping5="5";
                }
                else if (topping5.equals("Onion")||topping5.equals("Oignon")){
                    topping5="6";
                }
                else if (topping5.equals("Pepperoni")){
                    topping1="7";
                }
                else if (topping5.equals("Sausage")||topping5.equals("Saucisson")){
                    topping5="8";
                }
                else if (topping5.equals("Bacon")||topping5.equals("Lard")){
                    topping5="9";
                }
                topping6 = spinnerTopping5.getSelectedItem().toString();
                if(topping6.equals("Cheese")||topping6.equals("Fromage")){
                    topping6="1";
                }
                else if (topping6.equals("Mushroom")||topping6.equals("Champignon")){
                    topping6="2";
                }
                else if (topping6.equals("Pineapple")||topping6.equals("Ananas")){
                    topping6="3";
                }
                else if (topping6.equals("Green Pepper")||topping6.equals("Poivre vert")){
                    topping6="4";
                }
                else if (topping6.equals("Hot Pepper")||topping6.equals("Piment")){
                    topping6="5";
                }
                else if (topping6.equals("Onion")||topping6.equals("Oignon")){
                    topping6="6";
                }
                else if (topping6.equals("Pepperoni")){
                    topping6="7";
                }
                else if (topping6.equals("Sausage")||topping6.equals("Saucisson")){
                    topping6="8";
                }
                else if (topping6.equals("Bacon")||topping6.equals("Lard")){
                    topping6="9";
                }

                //add a contact- CREATE
                try {
                    db.open();
                    if (db.updateOrder(rowId, name, size, topping1, topping2, topping3, topping4, topping5, topping6))

                        Toast.makeText(this, "Update successful", Toast.LENGTH_LONG).show();

                    else

                        Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();


                    db.close();
                }
                catch(Exception e){
                    Log.e("Error","A database error occurred");
                }
                Intent intent= new Intent(EditOrder.this, ViewOrder.class);
                startActivity(intent);
            }//end else

        });//End Place order listener

        viewOrder.setOnClickListener(view->{
            Intent intent= new Intent(EditOrder.this, ViewOrder.class);
            startActivity(intent);
        });

        placeOrder.setOnClickListener(view -> {
            Intent intent= new Intent(EditOrder.this, PlaceOrder.class);
            startActivity(intent);});



        if (language.equals("French")) {


            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.FrenchToppings, android.R.layout.simple_spinner_item);

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.FrenchSizes, android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



            strings = res.getStringArray(R.array.French);

            // Apply the adapter to the spinner
            spinnerSizes.setAdapter(adapter2);
            spinnerTopping0.setAdapter(adapter);
            spinnerTopping1.setAdapter(adapter);
            spinnerTopping2.setAdapter(adapter);
            spinnerTopping3.setAdapter(adapter);
            spinnerTopping4.setAdapter(adapter);
            spinnerTopping5.setAdapter(adapter);

            placeOrder.setText(strings[0]);
            viewOrder.setText(strings[1]);
            editOrder.setText(strings[4]);

        }
        else if(language.equals("English")){


            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.EnglishToppings, android.R.layout.simple_spinner_item);

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.EnglishSizes, android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            strings = res.getStringArray(R.array.English);

            // Apply the adapter to the spinner
            spinnerSizes.setAdapter(adapter2);
            spinnerTopping0.setAdapter(adapter);
            spinnerTopping1.setAdapter(adapter);
            spinnerTopping2.setAdapter(adapter);
            spinnerTopping3.setAdapter(adapter);
            spinnerTopping4.setAdapter(adapter);
            spinnerTopping5.setAdapter(adapter);

            placeOrder.setText(strings[0]);
            viewOrder.setText(strings[1]);
            editOrder.setText(strings[4]);

    }

}
}