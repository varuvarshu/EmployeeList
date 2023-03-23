package com.example.employeeinfo.api;
import com.example.employeeinfo.model.EmployeeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApi {
    @GET("users")
    Call<List<EmployeeItem>> getAllEmployee();

    @GET("users/{userId}")
    Call<EmployeeItem> getEmployee(@Path("userId") Integer userId);
}
