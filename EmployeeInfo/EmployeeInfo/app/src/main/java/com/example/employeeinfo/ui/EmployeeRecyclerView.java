package com.example.employeeinfo.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeinfo.R;
import com.example.employeeinfo.model.EmployeeItem;

import java.util.List;

public class EmployeeRecyclerView extends RecyclerView.Adapter<EmployeeRecyclerView.EmployeeViewHolder> {
    List<EmployeeItem> employeeList;
    public EmployeeRecyclerView(List<EmployeeItem> employeeList) {
        this.employeeList = employeeList;
    }
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_card, null);
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.employeeName.setText(employeeList.get(position).getName());
        holder.employeeEmail.setText(employeeList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView employeeName, employeeEmail;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.employeeName);
            employeeEmail = itemView.findViewById(R.id.employeeEmail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int currPosition = getAdapterPosition();
            EmployeeItem employeeItem = employeeList.get(currPosition);
            Intent intent = new Intent(view.getContext(), SingleEmployeeInfoActivity.class);
            intent.putExtra(EmployeeListActivity.EMP_KEY, employeeItem.getId());
            view.getContext().startActivity(intent);
        }
    }
}
