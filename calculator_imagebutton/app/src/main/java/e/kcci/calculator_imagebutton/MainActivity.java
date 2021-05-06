package e.kcci.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //appcompatactivity는 낮은버전의 단말기에 앱을 실행시키기위해
    TextView Tv_result;
    Integer ButtonNumberID[] = {R.id.btn_num0,R.id.btn_num1,R.id.btn_num2,R.id.btn_num3,R.id.btn_num4,
            R.id.btn_num5,R.id.btn_num6,R.id.btn_num7,R.id.btn_num8,R.id.btn_num9};
    //id는 for문에서 index로 접근하기 위해 배열로 구성 ,연속된 일련번호가 아닐때 위와같이구성
    ImageButton btnNumber[] = new ImageButton[10];  //버튼배열 10개선언

    ImageButton btn_plus,btn_sub,btn_mul,btn_div,btn_op,btn_clear;

    Integer input_num=0, result = 0;
    //Integer는 자료형은 아니고 클래스다
    int opCode;  //연산자 입력변수 생성
    final int plus = 1,sub = 2, mul = 3,div = 4;  //final로 const선언을 하여 각 순서지정

    @Override  //사용자의 재정의를 필요로 하는 매소드를 포함시키기위해
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tv_result = (TextView) findViewById(R.id.tv_result);
        for (int i = 0; i < 10; i++) {
            btnNumber[i] = (ImageButton) findViewById(ButtonNumberID[i]);
        }
        for (int i = 0; i < 10; i++) {
            btnNumber[i].setOnClickListener(mClickListener);
            //onclicklistener로 mclicklistner로set해줌
        }
        /*
        btn_num0 = (Button)findViewById(R.id.btn_num0);
        btn_num1 = (Button)findViewById(R.id.btn_num1);
        btn_num2 = (Button)findViewById(R.id.btn_num2);
        btn_num3 = (Button)findViewById(R.id.btn_num3);
        btn_num4 = (Button)findViewById(R.id.btn_num4);
        btn_num5 = (Button)findViewById(R.id.btn_num5);
        btn_num6 = (Button)findViewById(R.id.btn_num6);
        btn_num7 = (Button)findViewById(R.id.btn_num7);
        btn_num8 = (Button)findViewById(R.id.btn_num8);
        btn_num9 = (Button)findViewById(R.id.btn_num9);
         */


        btn_plus = (ImageButton) findViewById(R.id.btn_plus);
        btn_sub = (ImageButton) findViewById(R.id.btn_sub);
        btn_mul = (ImageButton) findViewById(R.id.btn_mul);
        btn_div = (ImageButton) findViewById(R.id.btn_div);
        btn_op = (ImageButton) findViewById(R.id.btn_op);
        btn_clear = (ImageButton) findViewById(R.id.btn_clear);

        btn_plus.setOnClickListener(mClickListenerOP);
        btn_sub.setOnClickListener(mClickListenerOP);
        btn_mul.setOnClickListener(mClickListenerOP);
        btn_div.setOnClickListener(mClickListenerOP);
        btn_op.setOnClickListener(mClickListenerOP);
        btn_clear.setOnClickListener(mClickListenerOP);


    }
    //clicklistener를 2개 만듬 op와 숫자
    ImageButton.OnClickListener mClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_num0:
                    input_num = input_num * 10+ 0;
                    break;
                case R.id.btn_num1:
                    input_num = input_num * 10 + 1;
                    break;
                case R.id.btn_num2:
                    input_num = input_num * 10 + 2;
                    break;
                case R.id.btn_num3:
                    input_num =input_num * 10 + 3;
                    break;
                case R.id.btn_num4:
                    input_num = input_num * 10 + 4;
                    break;
                case R.id.btn_num5:
                    input_num = input_num * 10 + 5;
                    break;
                case R.id.btn_num6:
                    input_num =input_num * 10 + 6;
                    break;
                case R.id.btn_num7:
                    input_num = input_num * 10 + 7;
                    break;
                case R.id.btn_num8:
                    input_num =input_num * 10 + 8;
                    break;
                case R.id.btn_num9:
                    input_num =input_num * 10 + 9;
                    break;
            }
            Tv_result.setText(input_num.toString());

        }


    };
//num1로 숫자가 들어가면서 10진수 shift하는 느낌의 코드 구성



    ImageButton.OnClickListener mClickListenerOP = new ImageButton.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(input_num !=0) {
                if (opCode != 0) {
                    switch (opCode) {
                        case plus:
                            result = result + input_num;

                            opCode = plus;
                            break;
                        case sub:
                            result = result - input_num;

                            opCode = sub;
                            break;

                        case div:
                            result = result / input_num;

                            opCode = div;
                            break;

                        case mul:
                            result = result * input_num;

                            opCode = mul;
                            break;

                    }

                    Tv_result.setText(result.toString());
                    input_num = result;
                    opCode = 0;
                }

                switch (v.getId()) {
                    case R.id.btn_plus:
                        result = input_num;
                        input_num = 0;
                        opCode = plus;
                        break;
                    //op코드 눌렸을떄 num1으로만 들어가게 코드진행
                    case R.id.btn_sub:
                        result = input_num;
                        input_num = 0;
                        opCode = sub;
                        break;

                    case R.id.btn_div:
                        result = input_num;
                        input_num = 0;
                        opCode = div;
                        break;

                    case R.id.btn_mul:
                        result = input_num;
                        input_num = 0;
                        opCode = mul;
                        break;

                    case R.id.btn_clear:
                        input_num = 0;
                        result = 0;
                        opCode = 0;
                        Tv_result.setText(result.toString());
                        break;
                    case R.id.btn_op:
                        switch (opCode) {
                            case plus:
                                result = result + input_num;
                                break;
                            case sub:
                                result = result - input_num;
                                break;
                            case mul:
                                result = result * input_num;
                                break;
                            case div:
                                result = result / input_num;
                                break;

                            //num2가 먼저 입력된 값이 들어간것 두번쨰들어온 숫자가 num1

                        }


                        Tv_result.setText(result.toString());
                        input_num = result;

                        break;
                }

            }


            else{
                switch (v.getId()) {
                    case R.id.btn_plus: opCode = plus;break;
                    case R.id.btn_sub: opCode = sub;break;
                    case R.id.btn_mul: opCode = mul;break;
                    case R.id.btn_div: opCode = div;break;


                    case R.id.btn_clear:
                        input_num = 0;
                        result = 0;
                        opCode = 0;
                        Tv_result.setText(result.toString());
                        break;
                }

            }

        }

    };



}
