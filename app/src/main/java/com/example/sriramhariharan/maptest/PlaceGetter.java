package com.example.sriramhariharan.maptest;

import android.os.AsyncTask;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by User1 on 9/6/2016.
 */
public class PlaceGetter extends AsyncTask<View, Void, String> {

    public PlaceGetter(double lat, double lng){
        latitude = ""+lat;
        longtitude = ""+lng;
    }

    ArrayList<GooglePlace> venuesList;

    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String GOOGLE_KEY = "AIzaSyCkum52c7dpfhQQ-4V1o8HtdOpq7naaU5Q";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of New York
    public static String latitude = "";
    public static String longtitude = "";
    public static String radius = ""+100;
    String temp;

    @Override
    protected String doInBackground(View... urls) {
        // make Call to the url
        temp = makeCall("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=" + radius + "&sensor=true&key=" + GOOGLE_KEY);

        //print the call in the console
        System.out.println("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=" + radius + "&sensor=true&key=" + GOOGLE_KEY);
        return "";
    }

    @Override
    protected void onPreExecute() {
        // we can start a progress bar here
    }

    @Override
    protected void onPostExecute(String result) {
        if (temp == null) {
            // we have an error to the call
            // we can also stop the progress bar
        } else {
            // all things went right

            // parse Google places search result
            venuesList = parseGoogleParse(temp);
            Values.allplaces.clear();
            for(int i = 0; i < venuesList.size(); i++) {
                Values.allplaces.add(new Place(venuesList.get(i).getName(),venuesList.get(i).coord.latitude,venuesList.get(i).coord.longitude));
            }
            Values.places = Generator.getTour(Double.parseDouble(radius), Double.parseDouble(latitude), Double.parseDouble(longtitude));
            MapsActivity.places = Values.places;
            /*ArrayList<String> listTitle = new ArrayList<String>();

            for (int i = 0; i < venuesList.size(); i++) {
                // make a list of the venus that are loaded in the list.
                // show the name, the category and the city
                listTitle.add(i, venuesList.get(i).getName() + "\nOpen Now: " + venuesList.get(i).getOpenNow() + "\n(" + venuesList.get(i).getCategory() + ")");
            }*/

            // set the results to the list
            // and show them in the xml
            //myAdapter = new ArrayAdapter(Places.this, R.layout.row_layout, R.id.listText, listTitle);
            //setListAdapter(myAdapter);
            MapsActivity.generated = 1;
        }
    }
    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList<GooglePlace> parseGoogleParse(final String response) {

        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();
                    if (jsonArray.getJSONObject(i).has("geometry")) {
                        if (jsonArray.getJSONObject(i).getJSONObject("geometry").has("location")) {
                            poi.setCoord(Double.parseDouble(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat")),Double.parseDouble(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng")));
                        }
                    }
                    if (jsonArray.getJSONObject(i).has("name")) {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));

                        if (jsonArray.getJSONObject(i).has("opening_hours")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                                    poi.setOpenNow("YES");
                                } else {
                                    poi.setOpenNow("NO");
                                }
                            }
                        } else {
                            poi.setOpenNow("Not Known");
                        }
                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++) {
                                poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                            }
                        }
                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }
}