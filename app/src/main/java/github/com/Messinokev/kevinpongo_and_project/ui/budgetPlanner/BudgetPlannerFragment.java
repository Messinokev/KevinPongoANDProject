package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import github.com.Messinokev.kevinpongo_and_project.R;
import github.com.Messinokev.kevinpongo_and_project.ui.depositHistory.DepositHistoryViewModel;

public class BudgetPlannerFragment extends Fragment {

    private BudgetPlannerViewModel budgetPlannerViewModel;
    private DepositHistoryViewModel depositHistoryViewModel;
    private TextView emptyTextView;
    private Button createButton;
    private TextView startDateTextView;
    private TextView startDate;
    private TextView endDateTextView;
    private TextView endDate;
    private RecyclerView recyclerView;
    private SharedPreferences preferences;
    private int categoriesSize;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetPlannerViewModel =
                new ViewModelProvider(this).get(BudgetPlannerViewModel.class);
        depositHistoryViewModel = new ViewModelProvider(this).get(DepositHistoryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_budget_planner, container, false);

        //needed to see the trash icon
        setHasOptionsMenu(true);
        //get the shared preferences
        preferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        emptyTextView = root.findViewById(R.id.budgetPlannerEmptyTextView);
        createButton = root.findViewById(R.id.budgetPlannerGoCreateButton);
        startDateTextView = root.findViewById(R.id.budgetStartDateTextView);
        startDate = root.findViewById(R.id.budgetStartDate);
        endDateTextView = root.findViewById(R.id.budgetEndDateTextView);
        endDate = root.findViewById(R.id.budgetEndDate);

        //set an onClickListener to the budgetCreate button, which opens the create activity
        createButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateBudgetPlannerActivity.class);
            startActivity(intent);
            emptyTextView.setVisibility(View.INVISIBLE);
            createButton.setVisibility(View.INVISIBLE);
        });

        recyclerView = root.findViewById(R.id.budgetRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();
        BudgetPlannerAdapter adapter = new BudgetPlannerAdapter(budgetPlannerViewModel,depositHistoryViewModel);
        recyclerView.setAdapter(adapter);

        budgetPlannerViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            adapter.updateData(categories);

            //To check that any budget planner created already or not
            categoriesSize = categories.size();

            if (categoriesSize == 0) {
                emptyTextView.setVisibility(View.VISIBLE);
                createButton.setVisibility(View.VISIBLE);
                startDate.setVisibility(View.INVISIBLE);
                endDate.setVisibility(View.INVISIBLE);
                endDateTextView.setVisibility(View.INVISIBLE);
                startDateTextView.setVisibility(View.INVISIBLE);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_budgetPlanner));
            } else {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(preferences.getString("title","Budget Planner"));

                budgetPlannerViewModel.setBudgetPlannerTitle(preferences.getString("title","Budget Planner"));

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

    /**
     * Add the menu what I created (trash icon for deletion)
     * @param menu
     * @return
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.toolbar_menu,menu);
         super.onCreateOptionsMenu(menu,inflater);
    }

    /**
     * This method is responsible for the action should happen if the trash (deletion) icon is clicked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item){
        int itemId = item.getItemId();

        if (itemId == R.id.navigation_delete){
            if (categoriesSize == 0){
                Toast.makeText(this.getContext(),R.string.nothingToDeleteMessage, Toast.LENGTH_SHORT).show();
            }else {
                budgetPlannerViewModel.deleteCategories();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}