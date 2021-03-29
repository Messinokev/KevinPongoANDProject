package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GoalRepository {

    private GoalDAO goalDAO;
    private static GoalRepository instance;

    private GoalRepository(){
        goalDAO = GoalDAO.getInstance();
    }

    public static GoalRepository getInstance(){
        if(instance == null){
            instance = new GoalRepository();
        }
        return instance;
    }

    public LiveData<List<Goal>> getAllGoals(){
        return goalDAO.getAllGoals();
    }

    public void addGoal(Goal goal) {
        goalDAO.addGoal(goal);
    }


}
