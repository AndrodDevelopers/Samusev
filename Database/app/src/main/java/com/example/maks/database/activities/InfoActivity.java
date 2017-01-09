package com.example.maks.database.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.maks.database.R;

public class InfoActivity extends AppCompatActivity {
    public static final String ID_KEY="id_key";
    public static final String NAME_KEY="name_key";
    public static final String SURNAME_KEY="surname_key";
    public static final String ADDRESS_KEY="addres_key";
    public static final String EMAIL_KEY="email_key";
    public static final String PHONE_KEY="phone_key";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView=(TextView)findViewById(R.id.text_info);
        Intent intent =getIntent();

        StringBuilder sb= new StringBuilder();
        sb.append("id: ");
        sb.append(String.valueOf(intent.getLongExtra(ID_KEY,0)));
        sb.append("\n\r");
        sb.append("name: ");
        sb.append(intent.getStringExtra(NAME_KEY));
        sb.append("\n\r");
        sb.append("surname: ");
        sb.append(intent.getStringExtra(SURNAME_KEY));
        sb.append("\n\r");
        sb.append("address: ");
        sb.append(intent.getStringExtra(ADDRESS_KEY));
        sb.append("\n\r");
        sb.append("email: ");
        sb.append(intent.getStringExtra(EMAIL_KEY));
        sb.append("\n\r");
        sb.append("phone: ");
        sb.append(intent.getStringExtra(PHONE_KEY));

        textView.setText(sb.toString());


    }
}
