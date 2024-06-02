package com.prabhat.woodcal;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

// KB - cubic feet

// To do - 1. Handle crash when values entered     2. Add History Page

// Features to be added: 1. Print Bill Feature
// 1.a. Create a Database to store the values.
// 2.a. Create a activity/page that displays the bill by retrieving values from database(temporary database).// Add customer name and other details
// 3.a. Convert it in a pdf and save it and add print feature.

public class MainActivity extends AppCompatActivity {

    TextView KB, Price, totalKB, totalPrice;
    EditText Length, Breadth, Height, Rate, Quantity;
    Button cal, add, reset, resetVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Quantity.setText("1");
//        Rate.setText("0");
        cal =  findViewById(R.id.btCal);
        add = findViewById(R.id.btAdd);
        reset = findViewById(R.id.btReset);
        resetVal = findViewById(R.id.btResetVal);
        KB =  findViewById(R.id.kb);
        Price =  findViewById(R.id.price);

        Length =  findViewById(R.id.len);
        Breadth =  findViewById(R.id.brd);
        Height = findViewById(R.id.hght);
        Quantity = findViewById(R.id.quan);
        Rate = findViewById(R.id.rate);

        //double totalKB, totalPrice;

        totalKB = findViewById(R.id.totalKB);
        totalPrice = findViewById(R.id.totalPrice);

        cal.setOnClickListener(v -> {

            String kb = String.valueOf(calKb());
            //      String RoundedKB = String.format("%.3f", KB);
            String price = String.valueOf(calRate());
            //      String RoundedPrice = String.format("%.3f", Price);

//            if(KB.equals("-1") || KB.equals("-1.0") ) {
//                String value = "Enter all Values.";
//                ErrorDisplay.setText(value);
//            }

            KB.setText(kb);
            Price.setText(price);
        });

        // Calculating and displaying TotalKB
//         adding the previous calculated KB to the new one's to get  KB
        add.setOnClickListener(view -> {

            add.setEnabled(false); // Disable the button

            String totalKb = String.valueOf(calTotalKb());
            totalKB.setText(totalKb);

            String TotalPrice = String.valueOf(calTotalPrice());
            totalPrice.setText(TotalPrice);

            // Enable the button after a short delay (e.g., 1 second)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    add.setEnabled(true); // Enable the button
                }
            }, 2000); // 1000 milliseconds = 1 second
        });

        // Resetting all values
        resetVal.setOnClickListener(view -> {
            Length.setText("");
            Breadth.setText("");
            Height.setText("");
            Quantity.setText("");
            Rate.setText("");
            KB.setText("");
            Price.setText("");
        });


        // Resetting the total price and KB
        reset.setOnClickListener(view -> {
            totalKB.setText("");
            totalPrice.setText("");
            totalKb = 0;
        });


    }



    // Function to calculate TotalPrice and KB
    double totalKb = 0;
    public double calTotalKb(){
        String kb = String.valueOf(calKb());
        double KB = Double.parseDouble(kb);
        totalKb = totalKb+ KB ;

        // Create a DecimalFormat object with the desired format
        DecimalFormat df = new DecimalFormat("#.##");

        // Format the value of kb
        String roundedTotalKb = df.format(totalKb);

        // Convert the rounded value back to a double

        return Double.parseDouble(roundedTotalKb);

    }

    public double calTotalPrice(){
        double rate = Integer.parseInt(String.valueOf(Rate.getText()));
        double totalPrice = totalKb*rate;

        // Create a DecimalFormat object with the desired format
        DecimalFormat df = new DecimalFormat("#.##");

        // Format the value of kb
        String roundedTotalPrice = df.format(totalPrice);

        // Convert the rounded value back to a double

        return Double.parseDouble(roundedTotalPrice); // kb of wood
    }



    public double calKb() {
        String length = Length.getText().toString();
        String breadth = Breadth.getText().toString();
        String height = Height.getText().toString();
        String quantity = Quantity.getText().toString();
        String rate = Rate.getText().toString();

        if(length.isEmpty() || breadth.isEmpty()|| height.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter all values.",Toast.LENGTH_LONG).show();
            return -1;
        }

        if(quantity.isEmpty())
            Quantity.setText("1");

        if(rate.isEmpty())
            Rate.setText("0");


        double len = Double.parseDouble(String.valueOf(Length.getText()));
        double brdth = Double.parseDouble(String.valueOf(Breadth.getText()));
        double hght = Double.parseDouble(String.valueOf(Height.getText()));



        //Define a range for inputs of Length,Breadth and Height of timber
        // We know that the tallest tree is 400 foot so we take it's as the extreme level/boundary value

//        if(len<=0 || len>400 || brdth<=0 || brdth>400 || hght<=0 || hght>400){
//            ErrorDisplay.setText(R.string.BoundaryCaseError);
//           // return -1;
//        }
        double kb = (len*brdth*hght)/144; // kb of single piece

        int quan = Integer.parseInt(String.valueOf(Quantity.getText()));

        // Constraint for Quantity

//        if(quan<0) {
//            ErrorDisplay.setText("Enter valid range: 0 to 1000");
//           // return -1;
//        }
        kb *=quan; // kb = kb*quan

        // Create a DecimalFormat object with the desired format
        DecimalFormat df = new DecimalFormat("#.##");

        // Format the value of kb
        String roundedKb = df.format(kb);

        // Convert the rounded value back to a double

        return Double.parseDouble(roundedKb); // kb of wood
    }

    public double calRate() { // CalPrice
        double rate = Integer.parseInt(String.valueOf(Rate.getText()));
        double KB = calKb();
        double price = rate*KB;

        // Create Boundaries for KB

//        if(KB<0 || KB>400){
//            ErrorDisplay.setText("Enter valid range: 0 to 400");
//            return -1;
//        }
//       double Rate = price * KB;

        // Create a DecimalFormat object with the desired format
        DecimalFormat df = new DecimalFormat("#.##");

        // Format the value of kb
        String roundedPrice = df.format(price);

        // Convert the rounded value back to a double

        return Double.parseDouble(roundedPrice);
    }
}