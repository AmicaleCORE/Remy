package org.amicale_core.models;

import org.amicale_core.Remy;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public record YoutubeResponse(String title, String thumbnail) {
    @Nullable
    public static YoutubeResponse getYoutubeVideoInfos(String videoId){
        try{
            String apiKey = Remy.CONFIG.getYoutubeApiKey();
            URL url = new URL("https://www.googleapis.com/youtube/v3/videos?id=%s&key=%s&part=snippet,contentDetails,statistics,status&fields=items/snippet/title,items/snippet/thumbnails/standard".formatted(videoId, apiKey));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String response = content.toString();
            String title = response.split("\"title\": ")[1].split("\", ")[0].replace("\"", "");
            String thumbnail = response.split("\"url\": ")[1].split("\", ")[0].replace("\"", "");
            return new YoutubeResponse(title, thumbnail);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
