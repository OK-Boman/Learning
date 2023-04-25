package com.okboman.urlprinter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boman
 */
public class UrlPrinter {

    public static void main(String[] args) {
        System.out.println("Etsi sähkön hinta vuodelle ja kuukaudelle (2021M12)");
        try {
            httpClientPost("2021M12");
        } catch (Exception ex) {
            Logger.getLogger(UrlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static String httpClientPost(String ym) throws Exception {
        String url = "https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/ehi/statfin_ehi_pxt_12gc.px";
        String json = "{\n" +
"  \"query\": [\n" +
"    {\n" +
"      \"code\": \"Kuukausi\",\n" +
"      \"selection\": {\n" +
"        \"filter\": \"item\",\n" +
"        \"values\": [\n" +
"          \""+ym+"\"\n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"code\": \"Nord Pool -alue\",\n" +
"      \"selection\": {\n" +
"        \"filter\": \"item\",\n" +
"        \"values\": [\n" +
"          \"G\"\n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"code\": \"Tiedot\",\n" +
"      \"selection\": {\n" +
"        \"filter\": \"item\",\n" +
"        \"values\": [\n" +
"          \"hinta_sahko_eur_mwh\"\n" +
"        ]\n" +
"      }\n" +
"    }\n" +
"  ],\n" +
"  \"response\": {\n" +
"    \"format\": \"json-stat2\"\n" +
"  }\n" +
"}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        json = response.body();
        System.out.println(json);
        return json;
    }
}
