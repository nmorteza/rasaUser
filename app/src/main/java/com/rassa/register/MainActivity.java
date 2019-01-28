package com.rassa.register;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rassa.rassauser.RasaUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RasaUser rasaUser=new RasaUser(this,"http://79.175.155.143/Dorsaketab/api/");

       /* if (rasaUser.userIsRegistered()) {
            Log.d("TAG", "onCreate: "+rasaUser.getUserProfile().getKeyPhoneNumber("20"));
        }else{*/
            startActivityForResult(rasaUser.getIntentActivityRegisterUser(),100);
        //}


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100) {
            if (resultCode== Activity.RESULT_OK) {
                Log.d("TAG", "onCreate: RESULT_OK ");

            }else{
                Log.d("TAG", "onCreate: RESULT_CANCEL ");

            }
        }
    }
}
