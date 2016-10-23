package database.mongodb;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremy on 10/23/16.
 */
public class MongoUserManager {

    public static void main(String[] args) throws IOException {
        MongoUserManager mm = new MongoUserManager();

        mm.authenticate("http://localhost:8080/api/users/authenticate", "myuser", "pass");
    }
    public void authenticate(String url, String username, String password) throws IOException {

        HttpClient client= HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("paramName", "paramValue"));

        request.setEntity(new UrlEncodedFormEntity(pairs ));
        HttpResponse resp = client.execute(request);
        System.out.println(resp);

    }
}
