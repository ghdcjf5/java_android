package e.kcci.molekiller_180425;

/**
 * Created by KCCI on 2018-04-25.
 */

public class moleThread extends Thread {
    public CatchMoleView m_Mole ;
    public Integer  m_score;
    public int m_level=1;
    public int velocity=600;
    public int[] m_seq;
    public  int index;
    public int stage_flag =1;
    public moleThread(CatchMoleView mole){
        m_Mole = mole;
        m_seq = new int[] {m_Mole.DRAW_PRE,m_Mole.DRAW_MOLE,m_Mole.DRAW_PRE};
        index= 0;

    }

    @Override
    public void run() {

         for(int j=0;j<3;j++){

            if(m_Mole.m_mode == m_Mole.DRAW_LEVEL){
                m_Mole.postInvalidate();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


            for(int i=0; i<60; i++){
                if(m_Mole.m_mode == m_Mole.DRAW_HURT){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    index = 0;

                }
                if(index%3 ==0){
                    m_Mole.m_x =((int)((Math.random()*5))*m_Mole.getWidth()/5);
                    m_Mole.m_y = ((int)((Math.random()*6+1))
                            *m_Mole.getHeight()/7);
                }
                setMode(m_seq[index%3]);
                index++;

            }

             if(m_score<=999) break;
            // if(m_score<=2000)break ;
            if(m_score>=1000){
                velocity-=250;
                m_level++;
                m_Mole.m_mode = m_Mole.DRAW_LEVEL;

            }



            /* if(stage_flag==1&&m_score>=1000){

                 m_level++;

                 velocity -=200;

             }
             else if(stage_flag==2&&m_score>=2500){

                 m_level++;

                 velocity -=200;
             }



             stage_flag++;*/
         }




    }

    public  void setMode(int mode){
        m_Mole.m_mode = mode;
        m_Mole.postInvalidate();

           if(mode == m_Mole.DRAW_PRE){
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }

           else if(mode== m_Mole.DRAW_MOLE) {
               try {
                   Thread.sleep(velocity);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }








    }
}
