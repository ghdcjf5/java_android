package e.kcci.a180323_matchingcard;

/**
 * Created by KCCI on 2018-03-30.
 */
//Card는 속성만 있는것
public class Card {
    //카드의 상태를 나타내는 클래스 생성
    public int m_state;
    public int m_Image;

    public static final int img_irin = 0;
    public static final int img_joy = 1;
    public static final int img_yeri = 2;
    public static final int img_baby = 3;
    public static final int img_jin = 4;
    public static final int img_jungyein = 5;
    public static final int img_kei = 6;
    public static final int img_leemiju = 7;
    public static final int img_ryusujeong = 8;
    public static final int img_seojisu = 9;
    public static final int img_yujiae = 10;

//상수선언을 위해 final선언
    public static final int CARD_SHOW = 0;
    public static final int CARD_CLOSE = 1;
    public static final int CARD_PLAYOPEN = 2;
    public static final int CARD_MATCH = 3;

    public Card(int _Color){
        m_state =CARD_SHOW;
        m_Image = _Color; //객체 생성시 객체값들 초기화
//_Color는 이미지
    }


}
