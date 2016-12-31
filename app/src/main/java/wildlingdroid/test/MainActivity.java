package wildlingdroid.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String myrez="xxx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverAdress="http://172.17.0.1:8000";
                DataTask d = new DataTask(getApplicationContext(),serverAdress);
                try {
                  myrez=  d.execute(DataTask.USERS_TABLE,DataTask.USERS_FIND_ALL).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Log.i("result  ", myrez);
            }
        });

    }
}
