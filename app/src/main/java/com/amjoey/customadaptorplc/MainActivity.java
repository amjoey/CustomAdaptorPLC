package com.amjoey.customadaptorplc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity  extends Activity implements AdapterView.OnItemClickListener {
    String[] data_title;
    int[] data_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data_title = getResources().getStringArray(R.array.title_item);

      //  data_time = getResources().getIntArray(R.array.time_item);
        int[] data_time  = {2048,2304,4096,4352,4608,4864,4608,4608,4608,4608,4608,4608,4608,4608,2048,2304,4096,4352,4608,4864,4608,4608,4608,4608};

        MyArrayAdaptor adap = new MyArrayAdaptor(this, data_title, data_time);

        ListView lv = (ListView) findViewById(R.id.ListView);
        lv.setAdapter(adap);

        lv.setOnItemClickListener(this);
}

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Toast.makeText(getBaseContext(),
                "you" + data_title[position] + " " + data_time[position] , 0).show();
    }
}
