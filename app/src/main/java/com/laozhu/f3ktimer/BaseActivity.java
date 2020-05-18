package com.laozhu.f3ktimer;


import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    F3kTimerApp getApp(){
        return (F3kTimerApp) this.getApplication();
    }
}
