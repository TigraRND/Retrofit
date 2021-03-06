package ru.retrofit.reqres.in.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.retrofit.reqres.in.dto.requests.LoginPasswordReq;

public interface AuthorizationService {

    @POST("register")
    Call<ResponseBody> userRegistration(@Body LoginPasswordReq body);

    @POST("login")
    Call<ResponseBody> userAuthorization(@Body LoginPasswordReq body);
}
