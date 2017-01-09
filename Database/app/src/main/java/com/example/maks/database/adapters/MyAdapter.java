package com.example.maks.database.adapters;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maks.database.App;
import com.example.maks.database.R;

public class MyAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private SQLiteDatabase db;
    private Cursor cursor= null;
    private int mSize = 0;


    private static class ViewHolder {
        public TextView fullName;
        public TextView address;

        public ViewHolder(ViewGroup viewGroup) {
            fullName = (TextView) viewGroup
                    .findViewById(R.id.item_name_surname);

            address = (TextView) viewGroup
                    .findViewById(R.id.item_address);
        }
    }
    public MyAdapter(Activity activity, SQLiteDatabase db) {
        this.layoutInflater = activity.getLayoutInflater();
        this.db = db;
        doQuery();
    }

    private void doQuery(){
        if(cursor!=null){
            cursor.close();
        }
        cursor = db.rawQuery("SELECT id FROM user", new String[]{});;
        mSize = cursor.getCount();
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public Cursor getItem(int position) {
        if(cursor.moveToPosition(position)){
            long rowId = cursor.getLong(0);
            Cursor c =db.rawQuery("SELECT * FROM table1 WHERE rowid = ?", new String[]{Long.toString(rowId)});
            return c;
        }else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if(cursor.moveToPosition(position)){
            long rowId = cursor.getLong(0);
            return rowId;
        }else{
            return 0;
        }
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent
    ) {
        cursor.moveToPosition(position);
        long rowId = cursor.getLong(0);
        Cursor curs = App.getInstance().getDb().rawQuery("SELECT * FROM user WHERE rowid = ?", new String[]{Long.toString(rowId)});

        ViewGroup viewGroup;
        ViewHolder viewHolder;

        if (convertView != null) {
            viewGroup = (ViewGroup)convertView;
            viewHolder = (ViewHolder)convertView.getTag();
        } else {
            viewGroup = (ViewGroup)layoutInflater.inflate(
                    R.layout.item,
                    parent,
                    false
            );
            viewHolder = new ViewHolder(viewGroup);
            viewGroup.setTag(viewHolder);
        }
        if (curs != null && curs.moveToFirst()) {
            int nameColIndex = curs.getColumnIndex("name");
            int surnameColIndex = curs.getColumnIndex("surname");
            int phoneColIndex = curs.getColumnIndex("phone");
            do {
                StringBuilder sb = new StringBuilder();
                sb.append(curs.getString(nameColIndex));
                sb.append(" ");
                sb.append(curs.getString(surnameColIndex));
                viewHolder.fullName.setText(sb.toString());
                viewHolder.address.setText("tel:"+curs.getString(phoneColIndex));
            } while (curs.moveToNext());
        }
        curs.close();
        return viewGroup;
    }

}
