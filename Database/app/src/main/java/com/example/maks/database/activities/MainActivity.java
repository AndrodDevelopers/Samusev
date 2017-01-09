package com.example.maks.database.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maks.database.App;
import com.example.maks.database.database.DBUtils;
import com.example.maks.database.adapters.MyAdapter;
import com.example.maks.database.R;
import com.example.maks.database.models.User;


public class MainActivity extends AppCompatActivity{
    private ListView listView;
    private Button btn;
    private MyAdapter myAdapter;
    private DBUtils dbUtils=new DBUtils();
    private User user;
    private static final int CM_DELETE_ID = 1;
    private static final int REQUEST_CODE_ADD_USER=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        btn=(Button)findViewById(R.id.btn_add);

        myAdapter=new MyAdapter(this, App.getInstance().getDb());

        listView.setAdapter(myAdapter);
        registerForContextMenu(listView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_USER);
            }
        });

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView parent,
                            View view,
                            int position,
                            long id
                    ) {
                        user=dbUtils.getUserIdFull(myAdapter.getItemId(position));
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class)
                                .putExtra(InfoActivity.ID_KEY,user.getId())
                                .putExtra(InfoActivity.NAME_KEY,user.getName())
                                .putExtra(InfoActivity.SURNAME_KEY,user.getSurname())
                                .putExtra(InfoActivity.ADDRESS_KEY,user.getAddress())
                                .putExtra(InfoActivity.EMAIL_KEY,user.getEmail())
                                .putExtra(InfoActivity.PHONE_KEY,user.getPhone());
                        startActivity(intent);//*/
                    }
                }
        );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            dbUtils.deleteUser(myAdapter.getItemId(acmi.position));
            listView.setAdapter(new MyAdapter(this,App.getInstance().getDb()));
            // уведомляем, что данные изменились
            myAdapter.notifyDataSetChanged();

            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_USER:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, data.getStringExtra(AddActivity.ADD_NAME_KEY), Toast.LENGTH_LONG).show();
                    listView.setAdapter(new MyAdapter(this,App.getInstance().getDb()));
                    myAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, R.string.error_add, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new MyAdapter(this,App.getInstance().getDb()));
        myAdapter.notifyDataSetChanged();

    }


}
