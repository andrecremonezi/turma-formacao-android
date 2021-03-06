package br.com.cast.turmaformacao.taskmanager.model.http;


import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.taskmanager.model.entities.Address;

public class AddressService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private AddressService(){
        super();
    }


    public static Address getAddressByZipCode(String zipCode){
        Address address = null;
        try {
            URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, Address.class);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }


}
