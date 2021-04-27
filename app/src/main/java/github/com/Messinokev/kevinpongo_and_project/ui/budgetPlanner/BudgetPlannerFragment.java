package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class BudgetPlannerFragment extends Fragment {

    private BudgetPlannerViewModel budgetPlannerViewModel;

    private TextView emptyTextView;
    private Button createButton;

    private TextView startDateTextView;
    private TextView startDate;
    private TextView endDateTextView;
    private TextView endDate;

    private RecyclerView recyclerView;

    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetPlannerViewModel =
                new ViewModelProvider(this).get(BudgetPlannerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_budget_planner, container, false);

        preferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(preferences.getString("title","Budget Planner"));

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

        recyclerView = root.findViewById(R.id.budgetRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();
        BudgetPlannerAdapter adapter = new BudgetPlannerAdapter(budgetPlannerViewModel);
        recyclerView.setAdapter(adapter);

        budgetPlannerViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            adapter.updateData(categories);

            if (categories.size() == 0) {
                emptyTextView.setVisibility(View.VISIBLE);
                createButton.setVisibility(View.VISIBLE);
                startDate.setVisibility(View.INVISIBLE);
                endDate.setVisibility(View.INVISIBLE);
                endDateTextView.setVisibility(View.INVISIBLE);
                startDateTextView.setVisibility(View.INVISIBLE);
            } else {
                startDate.setVisibility(View.VISIBLE);
                endDate.setVisibility(View.VISIBLE);
                endDateTextView.setVisibility(View.VISIBLE);
                startDateTextView.setVisibility(View.VISIBLE);


                emptyTextView.setVisibility(View.INVISIBLE);
                createButton.setVisibility(View.INVISIBLE);

                String pattern = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String startDateString = simpleDateFormat.format(categories.get(0).getStartDate());
                String endDateString = simpleDateFormat.format(categories.get(0).getEndDate());

                startDate.setText(startDateString);
                endDate.setText(endDateString);
            }
        });

        return root;
    }
}