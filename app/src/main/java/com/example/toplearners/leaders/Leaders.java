package com.example.toplearners.leaders;

import android.net.Uri;
import android.util.Log;

import com.example.toplearners.model.LeaderListItem;
import com.example.toplearners.netutils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leaders {

    private static final String TAG = "Leaders";
    private static final String TAG_1 = "SkillLearners";

    private Utils mUtils = new Utils();


    public List<LeaderListItem> fetchLearners() {

        List<LeaderListItem> items = new ArrayList<>();
        try {
            String url = Uri.parse("https://gadsapi.herokuapp.com/api/hours")
                    .buildUpon()
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = mUtils.getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONArray jsonArr = new JSONArray(jsonString);
            parseItems(items, jsonArr);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }


    private void parseItems(List<LeaderListItem> items, JSONArray jsonBody)
            throws IOException, JSONException {

        for (int i = 0; i < jsonBody.length(); i++) {
            JSONObject photoJsonObject = jsonBody.getJSONObject(i);
            LeaderListItem item = new LeaderListItem();
            item.setName(photoJsonObject.getString("name"));
            item.setHours(photoJsonObject.getString("hours"));
            item.setCountry(photoJsonObject.getString("country"));

            if (!photoJsonObject.has("badgeUrl")) {
                continue;
            }
            item.setBadgeUrl(photoJsonObject.getString("badgeUrl"));
            items.add(item);
        }
    }


}
