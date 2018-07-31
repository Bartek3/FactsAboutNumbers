package com.bartek.factsaboutnumbers;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Bartek on 2018-07-31.
 */

abstract class ConnectAPI {

    static String connectAPI(String userInput) throws IOException {

        //Nawiązanie połączenia ze stroną
        URL url = new URL("http://numbersapi.com/" + userInput);
        URLConnection uc = url.openConnection();

        //Pobranie danych ze strony
        InputStream in = uc.getInputStream();
        Reader reader = new InputStreamReader(in, "UTF-8");

        return reader.toString();
    }

}
