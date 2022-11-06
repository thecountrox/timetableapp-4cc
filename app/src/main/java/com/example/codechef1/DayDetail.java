package com.example.codechef1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.codechef1.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private androidx.appcompat.widget.Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;
    private String[] PrefferedDay;
    private String[] PrefferedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        listView = (ListView) findViewById(R.id.lvDayDetail);
        toolbar = (Toolbar) findViewById(R.id.ToolbarDayDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){

        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);


        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5 = getResources().getStringArray(R.array.time5);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);
        if(selected_day.equalsIgnoreCase("monday")){
            PrefferedDay= Monday;
            PrefferedTime= Time1;
        }else if(selected_day.equalsIgnoreCase("tuesday")){
            PrefferedDay= Tuesday;
            PrefferedTime= Time2;
        }else if(selected_day.equalsIgnoreCase("wednesday")){
            PrefferedDay= Wednesday;
            PrefferedTime= Time3;
        }else if(selected_day.equalsIgnoreCase("thursday")){
            PrefferedDay= Thursday;
            PrefferedTime= Time4;
        }else{
            PrefferedDay= Friday;
            PrefferedTime= Time5;
        }

        SimpleAdapter simpleAdapter= new SimpleAdapter(this,PrefferedDay,PrefferedTime);
        listView.setAdapter(simpleAdapter);

    }


    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context,String[] subjectArray,String[] timeArray){
            mContext=context;
            this.subjectArray=subjectArray;
            this.timeArray=timeArray;
            layoutInflater=LayoutInflater.from(context);


        }


        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }

            subject = (TextView)convertView.findViewById(R.id.tvSubjectDayDetails);
            time = (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);
            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

            return convertView;

        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}