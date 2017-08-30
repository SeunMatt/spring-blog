package com.smatt.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

/**
 * Created by smatt on 29/08/2017.
 */
public class JMailChimp  {

    private String apiKey;
    private String apiRoot;
    public enum STATUS {
        SUBSCRIBED, UNSUBSCRIBED, PENDING, CLEANED;
    }

    private Logger logger = Logger.getLogger(JMailChimp.class);

    public JMailChimp() {}

    public JMailChimp(String apiKey, String apiRoot) {
        this.apiKey = apiKey;
        this.apiRoot = apiRoot;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiRoot() {
        return apiRoot;
    }

    public void setApiRoot(String apiRoot) {
        this.apiRoot = apiRoot;
    }

    public String addSubscriber(String email, String listId, JMailChimp.STATUS status) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email_address", email);
        jsonObject.put("status", status.toString().toLowerCase());

        Request request = new Request.Builder()
                .url( ((apiRoot.endsWith("/")) ? apiRoot + "lists/" + listId + "/members/" : apiRoot + "/lists/" + listId + "/members/") )
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "smatt " + apiKey)
                .post(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()))
                .build();

        OkHttpClient client = new OkHttpClient();
        Response serverResp = client.newCall(request).execute();
        String resp = serverResp.body().string();

        return resp;
    }


    public String listMembers(String listId) throws IOException {

        Request request = new Request.Builder()
                .url( ((apiRoot.endsWith("/")) ? apiRoot + "lists/" + listId + " /members/" : apiRoot + "/lists/" + listId + "/members/") )
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "smatt " + apiKey)
                .get()
                .build();

        OkHttpClient client = new OkHttpClient();
        Response serverResp = client.newCall(request).execute();
        String resp = serverResp.body().string();

        return resp;
    }

}
