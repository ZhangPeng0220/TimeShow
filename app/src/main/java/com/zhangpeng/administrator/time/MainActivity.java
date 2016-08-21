package com.zhangpeng.administrator.time;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DownTimer timer;
    private TimePickerView pvTime;
    private TextView text2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv_scend = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        tv_scend.setText("总共三分钟");
        timer = new DownTimer();//实例化
        timer.setTotalTime(3*60*1000);//设置毫秒数
        timer.setIntervalTime(60*1000);//设置间隔数
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "完成倒计时", Toast.LENGTH_SHORT).show();
                tv_scend.setText("点击设置时间");
            }

            @Override
            public void onInterval(long remainTime) {
                tv_scend.setText("还剩" + remainTime / 1000/60+"分钟就完成了");//剩余多少秒
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.start:
                timer.start();
                break;
            case R.id.pause:
                timer.pause();
                break;
            case R.id.cancel:
                timer.cancel();
                break;
            case R.id.resume:
                timer.resume();
                break;
            case R.id.text2:
                choose();
                break;
        }
    }
    public void choose(){
        pvTime = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                text2.setText(getTime(date));
            }
        });
        pvTime.show();
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
