package com.example.alton.loginphpmysql;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText e1, e2;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        login=(Button) findViewById(R.id.button);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String type = "login";
        String username = e1.getText().toString();
        String password = e2.getText().toString();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

}
