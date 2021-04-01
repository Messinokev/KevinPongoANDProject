package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.ui.goals.Goal;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalRepository;

public class BudgetPlannerViewModel extends ViewModel {

    private BudgetPlannerRepository repository;

    public BudgetPlannerViewModel() {
        repository = BudgetPlannerRepository.getInstance();
    }

    public LiveData<BudgetPlanner> getBudgetPlanner() {
        return repository.getBudgetPlanner();
    }

    public void createBudgetPlanner(final BudgetPlanner budgetPlanner){
        repository.createBudgetPlanner(budgetPlanner);
    }
}