package com.bartek.factsaboutnumbers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Bartek on 2018-07-31.
 */

abstract class ConnectAPI {

    static String httpToString(String URL) throws IOException {

        //Nawiązanie połączenia ze stroną
        URL url = new URL(URL);
        URLConnection uc = url.openConnection();

        //Pobranie danych ze strony
        InputStream in = uc.getInputStream();

        return inputStreamToString(in);
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }

}
