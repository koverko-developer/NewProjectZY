package com.example.anika.newprojectzy.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by x on 07.06.2017.
 */

public interface Api {
    @GET("zy/test.php")
    Call<Response> getData();


    @FormUrlEncoded
    @POST("zy/get_upload_v.php")
    Call<Response> setVacansy(@Field("name") String name,
                              @Field("dolzhnost") String dolzhnost,
                              @Field("trebovaniya") String trebovaniya,
                              @Field("alldescr") String alldescr,
                              @Field("unp") String unp,
                              @Field("phone") String phone,
                              @Field("description") String description,
                              @Field("town") String town);
    @FormUrlEncoded
    @POST("zy/get_upload_r.php")
    Call<Response> setResume(@Field("fio") String name,
                             @Field("speciality") String speciality,
                             @Field("opit") String opit,
                             @Field("education") String education,
                             @Field("skills") String skills,
                             @Field("phone") String phone,
                             @Field("img") String img,
                             @Field("description") String description,
                             @Field("town") String town

    );
    @FormUrlEncoded
    @POST("zy/upload_ob_to_server.php")
    Call<Response> setAdv(@Field("pid") int pid,
                          @Field("image") String image,
                          @Field("description") String description,
                          @Field("fio") String fio,
                          @Field("email") String email,
                          @Field("phone") String phone,
                          @Field("name") String name,
                          @Field("town") String town

    );
    @FormUrlEncoded
    @POST("zy/upload_org_to_server.php")
    Call<Response> setOrg(@Field("name") String name,
                          @Field("unp") String unp,
                          @Field("director") String director,
                          @Field("rs") String rs,
                          @Field("osnova") String osnova,
                          @Field("forma") String forma,
                          @Field("email") String email,
                          @Field("phone") String phone,
                          @Field("img") String img,
                          @Field("description") String description

    );

    @GET("zy/get_advertising.php")
    Call<List<Advertising>> getAdvertising(@Query("offset") int offset, @Query("q") int q);

    @GET("zy/get_resume.php")
    Call<List<Resume>> getResume(@Query("offset") int offset);

    @GET("zy/get_vacansy.php")
    Call<List<Vacansy>> getVacansy(@Query("offset") int offset);

    @GET("zy/get_advertising_id.php")
    Call<List<Advertising>> getAdvertisingID(@Query("id") int id);

    @GET("zy/get_resume_id.php")
    Call<List<Resume>> getResumeID(@Query("id") int id);


    @GET("zy/get_vacansy_id.php")
    Call<List<Vacansy>> getVacansyID(@Query("id") int id);

}
