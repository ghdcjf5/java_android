package e.kcci.idol;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //view를 임포트시켜서 (클래스에) 이프로젝트에 포함시키는것
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_irin,btn_yeri,btn_seulgi,btn_joy,btn_wendy;
    ImageView irin,wendy,seulgi,joy,yeri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_irin = (Button)findViewById(R.id.btn_irin);
        btn_wendy = (Button)findViewById(R.id.btn_wendy);
        btn_joy = (Button)findViewById(R.id.btn_joy);
        btn_seulgi = (Button)findViewById(R.id.btn_seulgi);
        btn_yeri = (Button)findViewById(R.id.btn_yeri);

        irin= (ImageView)findViewById(R.id.irin);
        wendy = (ImageView)findViewById(R.id.wendy);
        joy = (ImageView)findViewById(R.id.joy);
        seulgi = (ImageView)findViewById(R.id.seulgi);
        yeri = (ImageView)findViewById(R.id.yeri);


        btn_irin.setOnClickListener(mOnClickListener);  //버튼입력받기
        btn_wendy.setOnClickListener(mOnClickListener);
        btn_joy.setOnClickListener(mOnClickListener);
        btn_seulgi.setOnClickListener(mOnClickListener);
        btn_yeri.setOnClickListener(mOnClickListener);


    }

    Button.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // btn_irin.setText("Pressed");
            irin.setVisibility(View.INVISIBLE);  //View에 invisible상수값을 설정해주는것
            wendy.setVisibility(View.INVISIBLE);
            joy.setVisibility(View.INVISIBLE);
            seulgi.setVisibility(View.INVISIBLE);
            yeri.setVisibility(View.INVISIBLE);




            switch(v.getId()){
                case R.id. btn_irin : irin.setVisibility(View.VISIBLE); break;
                case R.id.  btn_wendy : wendy.setVisibility(View.VISIBLE) ; break;
                case R.id.  btn_joy : joy.setVisibility(View.VISIBLE) ; break;
                case R.id. btn_seulgi : seulgi.setVisibility(View.VISIBLE) ; break;
                case R.id. btn_yeri : yeri.setVisibility(View.VISIBLE) ; break;

            }

        }
    };

}
