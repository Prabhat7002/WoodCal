package com.prabhat.woodcal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView totalKB, totalPrice,ErrorDisplay;
    EditText Length, Breadth, Height, Rate, Quantity;
    Button cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cal = (Button) findViewById(R.id.btCal);
        totalKB = (TextView) findViewById(R.id.kb);
        totalPrice = (TextView) findViewById(R.id.price);

        Length = (EditText) findViewById(R.id.len);
        Breadth = (EditText) findViewById(R.id.brd);
        Height = (EditText) findViewById(R.id.hght);
        Quantity = (EditText) findViewById(R.id.quan);
        Rate = (EditText) findViewById(R.id.rate);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String KB = String.valueOf(calKb());
                //      String RoundedKB = String.format("%.3f", KB);
                String Price = String.valueOf(calRate());
                //      String RoundedPrice = String.format("%.3f", Price);

                totalKB.setText(KB);
                totalPrice.setText(Price);
            }
        });
    }


    public double calKb() {
        String length = Length.getText().toString();
        String breadth = Length.getText().toString();
        String height = Length.getText().toString();
        String quantity = Length.getText().toString();
        String rate = Rate.getText().toString();

        if(length.isEmpty() || breadth.isEmpty()|| height.isEmpty() || quantity.isEmpty() || rate.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter all values.",Toast.LENGTH_LONG).show();
            return -1;
        }


        double len = Double.parseDouble(String.valueOf(Length.getText()));
        double brdth = Double.parseDouble(String.valueOf(Breadth.getText()));
        double hght = Double.parseDouble(String.valueOf(Height.getText()));



        //Define a range for inputs of Length,Breadth and Height of timber
        // We know that the tallest tree is 400 foot so we take it's as the extreme level/boundary value

//        if(len<=0 || len>400 || brdth<=0 || brdth>400 || hght<=0 || hght>400){
//            ErrorDisplay.setText(R.string.BoundaryCaseError);
//           // return -1;
//        }
        double kb = (len*brdth*hght)/144;
        int quan = Integer.parseInt(String.valueOf(Quantity.getText()));


        // Constraint for Quantity

//        if(quan<0) {
//            ErrorDisplay.setText("Enter valid range: 0 to 1000");
//           // return -1;
//        }
        kb *=quan;

        return kb;
    }

    public double calRate() {
        double price = Integer.parseInt(String.valueOf(Rate.getText()));
        double KB = calKb();


        // Create Boundaries for KB

//        if(KB<0 || KB>400){
//            ErrorDisplay.setText("Enter valid range: 0 to 400");
//            return -1;
//        }
//       double totalRate = price * KB;

        return price*KB;
    }
}