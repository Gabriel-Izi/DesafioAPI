package api;

import utils.PropertiesUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ApiParams {

    Map<String, String> params = new HashMap<>();
    PropertiesUtils prop = new PropertiesUtils();

    //Cria os parametros para acessar a api Marvel
    public Map<String, String> marvelParams() throws NoSuchAlgorithmException {
        prop.getProp("uri_marvel");

        params.put("ts", timestampMarvel());
        params.put("apikey",prop.getProp("publickey"));
        params.put("hash",hashMarvel());

        return params;
    }

    //Cria os parametros para simular login na api Petstore
    public Map<String, String> petstoreParams(){
        params.put("username", "Trundle123456");
        params.put("password", "trundle123");

        return params;
    }

    //Cria a medida de tempo para o parametro "ts" na api Marvel
    private String timestampMarvel(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }

    //Cria o hashcode do parametro hash na api Marvel
    private String hashMarvel() throws NoSuchAlgorithmException {

        String timestamp = timestampMarvel();
        String privateKey = prop.getProp("privatekey");
        String publicKey = prop.getProp("publickey");

        String hash = timestamp+privateKey+publicKey;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(hash.getBytes(),0,hash.length());

        return new BigInteger(1,m.digest()).toString(16);
    }


}
