package e.kcci.a180323_matchingcard;

/**
 * Created by KCCI on 2018-04-06.
 */
//쓰레드로 상속해서 만들면 클래스안의 기능을 다른곳에서도 가지게 할수 있다.
public class cardgamethread extends Thread {
    CardGameView m_View;


   cardgamethread(CardGameView view){
       super(); //조상 클래스의 생성자 호출
       m_View = view;

   }
   //쓰레드는 run함수가 있어야한다. 쓰래드 실행하면 run함수가 실행된다.
    public void run(){
       while (m_View.th_flag==1){

           m_View.checkMatch();


       }

    }

}
