package database.mongodb;

import database.responses.DatabaseException;
import model.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/*
 * Created by jeremy on 10/28/16.
 */
class MongoWorkerReportManager {
    private final String url;
    public MongoWorkerReportManager(String url) {
        this.url = url;
    }

    public Collection<WorkerReport> getReports() throws IOException, JSONException, DatabaseException {
        String url = this.url + "/view";
        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        request.addHeader("x-access-token", TokenKeeper.getToken());

        HttpResponse resp = client.execute(request);
        String output = EntityUtils.toString(resp.getEntity(), "UTF-8");

        JSONArray objectArray = new JSONArray(output.trim());
        ArrayList<WorkerReport> resultList = new ArrayList<>();

        for (int i = 0; i < objectArray.length(); i++) {
            JSONObject object = objectArray.getJSONObject(i);


            String location = object.getString("location");
            WaterPurityCondition condition = WaterPurityCondition.values()[object.getInt("waterPurityCondition")];
            String id = object.getString("_id");
            String name = object.getString("reporterName");

            String time = object.getString("createdAt");
            SimpleDateFormat pulledFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");
            SimpleDateFormat javaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date;
            try {
                date = pulledFormat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new DatabaseException("Unable to parse the date from the mongo database");
            }

            time = javaFormat.format(date);

            WorkerReport report = new WorkerReport(time, name, location, condition, id);
            resultList.add(report);
        }
        return resultList;

    }

    public void addReport(WorkerReport report) throws IOException, JSONException, DatabaseException {
        String url = this.url + "/submit";

        String condition = Integer.toString(report.getCondition().ordinal());
        String location = report.getLocationColumn();
        String username = report.getUsernameColumn();

        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("reporterName", username));
        pairs.add(new BasicNameValuePair("waterPurityCondition", condition));
        pairs.add(new BasicNameValuePair("location", location));

        request.addHeader("x-access-token", TokenKeeper.getToken());
        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);

        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));
        System.out.println(object);
        String message = object.getString("message");

        boolean result = object.getBoolean("success");

        if (!result) {
            throw new DatabaseException(message);
        }
    }
}
