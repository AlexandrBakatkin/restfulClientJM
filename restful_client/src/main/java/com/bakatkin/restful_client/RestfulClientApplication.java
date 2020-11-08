package com.bakatkin.restful_client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestfulClientApplication {

    static final String SERVER_URL = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(SERVER_URL, HttpMethod.GET, entity, String.class);

        String sessionID = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        System.out.println("Создание пользователя: ");


        Map<String, Object> user = new LinkedHashMap<>();
        user.put("id", 3);
        user.put("name", "James");
        user.put("lastName", "Brown");
        user.put("age", (byte) 27);


        HttpHeaders headerPOST = new HttpHeaders();
        headerPOST.set(HttpHeaders.COOKIE, sessionID);
        HttpEntity<?> entityPOST = new HttpEntity<>(user, headerPOST);

        HttpEntity<String> responseGet = restTemplate.exchange(SERVER_URL, HttpMethod.POST, entityPOST, String.class);
        System.out.println("Код: " + responseGet.getBody());

        System.out.println("Редактирование пользователя: ");
        user.put("name", "Thomas");
        user.put("lastName", "Shelby");

        HttpEntity<?> entityPUT = new HttpEntity<>(user, headerPOST);
        HttpEntity<String> responsePUT = restTemplate.exchange(SERVER_URL, HttpMethod.PUT, entityPUT, String.class);
        System.out.println("Код: " + responsePUT.getBody());

        System.out.println("Удаление пользователя:");

        HttpEntity<String> entityDELETE = new HttpEntity<>(headerPOST);
        HttpEntity<String> responseEntityDel = restTemplate.exchange(SERVER_URL + "/" + user.get("id"), HttpMethod.DELETE, entityDELETE, String.class);
        System.out.println("Код: " + responseEntityDel.getBody());
    }
}