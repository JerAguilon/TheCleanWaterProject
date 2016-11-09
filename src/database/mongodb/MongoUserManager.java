package database.mongodb;
import database.responses.DatabaseException;
import exceptions.UserException;
import model.AuthorizationLevel;
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
import sun.security.x509.AuthorityInfoAccessExtension;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

        List<NameValuePair> pairs = new ArrayList<>();
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

        List<NameValuePair> pairs = new ArrayList<>();
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

    public User getUser(String username) throws IOException, JSONException, DatabaseException {
        String url = this.baseUrl + "/me";


        HttpClient client= HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.addHeader("x-access-token", TokenKeeper.getToken());
        request.addHeader("username", username);
        HttpResponse resp = client.execute(request);

        JSONObject object = new JSONObject(EntityUtils.toString(resp.getEntity(), "UTF-8"));

        boolean result = object.getBoolean("success");
        String message = object.getString("message");
        object = object.getJSONObject("userData");
        if (!result) {
            throw new DatabaseException(message);
        }
        
        String foundUsername = object.getString("username");
        String password = object.getString("password");
        int responsibility = object.getInt("responsibility");
        JSONObject profile = object.getJSONObject("profile");
        String email = profile.getString("email");
        String address = profile.getString("address");
        String title = profile.getString("title");

        AuthorizationLevel auth = AuthorizationLevel.values()[responsibility];
        Profile javaProfile = new Profile(email, address, title);
        User user;
        try {
            user = new User(foundUsername, password, auth, javaProfile);
        } catch (UserException e) {
            throw new DatabaseException("Unable to convert mongo data to java obect");
        }

        return user;



    }
}
