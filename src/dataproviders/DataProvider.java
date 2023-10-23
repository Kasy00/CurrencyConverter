package dataproviders;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataProvider implements IDataProvider {
    private static DataProvider instance;

    private DataProvider(){

    }

    public static DataProvider getInstance(){
        if(instance == null){
            instance = new DataProvider();
        }
        return instance;
    }

    @Override
    public String fetchDataFromNBP(String url){
        try{
            URL nbpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) nbpURL.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }

                in.close();

                return response.toString();
            } else{
                throw new IOException("Error: Cannot download data. Response code: " + responseCode);
            }
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

