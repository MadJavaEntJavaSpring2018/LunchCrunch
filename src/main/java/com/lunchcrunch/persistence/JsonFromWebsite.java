package com.lunchcrunch.persistence;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Utility class to get a string of json from a website.
 *
 * @author mchoinoski
 */

public class JsonFromWebsite {

    public String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
