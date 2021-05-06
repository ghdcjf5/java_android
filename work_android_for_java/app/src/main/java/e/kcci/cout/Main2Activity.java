package e.kcci.cout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView mytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String cout = " ";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mytext = (TextView)findViewById(R.id.mytext);
        for(int j=2; j<10;j++){
            for(int i=1;i<10;i++){
                cout += j+"*"+i+"="+i*j+"\n";
            }

            cout+= "\n";

        }


        mytext.setTextSize(35);
        mytext.setText(cout);
    }
}
