package ru.otus.APIHelpers.services;

import retrofit2.Call;
import retrofit2.http.*;
import ru.otus.APIHelpers.dto.requests.CreateUserReq;
import ru.otus.APIHelpers.dto.responses.*;

public interface UserService {
    @GET("users")
    Call<ListUsersResp> getUserList(@Query("page") int pageNum);

    @GET("users/{id}")
    Call<SingleUserResp> getUser(@Path("id") int id);

    @POST("users")
    Call<CreateUserResp> createUser(@Body CreateUserReq body);

    @PUT("users/{id}")
    Call<UpdateUserResp> updateUserPut(@Path("id") int userId, @Body CreateUserReq body);

    @PATCH("users/{id}")
    Call<UpdateUserResp> updateUserPatch(@Path("id") int userId, @Body CreateUserReq body);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}