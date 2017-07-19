package com.example.anika.newprojectzy.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by x on 07.06.2017.
 */

public class App extends Application {

    private static Api umoriliApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://anika-cs.by/server/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(Api.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static Api getApi() {
        return umoriliApi;
    }
}
