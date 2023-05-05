package sg.edu.rp.c346.id22030544.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText etAmount;
    EditText etNum;
    ToggleButton tbtnSvs;
    ToggleButton tbtnGst;
    EditText etDiscount;
    RadioGroup rgMethod;
    Button btnSplit;
    Button btnReset;
    TextView tvDisplay;
    double amount;
    double num;
    double total;
    String display;
    double splitAmount;
    double discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAmount = findViewById(R.id.editAmount);
        etNum = findViewById(R.id.editNum);
        tbtnSvs = findViewById(R.id.toggleButtonSvs);
        tbtnGst = findViewById(R.id.toggleButtonGst);
        etDiscount = findViewById(R.id.editDiscount);
        rgMethod = findViewById(R.id.radioGroupMethod);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tvDisplay = findViewById(R.id.textViewDisplay);









    btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String strAmount = etAmount.getText().toString();
               String strNum = etNum.getText().toString();
               String strDiscount = etDiscount.getText().toString();
                String amountWarning = "Please input Amount";


                if(strAmount.isEmpty()){   Toast toast = Toast.makeText(MainActivity.this, "Please Input Amount", Toast.LENGTH_LONG);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.RED);
                    toast.show();}
                else if (strNum.isEmpty()){        Toast toast = Toast.makeText(MainActivity.this, "Please Input Num of Pax", Toast.LENGTH_LONG);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.RED);
                    toast.show();}
                else if (strDiscount.isEmpty()){        Toast toast = Toast.makeText(MainActivity.this, "Please Input Discount", Toast.LENGTH_LONG);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.RED);
                    toast.show();}
                else if ((rgMethod.getCheckedRadioButtonId()!=R.id.radioButtonCash)&&(rgMethod.getCheckedRadioButtonId()!=R.id.radioButtonPayNow)){       Toast toast = Toast.makeText(MainActivity.this, "Please Select Payment Method", Toast.LENGTH_LONG);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.RED);
                    toast.show();}
                else{

                    amount=Double.parseDouble(strAmount);
                    total = amount;

                        if(tbtnSvs.isChecked()){total = total + amount *.1;}



                        if(tbtnGst.isChecked()){total = total * 1.08;}
                        discount = Double.parseDouble(strDiscount);
                    total=(100-discount)*total/100;
                display = String.format("%s%.2f\n","Total Bill: $",total);
                num=Double.parseDouble(strNum);
                splitAmount = total/num;
                display = String.format("%s%s%.2f",display,"Each Pays: $",splitAmount);
                if(rgMethod.getCheckedRadioButtonId()==R.id.radioButtonCash){
                    display = display + " in cash";
                }
                else {
                    display = display + "via PayNow to 99020302";
                }
                tvDisplay.setText(display);


            }}
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            etAmount.setText(null);
            etNum.setText(null);
            tvDisplay.setText(null);
            etDiscount.setText(null);
            tbtnSvs.setChecked(true);
            tbtnGst.setChecked(true);
            rgMethod.clearCheck();




            }
        });



    }
}