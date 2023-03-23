package com.example.employeeinfo.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeinfo.R;
import com.example.employeeinfo.api.IApi;
import com.example.employeeinfo.api.Retrofit;
import com.example.employeeinfo.model.EmployeeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleEmployeeInfoActivity extends AppCompatActivity {
    TextView empId, empName, empEmail, empAddress, empNumber, empCompanyName, empWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_employee_info);
        Integer id = getIntent().getExtras().getInt(EmployeeListActivity.EMP_KEY);
        empId = findViewById(R.id.empId);
        empName = findViewById(R.id.empName);
        empAddress = findViewById(R.id.empAddress);
        empEmail = findViewById(R.id.empEmail);
        empNumber = findViewById(R.id.empNumber);
        empCompanyName =findViewById(R.id.empCompanyName);
        empWebsite = findViewById(R.id.empWebsite);

        getDataOfEmployeeById(id);
    }

    private void getDataOfEmployeeById(Integer id) {
        IApi apiInterface = Retrofit.getRetrofit().create(IApi.class);

        Call<EmployeeItem> data = apiInterface.getEmployee(id);

        data.enqueue(new Callback<EmployeeItem>() {
            @Override
            public void onResponse(@NonNull Call<EmployeeItem> call, Response<EmployeeItem> response) {
                if(response.isSuccessful()) {
                    EmployeeItem employeeItem = response.body();
                    if (employeeItem != null) {
                        empId.setText(String.valueOf(employeeItem.getId()));
                        empName.setText(employeeItem.getName());
                        empEmail.setText(employeeItem.getEmail());
                        empNumber.setText(employeeItem.getPhone());
                        empCompanyName.setText(employeeItem.getCompany().getName());
                        empWebsite.setText(employeeItem.getWebsite());
                        empAddress.setText(employeeItem.getAddress().getStreet()+", "+ employeeItem.getAddress().getCity()+", "+employeeItem.getAddress().getZipcode() +",\n"
                                +employeeItem.getAddress().getSuite()+ ", "+employeeItem.getAddress().getGeo());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EmployeeItem> call, Throwable t) {
                Log.i(TAG, "onFailure: "+ call);
                Log.i(TAG, "onFailure: "+ t);
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}