package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class GoalsViewModel extends ViewModel {

    private GoalRepository repository;

    public GoalsViewModel() {
        repository = GoalRepository.getInstance();
    }

    public LiveData<List<Goal>> getAllGoals() {
        return repository.getAllGoals();
    }

    public void addGoal(final Goal goal){
        repository.addGoal(goal);
    }
}