package com.amjoey.customadaptorplc;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


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

    static class ViewHolder{
        TextView tvTitle;
        EditText etTime;
        ImageButton imgbutton;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View rowView = convertView;
        Log.d("Myarrayadaptor",String.valueOf(position));

        if(rowView == null){
            Log.d("Myarrayadaptor","New Create");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_layout,null,true);

            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) rowView.findViewById(R.id.textView);
            viewHolder.etTime = (EditText) rowView.findViewById(R.id.editText);


            rowView.setTag(viewHolder);
        }else{
            Log.d("Myarrayadapter","Use Reuse");
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.tvTitle.setText(itTitle[position]);
        viewHolder.etTime.setText(timeformat(itTime[position]));







        viewHolder.imgbutton = (ImageButton) rowView.findViewById(R.id.imageButton);
        viewHolder.imgbutton.setOnClickListener(new View.OnClickListener() {
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

