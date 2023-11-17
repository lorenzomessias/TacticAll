/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 *
 * @author Everymind
 */
public class SoccerService {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v3/players?id=276&season=2020")
                .header("X-RapidAPI-Key", "136fbf6d41msh6a5e6fa2e866009p16051cjsnb29cc94876bb")
                .header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }
}
