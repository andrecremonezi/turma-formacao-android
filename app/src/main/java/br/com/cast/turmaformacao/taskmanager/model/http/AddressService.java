package br.com.cast.turmaformacao.taskmanager.model.http;

import android.location.Address;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private AddressService(){
        super();
    }


    public static Address getAddressByZipCode(String zipCode){
        Address address = null;

        try{
            URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");

            int responseCode = conn.getResponseCode();
            Log.e("getAddressByCep","C�digo de retorno da requisi��o de cep: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream, Address.class);

                conn.disconnect();
            }
        }catch(Exception e){
            Log.e(AddressService.class.getName(),"" + e.getMessage());
        }

        return address;
    }


}
