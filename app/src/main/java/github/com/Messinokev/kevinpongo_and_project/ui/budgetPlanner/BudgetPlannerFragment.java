package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import github.com.Messinokev.kevinpongo_and_project.R;

public class BudgetPlannerFragment extends Fragment {

    private BudgetPlannerViewModel budgetPlannerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetPlannerViewModel =
                new ViewModelProvider(this).get(BudgetPlannerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_budget_planner, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        budgetPlannerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}