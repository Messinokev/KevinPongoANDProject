package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;
import github.com.Messinokev.kevinpongo_and_project.ui.depositHistory.DepositHistory;
import github.com.Messinokev.kevinpongo_and_project.ui.depositHistory.DepositHistoryViewModel;

public class BudgetPlannerAdapter extends RecyclerView.Adapter<BudgetPlannerAdapter.ViewHolder> {

    List<Category> budgetPlannerCategories;
    BudgetPlannerViewModel budgetPlannerViewModel;
    DepositHistoryViewModel depositHistoryViewModel;


    public BudgetPlannerAdapter(BudgetPlannerViewModel budgetPlannerViewModel, DepositHistoryViewModel depositHistoryViewModel) {
        this.budgetPlannerViewModel = budgetPlannerViewModel;
        this.depositHistoryViewModel = depositHistoryViewModel;
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
        holder.categoryName.setText(budgetPlannerCategories.get(position).getName());
        Resources getResource = holder.itemView.getContext().getResources();
        holder.categoryPrice.setText(getResource.getString(R.string.planText) + budgetPlannerCategories.get(position).getPrice());
        holder.inDeposit.setText(getResource.getString(R.string.spentText) + budgetPlannerCategories.get(position).getDeposit());
        holder.average.setText(getResource.getString(R.string.avgText) + budgetPlannerCategories.get(position).calculateAveragePerDay() + getResource.getString(R.string.dayText));
        holder.userAverage.setText(getResource.getString(R.string.your_avg_dayText) + budgetPlannerCategories.get(position).calculateUserAveragePerDay() + getResource.getString(R.string.dayText));

        Date today = new Date();

        //you can modify by back in  time but not in the future
        if (budgetPlannerCategories.get(position).getStartDate() <= today.getTime()) {
            holder.depositButton.setOnClickListener(v -> {
                if (!holder.deposit.getText().toString().equals("")) {
                    String title = budgetPlannerViewModel.getBudgetPlannerTitle() + " - " + holder.categoryName.getText().toString();
                    DepositHistory newDepositHistory = new DepositHistory(title, Integer.parseInt(holder.deposit.getText().toString()), new Date().getTime());
                    depositHistoryViewModel.createDepositHistory(newDepositHistory);


                    int deposit = Integer.parseInt(holder.deposit.getText().toString());
                    budgetPlannerViewModel.depositMoney(budgetPlannerCategories.get(position).getId(), deposit);
                    holder.inDeposit.setText(getResource.getString(R.string.spentText) + budgetPlannerCategories.get(position).getDeposit());
                    holder.userAverage.setText(getResource.getString(R.string.your_avg_dayText) + budgetPlannerCategories.get(position).calculateUserAveragePerDay() + getResource.getString(R.string.dayText));
                    holder.deposit.setText("");
                }
            });
        }

        imageChange(holder, position);
    }

    @Override
    public int getItemCount() {
        if (budgetPlannerCategories == null) {
            return 0;
        } else {
            return budgetPlannerCategories.size();
        }
    }

    public void updateData(List<Category> categories) {
        budgetPlannerCategories = categories;
        notifyDataSetChanged();
    }

    public void imageChange(@NonNull BudgetPlannerAdapter.ViewHolder holder, int position) {
        Resources getResource = holder.itemView.getContext().getResources();
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.foodText))) {
            holder.image.setImageResource(R.drawable.food);
        }
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.rentText))) {
            holder.image.setImageResource(R.drawable.rent);
        }
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.hobbyText))) {
            holder.image.setImageResource(R.drawable.hobby);
        }
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.socialText))) {
            holder.image.setImageResource(R.drawable.social);
        }
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.travelText))) {
            holder.image.setImageResource(R.drawable.travel);
        }
        if (budgetPlannerCategories.get(position).getName().equals(getResource.getString(R.string.otherText))) {
            holder.image.setImageResource(R.drawable.other);
        }
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
