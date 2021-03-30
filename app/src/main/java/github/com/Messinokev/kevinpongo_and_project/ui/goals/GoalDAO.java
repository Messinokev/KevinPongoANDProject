package github.com.Messinokev.kevinpongo_and_project.ui.goals;

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
        currentGoals.add(new Goal("Travel to Paris",6500, "Description", new Date(), new Date()));
        currentGoals.add(new Goal("Travel to Paris",7500, "Description", new Date(), new Date()));
        currentGoals.add(new Goal("Travel to Paris",20000, "Description", new Date(), new Date()));
        currentGoals.add(new Goal("Travel to Paris",11000, "Description", new Date(), new Date()));
        currentGoals.add(new Goal("Travel to Paris",17650, "Description", new Date(), new Date()));
        currentGoals.add(new Goal("Travel to Paris",14500, "Description", new Date(), new Date()));
        allGoals.setValue(currentGoals);
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
