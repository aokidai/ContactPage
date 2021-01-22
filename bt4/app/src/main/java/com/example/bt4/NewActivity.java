package com.example.bt4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewActivity extends Activity {
    EditText txtid, txtname;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        txtid = (EditText) findViewById(R.id.txtma);
        txtname = (EditText) findViewById(R.id.txtten);
        btnsave = (Button) findViewById(R.id.btnLuu);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                int id = Integer.parseInt(txtid.getText().toString());
                String name = txtname.getText().toString();
                Person p = new Person(id, name);
                bundle.putSerializable("per", p);
                intent.putExtra("DATA", bundle);
                setResult(MainActivity.SAVE_NEW_EMPLOYEE, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}