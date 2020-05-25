package com.example.mends4112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
//      Percy mends 216it02004112
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      findViewById(R.id.continueBtn)
              .setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,ServiceActivity.class));
                 }
              });

   }
}
