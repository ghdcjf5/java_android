package e.kcci.molekiller_180425;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;



/**
 * Created by KCCI on 2018-04-25.
 */
//생성자는 클래스와 이름이 똑같고 리턴이 없다.
public class CatchMoleView extends View {
   // Bitmap backGround;  //backGround는 참조변수
    public Bitmap Mole, Mole_pre,Mole_hurt,View_level;
    public MediaPlayer sound_mole,sound_catch;
    public int m_x,m_y,m_mode; //두더지 튀어나올 좌표, 두더지 모드(튀어나올것인지 empty일 것인지)
    public int m_touchx,m_touchy;
    public  int screen_width,screen_height;
    public final int DRAW_LEVEL =0; //background
    public final int DRAW_MOLE =1; //두더지
    public final int DRAW_PRE =2; //흙만있는이미지
    public final int DRAW_HURT =3;
    public  Integer m_score;
    public int m_level=1;
    public Bitmap[] number;
    public Paint p;

    public CatchMoleView(Context context){
        super(context);
      //  backGround = BitmapFactory.decodeResource(getResources(),R.mipmap.mole_background);
        Mole = BitmapFactory.decodeResource(getResources(),R.mipmap.mole);
        Mole_pre = BitmapFactory.decodeResource(getResources(),R.mipmap.mole_empty);
        Mole_hurt = BitmapFactory.decodeResource(getResources(),R.mipmap.mole_hurt);
        View_level = BitmapFactory.decodeResource(getResources(),R.mipmap.level);
        sound_mole = MediaPlayer.create(context,R.raw.s_mole);
        sound_catch = MediaPlayer.create(context,R.raw.catch_mole);
        m_score = 0;
        number = new Bitmap[]{
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_0),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_1),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_2),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_3),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_4),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_5),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_6),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_7),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_8),
                BitmapFactory.decodeResource(getResources(),R.mipmap.num_9),

        };


        p = new Paint();
        p.setTextSize(50);
        p.setColor(Color.WHITE);
        moleThread thread = new moleThread(this);
        thread.start();

    }


    //overload는 같은 이름의 함수를 여러개 생성할때 사용. 단, 매개변수타입이나 갯수가 달라야한다.
    @Override //조상클래스(catchmoleview)에 view를 상속시켰는데 view클래스안에 있는 ondraw를
    //조상클래스에서 내가 새로 수정하고 싶기에 override시키는것이다.

    //출력해주는곳
    protected void onDraw(Canvas canvas) {
        setBackgroundResource(R.mipmap.mole_background);

        if(m_mode == DRAW_LEVEL){
          //  canvas.scale(0.5f,0.5f,getWidth()/2,getHeight()/2);
            canvas.drawBitmap(View_level,(getWidth()-View_level.getWidth())/2,
                    ( getHeight()-View_level.getHeight())/2,null);

            canvas.drawBitmap(number[m_level],(getWidth()-View_level.getWidth())/2+170,
                    ( getHeight()-View_level.getHeight())/2+5,null);
        }
        else{
            if(m_mode==DRAW_MOLE){
                canvas.drawBitmap(Mole,m_x,m_y,null);

                sound_mole.start();
            }
            else if(m_mode==DRAW_PRE){
                canvas.drawBitmap(Mole_pre,m_x,m_y,null);

            }
            else if(m_mode == DRAW_HURT){
                canvas.drawBitmap(Mole_hurt,m_x,m_y,null);

                sound_catch.start();
            }
            // canvas.drawText(m_score.toString(),this.getWidth()-220,this.getHeight()-120,p);


            if(m_score>=1000) canvas.drawBitmap(number[m_score/1000%10],getWidth()-220,getHeight()-100,null);
            if(m_score>=100) canvas.drawBitmap(number[m_score/100%10],getWidth()-170,getHeight()-100,null);
            if(m_score>=10) canvas.drawBitmap(number[m_score/10%10],getWidth()-120,getHeight()-100,null);
            canvas.drawBitmap(number[m_score%10],getWidth()-70,getHeight()-100,null);




        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_touchx = (int) event.getX();
        m_touchy =  (int) event.getY();
        Rect hit_box =new Rect(m_x,m_y,m_x + getWidth()/5,m_y + getHeight()/7);
        if(event.getAction() == MotionEvent.ACTION_DOWN){//두더지를 손을 화면에 문대서 잡는거방지
            if(m_mode == DRAW_MOLE && hit_box.contains(m_touchx, m_touchy)){ //포함되면 true 아니면 false 리턴
                m_mode = DRAW_HURT;
                m_score+=100;
                invalidate();//화면 다시그리기
            }

        }
        return super.onTouchEvent(event);
    }
}
