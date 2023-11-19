package data_access;

import java.awt.*;
import java.io.IOException;
import java.net.URLEncoder;

public class GoogleTranslationDAO implements GoogleTranslationDAOInterface{
    private static final String TRANSLATION_URL = "https://google-translate113.p.rapidapi.com/api/v1/translator/text";
    private static final String RAPID_API_KEY = "8c57ce2190msh1ce89404b846d18p19077ejsna4f41ffae203";
    private final OkHttpClient client;

    public GoogleTranslationDAO() {
        this.client = new OkHttpClient();
    }

    public String translate(String text, String targetLanguage) {
        try {
            PageAttributes.MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String requestBody = String.format("from=auto&to=%s&text=%s", targetLanguage, URLEncoder.encode(text, "UTF-8"));

            Request request = new Request.Builder()
                    .url(TRANSLATION_URL)
                    .post(RequestBody.create(mediaType, requestBody))
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                    .addHeader("X-RapidAPI-Host", "google-translate113.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Translation failed. HTTP Code: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Translation failed. Exception: " + e.getMessage();
        }
    }
}
