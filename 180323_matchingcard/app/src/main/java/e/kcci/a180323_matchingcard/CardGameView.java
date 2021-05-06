

package e.kcci.a180323_matchingcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas; //바탕
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect; //
import android.view.MotionEvent; //터치입력
import android.view.View; //View는 라이브러리다. 누군가가 만들어 놓은것

/**
 * Created by KCCI on 2018-03-23.
 */
//public protect private 접근제한자에 보통 return타입(없으면 void)가 있다.


//extends를 사용하여 View를 상속시킨다.
public class CardGameView extends View { //view content로 쓴다.

    Bitmap m_Background; //m은 member변수
    Bitmap m_CardBackside;
    Bitmap m_Cardirin;
    Bitmap m_Cardjoy;
    Bitmap m_Cardyeri;
    Bitmap m_Cardbabysoul;
    Bitmap m_Cardjin;
    Bitmap m_Cardjungyein;
    Bitmap m_Cardkei;
    Bitmap m_Cardleemiju;
    Bitmap m_Cardryusujeong;
    Bitmap m_Cardseojisu;
    Bitmap m_Cardyujiae;



    int x_padding = 100;
    int y_padding = 300;
    int offset_card_x, offset_card_y;
    int card_x,  card_y;
    int card_width, card_height;
    Card m_Shuffle[][]; //3x2 이중배열로 만든다. 참조변수
    Card m_SelectedCard_1,m_SelectedCard_2; //참조변수

   /* int[] m_CardId = {R.mipmap.num1, R.mipmap.num2, R.mipmap.num3,
            R.mipmap.num4, R.mipmap.num5, R.mipmap.num6,
            R.mipmap.num7, R.mipmap.num8};*/
    int m_column,m_row;
    public static final int STATE_READY = 0;
    public static final int STATE_GAME = 1;
    public static final int STATE_END = 2;
    int STATE_stage_flag = 0;

    int th_flag=1;

    int m_state = STATE_READY;

//클래스에는 생성자가 있어야 한다. 생성자는 return type(void)이 없다. 즉 리턴할게 없다.
    public CardGameView(Context context){
        super(context);
        m_Background = BitmapFactory.decodeResource(getResources(),R.mipmap.background);
        m_CardBackside = BitmapFactory.decodeResource(getResources(),R.mipmap.backside);
        m_Cardjoy = BitmapFactory.decodeResource(getResources(),R.mipmap.img_joy);
        m_Cardyeri = BitmapFactory.decodeResource(getResources(),R.mipmap.img_yeri);
        m_Cardirin = BitmapFactory.decodeResource(getResources(),R.mipmap.img_irin);
        m_Cardbabysoul = BitmapFactory.decodeResource(getResources(),R.mipmap.img_baby);
        m_Cardjin = BitmapFactory.decodeResource(getResources(),R.mipmap.img_jin);
        m_Cardjungyein = BitmapFactory.decodeResource(getResources(),R.mipmap.img_jungyein);
        m_Cardkei = BitmapFactory.decodeResource(getResources(),R.mipmap.img_kei);
        m_Cardleemiju = BitmapFactory.decodeResource(getResources(),R.mipmap.img_leemiju);
        m_Cardryusujeong = BitmapFactory.decodeResource(getResources(),R.mipmap.img_ryusujeong);
        m_Cardseojisu = BitmapFactory.decodeResource(getResources(),R.mipmap.img_seojisu);
        m_Cardyujiae = BitmapFactory.decodeResource(getResources(),R.mipmap.img_yujiae);



            m_column = 3;
            m_row = 2;
        offset_card_x = (this.getWidth()-m_Cardirin.getWidth()*m_column)/(m_column+1);
        offset_card_y = ((this.getHeight()*4/5)-m_Cardirin.getHeight()*m_row)/(m_row+1);
        card_width = m_Cardirin.getWidth();
        card_height = m_Cardirin.getHeight();

        m_Shuffle = new Card[m_column][m_row];  //3x2배열 만듦 생성자만들었다.

        Card_Shuffle(m_column,m_row);
        th_flag=1;
        cardgamethread _thread = new cardgamethread(this);//thread자체를 호출(this)
        _thread.start();//thread 동작하게 start해준다.


    }

    void Card_Shuffle(int column,int row){
        if(STATE_stage_flag==0){
            for(int y=0; y<row;y++){
                for(int x=0; x<column;x++){
                    m_Shuffle[x][y] = new Card((y*column+x)/2);
                    //012 두개씩 순서대로 나오게 2로 나눠줌
                    //0 0 1 1

                }

            }


        }

        else if(STATE_stage_flag==1){

            for(int y=0; y<row;y++){
                for(int x=0; x<column;x++){
                    m_Shuffle[x][y] = new Card(((y*column+x)/2)+3);
                    //01234 두개씩 순서대로 나오게 2로 나눠줌

                }

            }


        }

        //카드섞는부분
        for(int i=0;i<300;i++) {
            int x = (int) (Math.random() * column);
            int y = (int) (Math.random() * row);

            m_SelectedCard_1 = m_Shuffle[x][y];
            x = (int) (Math.random() * column);
            y = (int) (Math.random() * row);
            m_SelectedCard_2 = m_Shuffle[x][y];
            int temp = m_SelectedCard_1.m_Image;
            m_SelectedCard_1.m_Image = m_SelectedCard_2.m_Image;
            m_SelectedCard_2.m_Image = temp;
        }
        m_SelectedCard_1 = null;
        m_SelectedCard_2 = null;

    }



/*
    private  int m_x;
    private  int m_y;
    private  String m_strAction = ""; //초기화
*/

//onDraw를 override를 해서 화면을 보여줘야한다.
    @Override
    protected void onDraw(Canvas canvas) {
//view는 ondraw함수가 있어야한다.

        setBackgroundResource(R.mipmap.background);
        if (m_state==STATE_READY){

            All_Card_View(canvas);



        }
        else if(m_state==STATE_GAME){

            for(int y=0;y<m_row;y++){
                for(int x=0;x<m_column;x++) {
                    if (m_Shuffle[x][y].m_state == Card.CARD_CLOSE) {
                        View_Backside_Image(canvas, x, y);


                    }
                   /* else if (m_Shuffle[x][y].m_state == Card.CARD_PLAYOPEN) {

                        View_Front_Image(canvas, x, y);
                    }
                    else if (m_Shuffle[x][y].m_state == Card.CARD_MATCH){

                        View_Front_Image(canvas, x, y);

                    }*/

                    else { View_Front_Image(canvas, x,y);}





                }

            }




        }

        else if (m_state == STATE_END){
            Paint paint = new Paint();
            paint.setTextSize(36);
            paint.setColor(Color.YELLOW);
            String str = "touch to next stage";
            canvas.drawText(str,80,580,paint);


        }

        invalidate();
        return;
        /*Paint pRed = new Paint(); //p라는 paint 클라스만든다.
        Paint pBlue = new Paint();
        pRed.setTextSize(35);
        pBlue.setTextSize(35);
        pRed.setColor(Color.RED);
        pBlue.setColor(Color.BLUE);

        canvas.drawText("cordinate "+ m_x+ ", "+m_y,10,50, pRed); //x좌표 10 y좌표 20
        canvas.drawText("Event Action : "+m_strAction, 10,100,pBlue);
        //event action은 터치하면 action down, 터치를 떄면 action up, 터치시에 움직이면 action move
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int px = (int)event.getX();
        int py = (int)event.getY();
        int card_x;
        int card_y;
        offset_card_x = (this.getWidth()-m_Cardirin.getWidth()*m_column)
                /(m_column+1);
        offset_card_y = ((this.getHeight()*4/5)-m_Cardirin.getHeight()*m_row)
                /(m_row+1);
        card_width = m_Cardirin.getWidth();
        card_height = m_Cardirin.getHeight();
        if(m_state == STATE_READY){  //m_state는 게임의 상태
            m_state = STATE_GAME;
            for(int y = 0; y<m_row;y++){
                for(int x=0; x<m_column;x++){


                    m_Shuffle[x][y].m_state = Card.CARD_CLOSE;

                }

            }

        }


        else if(m_state == STATE_GAME){
            for(int y = 0; y<m_row;y++){
                for(int x=0; x<m_column;x++){
                    offset_card_x = (this.getWidth()-m_Cardirin.getWidth()*m_column)
                            /(m_column+1);
                    offset_card_y = ((this.getHeight()*4/5)-m_Cardirin.getHeight()*m_row)
                            /(m_row+1);
                    card_width = m_Cardirin.getWidth();
                    card_height = m_Cardirin.getHeight();
//rect함수는 좌표 2개를 지정해주면 사각형을 그린다.
                    card_x = offset_card_x*(x+1) + card_width*x;
                    card_y = this.getHeight()/5+offset_card_y*(y+1) +card_height*y;

                    Rect box_card = new Rect(card_x, card_y,
                            card_x+card_width, card_y+card_height);
                    if(box_card.contains(px, py)){
                        if(m_Shuffle[x][y].m_state == Card.CARD_CLOSE){

                            if(m_SelectedCard_1 == null){
                                m_Shuffle[x][y].m_state = Card.CARD_PLAYOPEN;
                                m_SelectedCard_1 = m_Shuffle[x][y];
                            }
                            else if(m_SelectedCard_2 == null){
                                m_Shuffle[x][y].m_state = Card.CARD_PLAYOPEN;
                                m_SelectedCard_2 = m_Shuffle[x][y];

                            }
                            //기존에는  m_Shuffle[x][y].m_state = Card.CARD_PLAYOPEN;가 if elseif구문
                            //밖에있었는데 이때에는 3개의 카드가 빠르게 눌렸을경우 제3의 playopen카드가발생
                            //따라서 각 if문 안에 넣어줘서 이를 방지


                        }


                        //랜덤하게 카드 셔플


                    }
                }

            }


        /*  if(m_SelectedCard_1==null || m_SelectedCard_2 == null){


          }

          else if(m_SelectedCard_1.m_Image == m_SelectedCard_2.m_Image){
                m_SelectedCard_1.m_state = Card.CARD_MATCH;
                m_SelectedCard_2.m_state = Card.CARD_MATCH;


          }

          else{
              m_SelectedCard_1.m_state = Card.CARD_CLOSE;
              m_SelectedCard_2.m_state = Card.CARD_CLOSE;


          }*/

         /*   m_x = (int)event.getX();
            m_y = (int)event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                m_strAction = "Action down";
                //action_down은 상수다.
            }
            else if(event.getAction() == MotionEvent.ACTION_MOVE){
                m_strAction = "Action move";

            }

            else if(event.getAction() == MotionEvent.ACTION_UP){
                m_strAction = "Action up";

            }*/


        }

        else {   //게임 end일때
            STATE_stage_flag =1;
            th_flag=0;

            m_row =4;
            m_column =4;
            m_Shuffle = new Card[m_column][m_row];
            Card_Shuffle(m_column,m_row);

            m_state = STATE_READY;
            cardgamethread _thread = new cardgamethread(this);//thread자체를 호출(this)
            th_flag=1;
            _thread.start();//thread 동작하게 start해준다.

        }
        invalidate();//안드로이드에게 화면을 호출하게 닥달하는것
        return super.onTouchEvent(event); //빠른시간 두번터치를 하는것을 방지 혹은 false로 해도된다.
        //여러번 입력을 받아야할때 ture를 준다.
                //getX는 X좌표값을 가져온다.
    }




    public void All_Card_View(Canvas canvas){
        for(int y = 0; y<m_row;y++) {
            for (int x = 0; x < m_column; x++) {
                View_Front_Image(canvas,x,y);
            }
        }
    return;
    }


    public void View_Front_Image (Canvas canvas, int x,int y) {
        offset_card_x = (this.getWidth()-m_Cardirin.getWidth()*m_column)
                /(m_column+1);
        offset_card_y = ((this.getHeight()*4/5)-m_Cardirin.getHeight()*m_row)
                /(m_row+1);
        card_width = m_Cardirin.getWidth();
        card_height = m_Cardirin.getHeight();
        card_x = offset_card_x*(x+1) + card_width*x;
        card_y = this.getHeight()/5+offset_card_y*(y+1) +card_height*y;

            switch (m_Shuffle[x][y].m_Image) {
                case Card.img_joy:
                    canvas.drawBitmap(m_Cardjoy,  card_x, card_y, null);
                    break;
                case Card.img_yeri:
                    canvas.drawBitmap(m_Cardyeri, card_x, card_y, null);
                    break;
                case Card.img_irin:
                    canvas.drawBitmap(m_Cardirin,  card_x, card_y, null);
                    break;

                case Card.img_baby:
                    canvas.drawBitmap(m_Cardbabysoul,  card_x, card_y, null);
                    break;
                case Card.img_jin:
                    canvas.drawBitmap(m_Cardjin,  card_x, card_y, null);
                    break;
                case Card.img_jungyein:
                    canvas.drawBitmap(m_Cardjungyein,  card_x, card_y, null);
                    break;
                case Card.img_kei:
                    canvas.drawBitmap(m_Cardkei,  card_x, card_y, null);
                    break;
                case Card.img_leemiju:
                    canvas.drawBitmap(m_Cardleemiju, card_x, card_y, null);
                    break;
                case Card.img_ryusujeong:
                    canvas.drawBitmap(m_Cardryusujeong,  card_x, card_y, null);
                    break;
                case Card.img_seojisu:
                    canvas.drawBitmap(m_Cardseojisu,  card_x, card_y, null);
                    break;
                case Card.img_yujiae:
                    canvas.drawBitmap(m_Cardyujiae,  card_x, card_y, null);
                    break;

            }





    return;
    }
    public void View_Backside_Image (Canvas canvas, int x,int y){
        offset_card_x = (this.getWidth()-m_Cardirin.getWidth()*m_column)/(m_column+1);
        offset_card_y = ((this.getHeight()*4/5)-m_Cardirin.getHeight()*m_row)/(m_row+1);
        card_width = m_Cardirin.getWidth();
        card_height = m_Cardirin.getHeight();
        card_x = offset_card_x*(x+1) + card_width*x;
        card_y = this.getHeight()/5+offset_card_y*(y+1) +card_height*y;
        canvas.drawBitmap(m_CardBackside,
                card_x, card_y, null);
        return;

    }
//객체는 참조변수다. 변수가 아니다. m_shuffle은 참조변수여서 주소값이 들어간다. shuffle은 카드의 주소값으로 만들어져있따.
    //new card를 만들었으니까 card가 생성도ㅒㅆ고 card의 주소값이 shuffle로 들어간다.
    //mselected도 새로운 주소값이 생긴다. state는 카드가 가지고 있따(인스턴스). 카드는 mstate와 mimage를 가지고있다.
    //selectedcard1.은 .뒤의 주소값을 찾아가는것 null로 만들었으니까 주소값이 없으니 앱이 죽는다.(null exception error)
    public void checkMatch(){

        if(m_SelectedCard_1 !=null && m_SelectedCard_2 !=null){

            if(m_SelectedCard_1.m_Image==m_SelectedCard_2.m_Image){
                m_SelectedCard_1.m_state = Card.CARD_MATCH;

                m_SelectedCard_2.m_state = Card.CARD_MATCH;

                m_SelectedCard_2=null;  //null은 주소값을 지우는것이다.
                m_SelectedCard_1=null;
                for(int y=0;y<m_row;y++){
                    for(int x=0;x<m_column;x++){
                       if( m_Shuffle[x][y].m_state  !=Card.CARD_MATCH ){
                           postInvalidate();
                           return;
                           //return이 안되면 (카드상태가 매치가 아닌경우가 없을때 = 모든카드 매치상태) for문 빠져나올것이다.
                       }



                    }


                }
            m_state = STATE_END;
            }

            else{  //try catch는 에러처리. sleep을 쓰면 자동으로 써줘야한다.(예외처리)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m_SelectedCard_1.m_state = Card.CARD_CLOSE;

                m_SelectedCard_2.m_state = Card.CARD_CLOSE;
                m_SelectedCard_2=null;
                m_SelectedCard_1=null;
            }
            postInvalidate();
            //thread의 invalidate를 써줘야해서 post써야함

        }
        return;
    }


/*
    public void View_Backside_Image (Canvas canvas, int x,int y){
        canvas. drawBitmap(m_Cardvalid,null ,null, null);



        return;


    }
*/


}



