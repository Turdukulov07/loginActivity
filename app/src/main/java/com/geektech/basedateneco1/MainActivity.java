package com.geektech.basedateneco1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edName, edSecName, edEmail;
    private DatabaseReference mDatabase;
    private String USER_KEY = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        edName = findViewById(R.id.edName);
        edSecName = findViewById(R.id.edSecName);
        edEmail = findViewById(R.id.edEmail);
        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickSave(View view) {
        String id = mDatabase.getKey();
        String name = edName.getText().toString();
        String sec_name = edSecName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, sec_name, email);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)) {


            mDatabase.push().setValue(newUser);
            Toast.makeText(this, "Сакталды", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Талаа бош калды", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickRead(View view) {

        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }

    public void onClickChooseImage(View view) {

        getImage();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null) {
            if (resultCode == RESULT_OK) {
                Log.d("My Log", "Image URI : " + data.getData());
            }
        }
    }


        private void getImage () {
            Intent intentChooser = new Intent();
            intentChooser.setType("image/*");
            intentChooser.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intentChooser, 1);
        }


    }