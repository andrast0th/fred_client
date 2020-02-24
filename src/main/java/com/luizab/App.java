package com.luizab;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    static {
        System.setProperty("http.proxyHost", "web-proxy.il.softwaregrp.net");
        System.setProperty("http.proxyPort", "8080");
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder
                .create()
                .useSystemProperties()
                .build();

        URIBuilder builder = new URIBuilder("http://wit.istc.cnr.it/stlab-tools/fred");
        builder.setParameter("text", "Depression is a disease")
                .setParameter("wfd_profile", "b");

        HttpGet request = new HttpGet(builder.build());
        // add request headers
        request.addHeader("Accept", "application/rdf+json");
        request.addHeader("Authorization", "Bearer a79f44e9-2e8a-32e7-8c4b-64107bbb14dd");

        HttpResponse response = httpClient.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println(responseJson.toString(5));

    }


}
