package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class BudgetPlannerAdapter extends RecyclerView.Adapter<BudgetPlannerAdapter.ViewHolder> {

    LiveData<List<Category>> budgetPlannerCategories;

    public BudgetPlannerAdapter(LiveData<List<Category>> budgetPlannerCategories) {
        this.budgetPlannerCategories = budgetPlannerCategories;
    }

    @NonNull
    @Override
    public BudgetPlannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.budget_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetPlannerAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(budgetPlannerCategories.getValue().get(position).getName());
        holder.categoryPrice.setText("Plan: " + budgetPlannerCategories.getValue().get(position).getPrice());
        holder.inDeposit.setText("Spent: " + budgetPlannerCategories.getValue().get(position).getDeposit());
        holder.average.setText("Avg: "+budgetPlannerCategories.getValue().get(position).calculateAveragePerDay() + " /Day");
        holder.userAverage.setText("Your Avg: " + budgetPlannerCategories.getValue().get(position).calculateUserAveragePerDay() + " /Day");

        Date today = new Date();

        //you can modify by back in the time but not in the future
        if (budgetPlannerCategories.getValue().get(position).getStartDate().getTime() <= today.getTime()) {
            holder.depositButton.setOnClickListener(v -> {
                int deposit = Integer.parseInt(holder.deposit.getText().toString());
                budgetPlannerCategories.getValue().get(position).setDeposit(deposit);
                holder.inDeposit.setText("Spent: " + budgetPlannerCategories.getValue().get(position).getDeposit());
                holder.userAverage.setText("Your Avg: " + budgetPlannerCategories.getValue().get(position).calculateUserAveragePerDay() + " /Day");
                holder.deposit.setText("");
            });
        }

        if (budgetPlannerCategories.getValue().get(position).getName().equals("Food")){
            holder.image.setImageResource(R.drawable.food);
        }
        if (budgetPlannerCategories.getValue().get(position).getName().equals("Rent")){
            holder.image.setImageResource(R.drawable.rent);
        }
        if (budgetPlannerCategories.getValue().get(position).getName().equals("Hobby")){
            holder.image.setImageResource(R.drawable.hobby);
        }
        if (budgetPlannerCategories.getValue().get(position).getName().equals("Social")){
            holder.image.setImageResource(R.drawable.social);
        }
        if (budgetPlannerCategories.getValue().get(position).getName().equals("Travel")){
            holder.image.setImageResource(R.drawable.travel);
        }
        if (budgetPlannerCategories.getValue().get(position).getName().equals("Other")){
            holder.image.setImageResource(R.drawable.other);
        }
    }

    @Override
    public int getItemCount() {
        return budgetPlannerCategories.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView categoryName;
        TextView categoryPrice;
        TextView inDeposit;
        TextView average;
        TextView userAverage;
        Button depositButton;
        EditText deposit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.budgetPlannerCategoryImage);
            categoryName = itemView.findViewById(R.id.budgetPlannerCategoryName);
            categoryPrice = itemView.findViewById(R.id.budgetPlannerCategoryPrice);
            inDeposit = itemView.findViewById(R.id.budgetPlannerInDeposit);
            average = itemView.findViewById(R.id.budgetPlannerAverage);
            userAverage = itemView.findViewById(R.id.budgetPlannerUserAverage);
            depositButton = itemView.findViewById(R.id.budgetPlannerAddDepositButton);
            deposit = itemView.findViewById(R.id.budgetPlannerDeposit);
        }
    }
}
