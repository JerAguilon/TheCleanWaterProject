package database.mongodb;

import database.responses.DatabaseException;
import model.DateGenerator;
import model.UserReport;
import model.WaterSourceCondition;
import model.WaterSourceType;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/*
 * Created by jeremy on 10/23/16.
 */
class MongoUserReportManager {
    private final String url;
    public MongoUserReportManager(String url) {
        this.url = url;
    }

    public void addUserReport(UserReport report)  throws  IOException, JSONException, DatabaseException {
        String url = this.url + "/submit";

        String condition = Integer.toString(report.getCondition().ordinal());
        String type = Integer.toString(report.getType().ordinal());
        String location = report.getLocationColumn();
        String username = report.getUsernameColumn();
        Date date = report.getDate();

        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("reporterName", username));
        pairs.add(new BasicNameValuePair("waterSourceType", type));
        pairs.add(new BasicNameValuePair("waterSourceCondition", condition));
        pairs.add(new BasicNameValuePair("location", location));
        pairs.add(new BasicNameValuePair("date", DateGenerator.dateToDBString(date)));

        request.addHeader("x-access-token", TokenKeeper.getToken());
        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);
        System.out.println(resp);
        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));

        String message = object.getString("message");

        boolean result = object.getBoolean("success");

        if (!result) {
            throw new DatabaseException(message);
        }
    }

    public Collection<UserReport> getReports() throws IOException, JSONException, DatabaseException {
        String url = this.url + "/view";
        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        request.addHeader("x-access-token", TokenKeeper.getToken());

        HttpResponse resp = client.execute(request);
        String output = EntityUtils.toString(resp.getEntity(), "UTF-8");

        JSONArray objectArray = new JSONArray(output.trim());
        ArrayList<UserReport> resultList = new ArrayList<>();
        for (int i = 0; i < objectArray.length(); i++) {
            JSONObject object = objectArray.getJSONObject(i);


            String location = object.getString("location");
            WaterSourceType type = WaterSourceType.values()[object.getInt("waterSourceType")];
            WaterSourceCondition condition = WaterSourceCondition.values()[object.getInt("waterSourceCondition")];

            String id = object.getString("_id");
            String name = object.getString("reporterName");

            Date date;

            try {
                date = DateGenerator.generateDate(object.getString("date"));
            } catch (ParseException e) {
                e.printStackTrace();
                throw new DatabaseException("Unable to parse the date from the mongo database");
            }

            String time = DateGenerator.dateToJavaString(date);

            UserReport report;
            try {
                report = new UserReport(time, name, location, type, condition, id);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new DatabaseException("Unable to parse date upon java object creation");
            }
            resultList.add(report);
        }
        return resultList;
    }
}
