package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class GoalDAO {
    private MutableLiveData<List<Goal>> allGoals;
    private static GoalDAO instance;

    private GoalDAO() {
        allGoals = new MutableLiveData<>();
        List<Goal> newList = new ArrayList<>();
        allGoals.setValue(newList);

    }

    public static GoalDAO getInstance() {
        if (instance == null) {
            instance = new GoalDAO();
        }
        return instance;
    }

    public MutableLiveData<List<Goal>> getAllGoals() {
        return allGoals;
    }

    public void addGoal(Goal goal){
        List<Goal> currentGoals = allGoals.getValue();
        currentGoals.add(goal);
        allGoals.setValue(currentGoals);
    }
}
