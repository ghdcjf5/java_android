//하드웨어 소프트웨어 한 덩어리를 플랫폼이라한다.
//지금 만드는것은 안드로이드 플랫폼에서 라이브러리로 새로운것을 만드는것이기에 응용프로그램이다.
//mainactivity의 oncreate(앱이 실행될때 처음으로 수행하는것)가 실행한다.

package e.kcci.a180323_matchingcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CardGameView(this));  //androidtestview로 클래스를 만들겠다
    } //new(연산자)로 생성자를 호출한다.
}
//클래스는 생성자가 있어야하고 클래스안에 기능과 속성이 들어있다.
//view를 상속시켜서 cardgameview를 만들었따.
//클래스로 생성된 객체는 인스턴스라고 한다. 오브젝트는 그냥 객체
//클래스는 객체생성을 위해 사용하는것
//우리가 쓰는것은 객체이다.