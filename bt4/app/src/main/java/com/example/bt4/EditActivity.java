package com.example.bt4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {
    EditText txtid, txtname;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        final Person p = (Person) bundle.getSerializable("per");
        txtid = (EditText) findViewById(R.id.txtma);
        txtname = (EditText) findViewById(R.id.txtten);
        btnsave = (Button) findViewById(R.id.btnLuu);
        txtid.setText(p.getId() + "");
        txtname.setText(p.getName());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                p.setName(txtname.getText() + "");
                setResult(MainActivity.SAVE_EDIT_EMPLOYEE, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}