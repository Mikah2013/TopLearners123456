package com.example.toplearners.skilliq;

import android.net.Uri;
import android.util.Log;

import com.example.toplearners.model.SkillListItem;
import com.example.toplearners.netutils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkillLearners {

    private static final String TAG = "SkillLearners";
    private Utils mUtils = new Utils();

    public List<SkillListItem> fetchSkillLearners() {

        List<SkillListItem> mItems = new ArrayList<>();
        try {
            String url = Uri.parse("https://gadsapi.herokuapp.com/api/skilliq")
                    .buildUpon()
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = mUtils.getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONArray jsonArr1 = new JSONArray(jsonString);
            parseItems(mItems, jsonArr1);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return mItems;
    }

    private void parseItems(List<SkillListItem> items, JSONArray jsonBody1)
            throws IOException, JSONException {

        for (int i = 0; i < jsonBody1.length(); i++) {
            JSONObject mJsonObject = jsonBody1.getJSONObject(i);
            SkillListItem item = new SkillListItem();
            item.setName(mJsonObject.getString("name"));
            item.setScore(mJsonObject.getString("score"));
            item.setCountry(mJsonObject.getString("country"));

            if (!mJsonObject.has("badgeUrl")) {
                continue;
            }
            item.setBadgeUrl(mJsonObject.getString("badgeUrl"));
            items.add(item);
        }
    }



}
