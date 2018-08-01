package com.bartek.factsaboutnumbers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class DateFragment extends Fragment {

    Button button;
    EditText day;
    EditText month;
    TextView textView;
    String url = "http://numbersapi.com/";
    String dayString;
    String monthString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_date, container, false);

        button = rootView.findViewById(R.id.button);
        day = rootView.findViewById(R.id.edittext1);
        month = rootView.findViewById(R.id.edittext2);
        textView = rootView.findViewById(R.id.text);

        day.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    monthString = month.getText().toString();
                    dayString = day.getText().toString();
                    if (monthString.length() == 0 || dayString.length() == 0) {
                        Toast.makeText(getContext(), "Enter numbers!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!button.isClickable()) button.setClickable(true);
                        NumberAsyncTask task = new NumberAsyncTask();
                        task.execute();
                    }
                    return true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthString = month.getText().toString();
                dayString = day.getText().toString();
                if (monthString.length() == 0 || dayString.length() == 0) {
                    Toast.makeText(getContext(), "Enter numbers!", Toast.LENGTH_SHORT).show();
                } else {
                    NumberAsyncTask task = new NumberAsyncTask();
                    task.execute();
                }
            }
        });

        return rootView;
    }


    private class NumberAsyncTask extends AsyncTask<String,Void,String> {

        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                result = ConnectAPI.httpToString(url + monthString + "/" + dayString + "/date");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String result) {
            progressDialog.cancel();
            textView.setText(result);
        }
    }

}

