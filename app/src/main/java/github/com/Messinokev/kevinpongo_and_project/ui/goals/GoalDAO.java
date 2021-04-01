package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoalDAO {
    private MutableLiveData<List<Goal>> allGoals;
    private static GoalDAO instance;

    private GoalDAO() {
        allGoals = new MutableLiveData<>();
        List<Goal> newList = new ArrayList<>();
        allGoals.setValue(newList);

        List<Goal> currentGoals = allGoals.getValue();
        currentGoals.add(new Goal("Travel to Paris",6500, "Travel to Paris", new Date(), new Date()));
        currentGoals.add(new Goal("Volvo F50",7500, "Volvo F50", new Date(), new Date()));
        currentGoals.add(new Goal("Birthday present to Mom",20000, "Birthday present to Mom", new Date(), new Date()));
        allGoals.setValue(currentGoals);
    }

    public static GoalDAO getInstance() {
        if (instance == null) {
            instance = new GoalDAO();
        }
        return instance;
    }

    public LiveData<List<Goal>> getAllGoals() {
        return allGoals;
    }

    public void addGoal(Goal goal){
        List<Goal> currentGoals = allGoals.getValue();
        currentGoals.add(goal);
        allGoals.setValue(currentGoals);
    }
}
