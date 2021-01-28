package com.example.bloodbank.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.bloodbank.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.load_image);

        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
         animationDrawable.start();

        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },2000);

    }
}
