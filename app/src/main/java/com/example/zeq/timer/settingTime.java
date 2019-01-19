package com.example.zeq.timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class settingTime extends AppCompatActivity {
    private Button button_work, button_relax;
    private EditText editText_work, editText_relax;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        button_work = (Button)findViewById(R.id.work_time_button);
        button_relax = (Button)findViewById(R.id.relax_time_button);
        editText_work = (EditText)findViewById(R.id.work_time_edit);
        editText_work.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText_relax = (EditText)findViewById(R.id.relax_time_edit);
        editText_relax.setInputType(InputType.TYPE_CLASS_NUMBER);
        button_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText_work.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_return", inputText);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_relax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText_relax.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_return", inputText);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });


    }
}
