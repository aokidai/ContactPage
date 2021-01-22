package com.example.bt4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends Activity {
    public static final int NEW_EMPLOYEE = 113;
    public static final int EDIT_EMPLOYEE = 114;
    public static final int SAVE_NEW_EMPLOYEE = 115;
    public static final int SAVE_EDIT_EMPLOYEE = 116;
    ListView lv;
    int possselected = -1;
    ArrayList<Person>list = new ArrayList<Person>();
    ArrayAdapter<Person>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new Person(1, "Teo"));
        list.add(new Person(2, "Ty"));
    //   Toast.makeText(MainActivity.this, list.get(1).toString(), Toast.LENGTH_LONG).show();
        lv = (ListView) findViewById(R.id.lvnhanvien);
        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                possselected = arg2;
                return false;
            }
        });
        registerForContextMenu(lv);
    }
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menucontext, menu);
    }
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnunew:
                doStartNew();
                break;
            case R.id.mnuedit:
                doStartEdit();
                break;
            case R.id.mnudelete:
                doDelete();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void doStartNew(){
        Intent intentNew = new Intent(this, NewActivity.class);
        startActivityForResult(intentNew, MainActivity.NEW_EMPLOYEE);
    }
    public void doStartEdit(){
        Intent intentEdit = new Intent(this, EditActivity.class);
        Person p = list.get(possselected);
        Bundle bundle = new Bundle();
        bundle.putSerializable("per", p);
        intentEdit.putExtra("DATA", bundle);
        startActivityForResult(intentEdit, MainActivity.EDIT_EMPLOYEE);
    }
    public void doDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hoi xoa");
        builder.setMessage("Muon xoa that a?");
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(possselected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MainActivity.NEW_EMPLOYEE:
                if (resultCode == MainActivity.SAVE_NEW_EMPLOYEE){
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.add(p);
                    adapter.notifyDataSetChanged();
                }
                break;
            case MainActivity.EDIT_EMPLOYEE:
                if (resultCode == MainActivity.SAVE_EDIT_EMPLOYEE){
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.set(possselected, p);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}