package com.example.maks.database.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maks.database.database.DBUtils;
import com.example.maks.database.R;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private EditText address;
    private EditText email;
    private EditText phone;
    private Button btn;

    private String inputName;
    private String inputSurname;
    private String inputAddress;
    private String inputEmail;
    private String inputPhone;

    private DBUtils dbUtils=new DBUtils();

    public final static String ADD_NAME_KEY="add_name_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText)findViewById(R.id.edit_name);
        surname=(EditText)findViewById(R.id.edit_surname);
        address=(EditText)findViewById(R.id.edit_address);
        email=(EditText)findViewById(R.id.edit_email);
        phone=(EditText)findViewById(R.id.edit_phone);
        btn=(Button)findViewById(R.id.add_btn_addActivity);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryRegister();
            }
        });
    }

    public void tryRegister(){
        name.setError(null);
        surname.setError(null);
        address.setError(null);
        email.setError(null);
        phone.setError(null);

        inputName=name.getText().toString().trim();
        inputSurname=surname.getText().toString().trim();
        inputAddress=address.getText().toString().trim();
        inputEmail=email.getText().toString().trim();
        inputPhone=phone.getText().toString().trim();


        boolean hasError=false;

        if(inputName.length()==0){
            name.setError(getString(R.string.input_error));
            hasError=true;
        }
        if(inputName.contains(" ")){
            name.setError(getString(R.string.input_error_duo));
            hasError=true;
        }
        if(inputSurname.length()==0){
            surname.setError(getString(R.string.input_error));
            hasError=true;
        }
        if(inputSurname.contains(" ")){
            surname.setError(getString(R.string.input_error_duo));
            hasError=true;
        }
        if(inputAddress.length()==0){
            address.setError(getString(R.string.input_error));
            hasError=true;
        }
        if(inputEmail.length()==0){
            email.setError(getString(R.string.input_error));
            hasError=true;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            email.setError(getString(R.string.input_error_none_email));
            hasError=true;
        }
        if(inputPhone.length()==0){
            phone.setError(getString(R.string.input_error));
            hasError=true;
        }
        if(!Patterns.PHONE.matcher(inputPhone).matches()){
            phone.setError(getString(R.string.input_error_none_phone));
            hasError=true;
        }

        if(hasError){
            return;
        }

        try{
            Intent intent = new Intent();
            intent.putExtra(ADD_NAME_KEY,"Успешно добавлен user: "+inputName);
            dbUtils.addUser(inputName,inputSurname,inputAddress,inputEmail,inputPhone);
            setResult(RESULT_OK, intent);
        }catch(Exception e){
            setResult(RESULT_CANCELED);
        }finally {
            finish();
        }
    }
}
