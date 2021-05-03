package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GoalsViewModel extends AndroidViewModel {

    private GoalRepository repository;

    public GoalsViewModel(Application application) {
        super(application);
        repository = GoalRepository.getInstance(application);
    }

    public LiveData<List<Goal>> getAllGoals() {
        return repository.getAllGoals();
    }

    public void addGoal(final Goal goal){
        repository.addGoal(goal);
    }

    public LiveData<Goal> getGoalById(int id){
        return repository.getGoalById(id);
    }

    public void depositMoney(int id, int deposit){
        repository.depositMoney(id,deposit);
    }

    public void deleteGoal(int id){
        repository.deleteGoal(id);
    }
}