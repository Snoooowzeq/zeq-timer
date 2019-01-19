package com.example.zeq.timer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ImageView settingbutton;

    //MenuItem menuItem_work=null, menuItem_relax=null             ;

    TextView txt_time;
    private MyCount count = null;
    Button breStart, bcancle, button_work, button_relax;
    //时间变量
    private long hour = 0;//时
    private long minute = 0;//分
    private long second = 0;//秒
    private long time = 0;//总时间
    private long minute_work=50, minute_relax=10;
    long temp_minute = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_time = (TextView) findViewById(R.id.tv_time);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_set);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, settingTime.class);
                startActivityForResult(intent, 1);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.start);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime(txt_time);
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }
        });

        settingbutton = (ImageView)findViewById(R.id.setting) ;
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, settingTime.class);
                startActivityForResult(intent, 1);
            }
        });

        button_work = (Button)findViewById(R.id.button_work);
        button_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_minute = minute_work;
                hour = Long.parseLong("00");
                //minute = Long.parseLong("10");
                minute = temp_minute;
                //minute = minute_work;
                second = Long.parseLong("00");
                time = (hour * 3600 + minute * 60 + second) * 1000;//因为以ms为单位，所以乘以1000.
                count = new MyCount(time, 1000);//延时时间为1s
                txt_time.setText("剩余时间00：00：00");
                txt_time.setTextSize(30);
                Toast.makeText(MainActivity.this, "工作"+temp_minute+"分钟",Toast.LENGTH_LONG).show();
            }
        });

        button_relax = (Button)findViewById(R.id.button_relax);
        button_relax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_minute = minute_relax;
                hour = Long.parseLong("00");
                //minute = Long.parseLong("10");
                minute = temp_minute;
                //minute = minute_work;
                second = Long.parseLong("00");
                time = (hour * 3600 + minute * 60 + second) * 1000;//因为以ms为单位，所以乘以1000.
                count = new MyCount(time, 1000);//延时时间为1s
                txt_time.setText("剩余时间00：00：00");
                txt_time.setTextSize(30);
                Toast.makeText(MainActivity.this, "休息"+temp_minute+"分钟",Toast.LENGTH_LONG).show();
            }
        });


        /*hour = Long.parseLong("00");
        minute = Long.parseLong("10");
        //minute = temp_minute;
        //minute = minute_work;
        second = Long.parseLong("00");
        time = (hour * 3600 + minute * 60 + second) * 1000;//因为以ms为单位，所以乘以1000.
        count = new MyCount(time, 1000);//延时时间为1s
        txt_time.setText("剩余时间00：00：00");
        txt_time.setTextSize(30);*/

    }

//    @Override
//    public boolean onCreatePanelMenu(int featureId, Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu, menu);
//        menuItem_work = menu.findItem(R.id.nav_work);
//        menuItem_relax = menu.findItem(R.id.nav_relax);
//        return true;
//    }

    @Override
    protected void onStart() {
        super.onStart();
        hour = Long.parseLong("00");
        //minute = Long.parseLong("10");
        minute = temp_minute;
        //minute = minute_work;
        second = Long.parseLong("00");
        time = (hour * 3600 + minute * 60 + second) * 1000;//因为以ms为单位，所以乘以1000.
        count = new MyCount(time, 1000);//延时时间为1s
        txt_time.setText("剩余时间00：00：00");
        txt_time.setTextSize(30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String return_work_time = data.getStringExtra("data_return");
                    minute_work = Long.parseLong(return_work_time);
                    Toast.makeText(MainActivity.this, "工作时间设置为: "+return_work_time+"分钟",Toast.LENGTH_LONG).show();
                }
                if (requestCode == RESULT_CANCELED){
                    String return_relax_time = data.getStringExtra("data_return");
                    minute_relax = Long.parseLong(return_relax_time);
                    Toast.makeText(MainActivity.this, "休息时间设置为: "+return_relax_time+"分钟",Toast.LENGTH_LONG).show();
                }
        }
    }

    public void startTime(View view){
        minute = temp_minute;
        count.start(); //开始计时
    }


    public void restartTime(View view){
        count.resume();//重新计时
    }

    public void cancleTime(View view){
        count.cancel();//取消计时
    }

    class MyCount extends AdvancedCountdownTimer{
        public MyCount(long millisInFuture, long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onFinish() {
            //Intent intent = new Intent(MainActivity.this,settingTime.class);
            //startActivity(intent);
            finish();
        }

        //更新剩余时间
        String a = null;
        @Override
        public void onTick(long millisUntilFinished, int percent) {
            long myhour = (millisUntilFinished / 1000) / 3600;
            long myminute = ((millisUntilFinished / 1000) - myhour * 3600) / 60;
            long mysecond = millisUntilFinished / 1000 - myhour * 3600 - myminute * 60;
            if (mysecond < 10){
                a = "0" + mysecond;
                txt_time.setText("剩余时间" + "0"+ myhour + ":" +"0"+myminute + ":" + a);
            }else {
                txt_time.setText("剩余时间" + "0"+ myhour + ":" +"0"+myminute + ":" + mysecond);
            }
            txt_time.setTextSize(30);
        }
    }
}
