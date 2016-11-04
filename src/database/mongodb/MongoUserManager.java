package database.mongodb;
import database.responses.DatabaseException;
import model.Profile;
import model.User;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by jeremy on 10/23/16.
 */
class MongoUserManager {
    private final String baseUrl;
    public MongoUserManager(String url) {
        this.baseUrl = url;
    }
    public void authenticate(String username, String password) throws IOException, JSONException, DatabaseException {
        String url = this.baseUrl + "/authenticate";

        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("username", username));
        pairs.add(new BasicNameValuePair("password", password));


        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);

        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));

        boolean result = object.getBoolean("success");
        String message = object.getString("message");
        if (!result) {
            throw new DatabaseException(message);
        }

        TokenKeeper.setToken(object.getString("token"));
    }

    public void addUser(User user) throws IOException, JSONException, DatabaseException {
        String url = this.baseUrl + "/adduser";

        String username = user.USERNAME;
        String password = user.PASSWORD;
        String responsibility = Integer.toString(user.AUTH.ordinal());
        Profile profile = user.PROFILE;
        String email = profile.EMAIL;
        String title = profile.TITLE;
        String address = profile.HOME_ADDRESS;

        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("username", username));
        pairs.add(new BasicNameValuePair("password", password));
        pairs.add(new BasicNameValuePair("email", email));
        pairs.add(new BasicNameValuePair("title", title));
        pairs.add(new BasicNameValuePair("address", address));
        pairs.add(new BasicNameValuePair("responsibility", responsibility));



        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);

        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));

        boolean result = object.getBoolean("success");
        String message = object.getString("message");

        if (!result) {
            throw new DatabaseException(message);
        }

    }

    public List<User> viewUsers() throws IOException, JSONException, DatabaseException {
        String url = this.baseUrl + "/viewusers";
        HttpClient client= HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.addHeader("x-access-token", TokenKeeper.getToken());

        HttpResponse resp = client.execute(request);

        System.out.println(EntityUtils.toString(resp.getEntity(), "UTF-8"));
        //TODO: Implement this

        throw new NotImplementedException();
    }
}
