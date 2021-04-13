package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import github.com.Messinokev.kevinpongo_and_project.model.ProjectDatabase;

public class GoalRepository {

    private GoalDAO goalDAO;
    private static GoalRepository instance;
    private ExecutorService executorService;

    private GoalRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getInstance(application);
        goalDAO = database.goalDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized GoalRepository getInstance(Application application) {
        if (instance == null) {
            instance = new GoalRepository(application);
        }
        return instance;
    }

    public LiveData<List<Goal>> getAllGoals() {
        return goalDAO.getAllGoals();
    }

    public void addGoal(Goal goal) {
        executorService.execute(() -> goalDAO.addGoal(goal));
    }

    public LiveData<Goal> getGoalById(int id){
        return goalDAO.getGoalById(id);
    }

}
