package spacecorecorp.battlestationsapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    private EditText editTextName;
    private EditText editTextIP;
    private Button buttonProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        editTextName = findViewById(R.id.editTextUser);
        editTextIP = findViewById(R.id.editTextIP);
        buttonProceed = findViewById(R.id.buttonGo);

        buttonProceed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(editTextName.getText().toString().equals(""))
                {

                }
                else if(editTextIP.getText().toString().equals(""))
                {

                }
                else
                {
                    
                }
            }
        });
    }
}
