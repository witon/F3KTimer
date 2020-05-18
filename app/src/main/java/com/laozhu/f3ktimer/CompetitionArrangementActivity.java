package com.laozhu.f3ktimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;

import com.laozhu.f3kdb.DbCompetition;
import com.laozhu.f3kdb.DbManager;
import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.CompetitionArrangement;
import com.laozhu.f3ktimer.databinding.ActivityCompetitionArrangementBinding;
import com.laozhu.f3ktimer.databinding.ActivityMainBinding;

public class CompetitionArrangementActivity extends SubActivity{
    private CompetitionArrangement competitionArrangement = new CompetitionArrangement();
    private Competition competition = new Competition("new competition");
    private boolean isNewCompetition = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intend = getIntent();
        String competitionName = intend.getStringExtra("competitionName");
        if(competitionName == null)
            isNewCompetition = true;
        super.onCreate(savedInstanceState);
        ActivityCompetitionArrangementBinding activityCompetitionArrangementBinding = DataBindingUtil.setContentView(this, R.layout.activity_competition_arrangement);
        activityCompetitionArrangementBinding.setCompetition(competition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competition_arrangement, menu);
        return true;
    }

    private boolean saveCompetition(){
        DbManager dbManager = new DbManager(this);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        int ret = DbCompetition.RET_OTHER_FAIL;
        if(isNewCompetition) {
            ret = dbCompetition.addCompetition(competition);
        }
        dbManager.closeDB();
        switch (ret){
            case DbCompetition.RET_SUCCESS:
                return true;
            case DbCompetition.RET_DUPLICATE_COMPETITION:
                return false;
            case DbCompetition.RET_OTHER_FAIL:
                return false;
        }
        return true;
    }

    protected void onSaveCompetition() {
        boolean ret = saveCompetition();
        if(!ret) {
            return;
        }
        notifyMainActivity();
        finish();
    }

    protected void notifyMainActivity() {
        Intent intent = new Intent(MainActivity.ACTION_NEW_COMPETITION);
        intent.putExtra("name",competition.getName());
        sendBroadcast(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_save:
                onSaveCompetition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
