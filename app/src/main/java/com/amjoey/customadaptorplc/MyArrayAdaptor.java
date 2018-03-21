package com.amjoey.customadaptorplc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 14/3/2561.
 */

public class MyArrayAdaptor  extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itTitle;
    private final int[] itTime;


    public MyArrayAdaptor(Activity context, String[] titles, int[] times) {
        super(context, R.layout.row_layout, titles);
        this.context = context;
        this.itTitle = titles;
        this.itTime = times;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Log.d("myarrayadaptor",String.valueOf(position));
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_layout,null,true);
        //View inputView = inflater.inflate(R.layout.input_box,null,true);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.textView);
        EditText etTime = (EditText) rowView.findViewById(R.id.editText);


        tvTitle.setText(itTitle[position]);
        etTime.setText(timeformat(itTime[position]));

        ImageButton imgbutton = (ImageButton) rowView.findViewById(R.id.imageButton);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, timeformat(itTime[position]), 0).show();
                showInputBox(timeformat(itTime[position]),position);
            }
        });


        return rowView;

    }

    public void showInputBox(String oldItem, final int index) {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);

      EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);




        Button bt=(Button)dialog.findViewById(R.id.btdone);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // arrayList.set(index,editText.getText().toString());
               // arrayAdapter.notifyDataSetChanged();
                //etTime.setText(timeformat(itTime[position]));

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public static String timeformat(int t){
        String intTime;
        intTime =String.valueOf(Integer.toHexString(t));
        String first = padding(Integer.parseInt(intTime.substring(0, intTime.length() / 2)));
        String second = padding(Integer.parseInt(intTime.substring(intTime.length() / 2)));
        return first+ ":"  +second;
    }
    public static String padding(int c){
        if(c>=10)
            return String.valueOf(c);
        else
            return "0"+ String.valueOf(c);
    }
}

