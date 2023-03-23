package com.example.employeeinfo.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.employeeinfo.R;
import com.example.employeeinfo.api.IApi;
import com.example.employeeinfo.api.Retrofit;
import com.example.employeeinfo.model.EmployeeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {
    public static String EMP_KEY = "empId";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        getDataAndList();
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false)
        );
    }

    private void getDataAndList() {
        IApi apiInterface = Retrofit.getRetrofit().create(IApi.class);
        Call<List<EmployeeItem>> data = apiInterface.getAllEmployee();

        data.enqueue(new Callback<List<EmployeeItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<EmployeeItem>> call, Response<List<EmployeeItem>> response) {
                if(response.isSuccessful()) {
                    EmployeeRecyclerView employeeRecyclerAdapter = new EmployeeRecyclerView(response.body());
                    recyclerView.setAdapter(employeeRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EmployeeItem>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+ call);
                Log.i(TAG, "onFailure: "+ t);
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}