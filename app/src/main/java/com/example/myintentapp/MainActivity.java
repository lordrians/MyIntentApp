package com.example.myintentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMoveActivity, btnMoveWithDataActivity, btnMoveWithObject, btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);

        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);
        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);
        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);
        btnMoveResult = findViewById(R.id.btn_move_for_result);
        btnMoveResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(this,MoveActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.btn_move_activity_data:
                Intent moveDataIntent = new Intent(this, MoveWithDataActivity.class);
                moveDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Ferrian Redhia Pratama");
                moveDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveDataIntent);
                break;

            case R.id.btn_move_activity_object:
                Person person = new Person();
                person.setName("Ferrian Redhia Pratama");
                person.setAge(5);
                person.setCity("Depok");
                person.setEmail("ferrianrp@gmail.com");

                Intent moveWithObjectIntent = new Intent(this, MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);

            case R.id.btn_dial_number:
                String phoneNumber = "081385558854";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.btn_move_for_result:
                Intent moveResultIntent = new Intent(this, MoveForResultActivity.class);
                startActivityForResult(moveResultIntent, REQUEST_CODE);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }

    }

}
