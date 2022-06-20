
package com.example.video;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private com.example.video.Bird bird;
    private Handler handler;
    private  Runnable r;
    private ArrayList<Pipes> arrPipes;
    private int sumpipe ,distance;
    private int  score, bestscore=0;
    private boolean start ;
    private Context context;
    private int soundJump;
    private float volume;
    private boolean loadedsound;
    private SoundPool soundPool;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        SharedPreferences sp = context.getSharedPreferences("gamasetting", Context.MODE_PRIVATE);
        if (sp != null) {
            bestscore = sp.getInt("bestscore", 0);
        }
        score = 0;
        bestscore = 0;
        start = false;
        initBird();
        initPipe();
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        if(Build.VERSION.SDK_INT>=2){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(5);
            this.soundPool = builder.build();

        }else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loadedsound = true;

            }
        });

        soundJump =  this.soundPool.load(context, R.raw.jump_02, 2);
    }

    private void initPipe(){

        sumpipe = 6;
        distance = 300* Constants.SCREEN_HEIGHT/1920;
        arrPipes = new ArrayList<>();

        for (int i = 0; i< sumpipe ; i++){

            if(i<sumpipe/2){
                this.arrPipes.add(new Pipes ( Constants.SCREEN_WIDTH+i*((Constants.SCREEN_WIDTH+200* com.example.video.Constants.SCREEN_WIDTH/1000)/(sumpipe/2)),
                        0, 200* Constants.SCREEN_WIDTH/1000, Constants.SCREEN_HEIGHT/2));

                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(),R.drawable.pipe2));
                this.arrPipes.get(this.arrPipes.size()-1).randomY();
            }else{
                this.arrPipes.add(new Pipes(this.arrPipes.get(i-sumpipe/2).getX(), this.arrPipes.get(i-sumpipe/2).getY()
                        +this.arrPipes.get(i-sumpipe/2).getHeight()+this.distance,200* Constants.SCREEN_WIDTH/1080, com.example.video.Constants.SCREEN_HEIGHT/2));
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe2));

            }


        }


    }
    private void initBird(){
        bird = new com.example.video.Bird();
        bird.setWidth(100* com.example.video.Constants.SCREEN_WIDTH/1080);
        bird.setHeight(100* com.example.video.Constants.SCREEN_HEIGHT/1920);
        bird.setX(100* com.example.video.Constants.SCREEN_WIDTH/1080);
        bird.setY(com.example.video.Constants.SCREEN_HEIGHT/2-bird.getHeight()/2);
        ArrayList<Bitmap>arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.bird1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.bird2));
        bird.setArrBms(arrBms);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if(start){
            bird.draw(canvas);
            for(int i = 0; i < sumpipe; i++){

                if(bird.getRect().intersect(arrPipes.get(i).getRect())||bird.getY()*bird.getHeight()<0|| bird.getY()> com.example.video.Constants.SCREEN_HEIGHT){
                    Pipes.speed=0;
                    com.example.video.GameActivity.txt_score_over.setText(com.example.video.GameActivity.txt_score.getText());
                    com.example.video.GameActivity.txt_best_score.setText("best: " +bestscore);
                    com.example.video.GameActivity.txt_score.setVisibility(INVISIBLE);
                    com.example.video.GameActivity.rl_game_over.setVisibility(VISIBLE);

                }
                if(this.bird.getX()+this.bird.getWidth()>arrPipes.get(i).getX()+arrPipes.get(i).getWidth()/2
                        && this.bird.getX()+this.bird.getWidth()<=arrPipes.get(i).getX()+arrPipes.get(i).getWidth()/2+Pipes.speed
                        &&i<sumpipe/2)

                {
                    score++;
                    if(score>bestscore){
                        bestscore=score;
                        SharedPreferences sp =context.getSharedPreferences("gamesetting",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("bestscore", bestscore);
                        editor.apply();
                    }
                    com.example.video.GameActivity.txt_score.setText(""+score);


                }
                if(this.arrPipes.get(i).getX() <-arrPipes.get(i).getWidth()){
                    this.arrPipes.get(i).setX(com.example.video.Constants.SCREEN_WIDTH);
                    if(i< sumpipe/2){
                        arrPipes.get(i).randomY();
                    }else{
                        arrPipes.get(i).setY(this.arrPipes.get(i-sumpipe/2).getHeight()+this.distance);
                    }
                }

                this.arrPipes.get(i).draw(canvas);
            }

        }else{
            if(bird.getY()> com.example.video.Constants.SCREEN_HEIGHT/2){
                bird.setDrop(-15* com.example.video.Constants.SCREEN_HEIGHT/1920);
            }

            bird.draw(canvas);
        }

        handler.postDelayed(r,10);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

    if(event.getAction() == MotionEvent.ACTION_DOWN){
        bird.setDrop(-15);
        if(loadedsound){
            int streamed = this.soundPool.play(this.soundJump, (float)0.5, (float)0.5, 1,0,1f);
        }

    }
    return true;

    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void reset(){
       com.example.video.GameActivity.txt_score.setText("0");
       score = 0;
       initPipe();
       initBird();
    }


}


