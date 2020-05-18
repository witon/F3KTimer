package com.laozhu.f3ktimer;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;

import com.laozhu.f3ktimer.databinding.ActivityCompetitorListBinding;

public class CompetitorListActivity extends SubActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_competitor_list);
        super.onCreate(savedInstanceState);
        ActivityCompetitorListBinding activityCompetitorListBinding = DataBindingUtil.setContentView(this, R.layout.activity_competitor_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competition_arrangement, menu);
        return true;
    }

}
