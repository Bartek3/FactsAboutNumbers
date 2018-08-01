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

public class MathFragment extends Fragment {

    Button button;
    EditText editText;
    TextView textView;
    String url = "http://numbersapi.com/";
    String number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_math, container, false);

        button = rootView.findViewById(R.id.button);
        editText = rootView.findViewById(R.id.edittext);
        textView = rootView.findViewById(R.id.text);

        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    number = editText.getText().toString();
                    if (number.length() == 0) {
                        Toast.makeText(getContext(), "Enter number!", Toast.LENGTH_SHORT).show();
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
                number = editText.getText().toString();
                if (number.length() == 0) {
                    Toast.makeText(getContext(), "Enter number!", Toast.LENGTH_SHORT).show();
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
                result = ConnectAPI.httpToString(url + number + "/math");
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
