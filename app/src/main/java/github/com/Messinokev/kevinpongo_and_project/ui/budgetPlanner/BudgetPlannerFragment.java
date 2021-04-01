package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

import github.com.Messinokev.kevinpongo_and_project.R;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalAdapter;

public class BudgetPlannerFragment extends Fragment {

    private BudgetPlannerViewModel budgetPlannerViewModel;
    private BudgetPlanner budgetPlanner;

    TextView emptyTextView;
    Button createButton;

    TextView startDateTextView;
    TextView startDate;
    TextView endDateTextView;
    TextView endDate;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetPlannerViewModel =
                new ViewModelProvider(this).get(BudgetPlannerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_budget_planner, container, false);

        budgetPlanner = budgetPlannerViewModel.getBudgetPlanner().getValue();

        emptyTextView = root.findViewById(R.id.budgetPlannerEmptyTextView);
        createButton = root.findViewById(R.id.budgetPlannerGoCreateButton);
        startDateTextView = root.findViewById(R.id.budgetStartDateTextView);
        startDate = root.findViewById(R.id.budgetStartDate);
        endDateTextView = root.findViewById(R.id.budgetEndDateTextView);
        endDate = root.findViewById(R.id.budgetEndDate);

        createButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateBudgetPlannerActivity.class);
            startActivity(intent);
            emptyTextView.setVisibility(View.INVISIBLE);
            createButton.setVisibility(View.INVISIBLE);
        });

        if (budgetPlanner == null) {
            emptyTextView.setVisibility(View.VISIBLE);
            createButton.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.INVISIBLE);
            endDate.setVisibility(View.INVISIBLE);
            endDateTextView.setVisibility(View.INVISIBLE);
            startDateTextView.setVisibility(View.INVISIBLE);
        } else {
            recyclerView = root.findViewById(R.id.budgetRV);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.hasFixedSize();
            BudgetPlannerAdapter adapter = new BudgetPlannerAdapter(budgetPlannerViewModel.getBudgetPlanner().getValue().getCategories());
            recyclerView.setAdapter(adapter);

            startDate.setVisibility(View.VISIBLE);
            endDate.setVisibility(View.VISIBLE);
            endDateTextView.setVisibility(View.VISIBLE);
            startDateTextView.setVisibility(View.VISIBLE);

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(budgetPlanner.getTitle());
            emptyTextView.setVisibility(View.INVISIBLE);
            createButton.setVisibility(View.INVISIBLE);

            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String startDateString = simpleDateFormat.format(budgetPlannerViewModel.getBudgetPlanner().getValue().getCategories().getValue().get(0).getStartDate());
            String endDateString = simpleDateFormat.format(budgetPlannerViewModel.getBudgetPlanner().getValue().getCategories().getValue().get(0).getEndDate());

            startDate.setText(startDateString);
            endDate.setText(endDateString);
        }

        return root;
    }
}