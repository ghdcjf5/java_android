package com.example.kcci.combobjo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//추상클래스는 몸체가 없는것 (c에서 프로토타입같은 느낌)
public class MainActivity extends AppCompatActivity {
    FrameLayout backGround;
    Animation anim_exam, anim_cloud1, anim_cloud2, anim_cloud3, anim_opening, anim_walking,
            anim_pig_center_upmotion,anim_pig_left_upmotion,anim_pig_right_upmotion;
    ImageView cloud1, cloud2, cloud3, pigWalking,pig_up;
    ImageButton btnMole,btnCard;
    MediaPlayer bgm_title,bgm_walk, bgm_sideup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backGround = (FrameLayout)findViewById(R.id.back_ground);
        cloud3 = (ImageView)findViewById(R.id.cloud1);
        cloud2 = (ImageView)findViewById(R.id.cloud2);
        cloud1 = (ImageView)findViewById(R.id.cloud3);
        pigWalking = (ImageView)findViewById(R.id.pig_walking);
        pig_up = (ImageView)findViewById(R.id.pig_up);

        btnCard = (ImageButton)findViewById(R.id.button_card);
        btnMole = (ImageButton)findViewById(R.id.button_mole);

        bgm_title = MediaPlayer.create(this,R.raw.bg);
        bgm_walk = MediaPlayer.create(this,R.raw.walk);
        bgm_sideup = MediaPlayer.create(this,R.raw.sideup);

        anim_exam = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anima_exam);
        anim_cloud1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.cloud1);
        anim_cloud2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.cloud2);
        anim_cloud3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.cloud3);
        anim_opening = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.opening);
        anim_walking = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pig_walking);
        anim_pig_center_upmotion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pig_center_up);
        anim_pig_left_upmotion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pig_left_up);
        anim_pig_right_upmotion =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pig_right_up);

        anim_opening.setAnimationListener(new Animation.AnimationListener() {
            @Override  //override이지만 implement한것 추상클래스를 구현한것
            //49줄에서 I 아이콘이 있는게 추상클래스를 구현했다는 뜻
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cloud1.startAnimation(anim_cloud1);
                cloud2.startAnimation(anim_cloud2);
                cloud3.startAnimation(anim_cloud3);
                pigWalking.setVisibility(View.VISIBLE);
                pigWalking.startAnimation(anim_walking);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim_walking.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bgm_walk.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) { //END는 끝나면 다음에 수행할 곳으로 이동
                pigWalking.setVisibility(View.INVISIBLE); //걸어나오다가 사라진다.
                pig_up.setVisibility(View.VISIBLE);

                pig_up.setRotation(-45);
                pig_up.startAnimation(anim_pig_right_upmotion);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
       anim_pig_right_upmotion.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {
               bgm_sideup.start();
           }

           @Override
           public void onAnimationEnd(Animation animation) {
               pig_up.setRotation(45);
               pig_up.startAnimation(anim_pig_left_upmotion);
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });
        anim_pig_left_upmotion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bgm_sideup.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pig_up.setRotation(0);
                pig_up.startAnimation(anim_pig_center_upmotion);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim_pig_center_upmotion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bgm_sideup.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnMole.setVisibility(View.VISIBLE);
                btnCard.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        backGround.startAnimation(anim_opening);
        bgm_title.start();
    }
}
