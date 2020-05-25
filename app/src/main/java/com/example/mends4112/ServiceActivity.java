package com.example.mends4112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity {
//   Percy mends 216it02004112

//   Variables
   static String numDays;
   static String medCharges;
   static String surgCharges;
   static String labFees;
   static String physicalRehab;
   TextView viewReport;
      String emailHolder;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_service);

//       Finding  views for inputs
      final EditText editDays = findViewById(R.id.editDays);
      final EditText editMedication = findViewById(R.id.editMedication);
      final EditText editSurgical = findViewById(R.id.editSurgical);
      final EditText editLab = findViewById(R.id.editLab);
      final EditText editRehab = findViewById(R.id.editRehab);

//      finding TextView
       viewReport = findViewById(R.id.viewReport);

//      finding button views
      findViewById(R.id.btnCalculate)
      .setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            numDays = editDays.getText().toString().trim();
            medCharges = editMedication.getText().toString().trim();
            surgCharges = editSurgical.getText().toString().trim();
            labFees =editLab.getText().toString().trim();
            physicalRehab = editRehab.getText().toString().trim();
            if (TextUtils.isEmpty(numDays) &&TextUtils.isEmpty(medCharges) &&
                    TextUtils.isEmpty(surgCharges) &&TextUtils.isEmpty(labFees) &&
                    TextUtils.isEmpty(physicalRehab) )
            {
               Toast.makeText(ServiceActivity.this,"Please fill out all fields"
                       ,Toast.LENGTH_LONG).show();
            }
            else {
               CalcTotalCharges();
            }



         }
      });
//      end of Calculate Button

      findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           editDays.setText(" ");
           editMedication.setText(" ");
           editSurgical.setText(" ");
           editLab.setText(" ");
           editRehab.setText(" ");
           viewReport.setText(" ");
         }
      });

//         THE SEND BILL AS EMAIL BUTTON
      findViewById(R.id.btnSend)
              .setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent it = new Intent(Intent.ACTION_SEND);
                    it.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@hcApp.com"});
                    it.putExtra(Intent.EXTRA_SUBJECT, "Welcome to hcApp HOSPITAL BILL");
                    it.putExtra(Intent.EXTRA_TEXT, emailHolder);
                    it.setType("message/rfc822");
                    startActivity(Intent.createChooser(it,"Choose Mail App"));
                 }
              });

   }


   private  double CalcMiscCharges(){
      double total;
      total = Double.parseDouble(medCharges + surgCharges
              + labFees + physicalRehab);
      return total;
   }
   private  double CalcStayCharges(){
      double baseCharge;
      baseCharge = 500 * Double.parseDouble(numDays);
      return baseCharge;

   }


   private  double CalcTotalCharges() {
      double total;
      total = CalcMiscCharges() + CalcStayCharges();

            String MC = String.valueOf(CalcMiscCharges());
            String SC = String.valueOf(CalcStayCharges());

         String MedCharge = "Medication Charge is: $" + MC;
         String StayC = " Stay Charge is: $"+SC;
         String totalValue = String.valueOf(total);

      emailHolder = new StringBuilder()
              .append(MedCharge)
              .append("\n")
              .append(StayC)
              .append("\n")
              .append("" +
                      "The total cost is: $" + totalValue)
              .toString();


      viewReport.setText(emailHolder);

      return total;

   }
}
