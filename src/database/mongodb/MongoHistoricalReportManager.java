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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremy on 11/8/16.
 */
public class MongoHistoricalReportManager {
    private final String url;
    public MongoHistoricalReportManager(String url) {
        this.url = url;
    }
    public void addHistoricalReport(HistoricalReport report)  throws IOException, JSONException, DatabaseException {
        String url = this.url + "/submit";

        String reportType = Integer.toString(report.getHistoricalReportType().ordinal());
        String ppm = Long.toString(report.getPPM());
        String location = report.getLocation();
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        date = formatter.format(report.getDate());

        System.out.println("DATE: " + date);
        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("historicalReportType", reportType));
        pairs.add(new BasicNameValuePair("ppm", ppm));
        pairs.add(new BasicNameValuePair("location", location));
        pairs.add(new BasicNameValuePair("date", date));
        request.addHeader("x-access-token", TokenKeeper.getToken());
        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);

        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));

        String message = object.getString("message");

        boolean result = object.getBoolean("success");

        if (!result) {
            throw new DatabaseException(message);
        }
    }
    public Collection<HistoricalReport> getReports() throws IOException, JSONException, DatabaseException {
        String url = this.url + "/view";
        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        request.addHeader("x-access-token", TokenKeeper.getToken());

        HttpResponse resp = client.execute(request);
        String output = EntityUtils.toString(resp.getEntity(), "UTF-8");

        JSONArray objectArray = new JSONArray(output.trim());
        ArrayList<HistoricalReport> resultList = new ArrayList<>();

        for (int i = 0; i < objectArray.length(); i++) {
            JSONObject object = objectArray.getJSONObject(i);


            int reportType = object.getInt("historicalReportType");
            long ppm = object.getLong("ppm");
            String location = object.getString("location");

            String time = object.getString("date");
            SimpleDateFormat pulledFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date date;
            try {
                date = pulledFormat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new DatabaseException("Unable to parse the date from the mongo database");
            }

            HistoricalReport report = new HistoricalReport(location, HistoricalReportType.values()[reportType],date,  ppm);
            resultList.add(report);
        }
        return resultList;
    }

}
