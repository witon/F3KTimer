package com.laozhu.f3ktimer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.laozhu.f3krule.*;
import com.laozhu.f3ktimer.databinding.ActivityMainBinding;

import java.util.List;

import com.laozhu.proto.CompetitionServiceGrpc;

import Proto.CompetitionOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class MainActivity extends BaseActivity {

    long timeStamp = System.currentTimeMillis();
    BaseAdapter baseAdapter = null;
    BroadcastReceiver broadcastReceiver = null;
    public static final String ACTION_NEW_COMPETITION = "ACTION.NEW_COMPETITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Competition competition = getApp().competitionArrangement.getCompetition();
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getApp().getCompetitionsFromDb();
        baseAdapter = new CompetitionsAdapter<Competition>(getApp().competitions.getCompetionsArrayList(), R.layout.listitem_competition, BR.competition);
        activityMainBinding.setAdapter(baseAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        regEventReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void onNewCompetition() {
        Intent intent = new Intent(MainActivity.this, CompetitionArrangementActivity.class);
        startActivity(intent);
    }
    private void onCompetitorList() {
        Intent intent = new Intent(MainActivity.this, CompetitorListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregEventReceiver();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.action_new_competition:
                onNewCompetition();
                return true;
            case R.id.action_competitor_list:
                onCompetitorList();
                return true;
            case R.id.action_refresh:
                refreshCompetitionList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshCompetitionList(){
        getApp().getCompetitionsFromDb();
        ListView view = findViewById(R.id.lv_competitions);
        baseAdapter.notifyDataSetChanged();
    }

    void regEventReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(ACTION_NEW_COMPETITION)) {
                    String competitionName = intent.getStringExtra("name");
                    refreshCompetitionList();
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_NEW_COMPETITION));
    }

    void unregEventReceiver() {
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }

    public class CompetitionsAdapter<T> extends BaseAdapter {
        private List<T> data;
        private int itemLayoutId;
        private int variableId;

        public CompetitionsAdapter(List<T> data, int itemLayoutId, int variableId) {
            this.data = data;
            this.itemLayoutId = itemLayoutId;
            this.variableId = variableId;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public T getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewDataBinding binding;
            if (convertView == null) {
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), itemLayoutId, parent, false);
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }
            binding.setVariable(variableId, data.get(position));
            return binding.getRoot();
        }
    }
}
