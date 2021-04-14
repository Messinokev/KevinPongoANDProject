package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import github.com.Messinokev.kevinpongo_and_project.model.ProjectDatabase;


public class BudgetPlannerRepository {
    private BudgetPlannerDAO budgetPlannerDAO;
    private static BudgetPlannerRepository instance;
    private ExecutorService executorService;

    private BudgetPlannerRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getInstance(application);
        budgetPlannerDAO = database.budgetPlannerDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized BudgetPlannerRepository getInstance(Application application) {
        if (instance == null) {
            instance = new BudgetPlannerRepository(application);
        }
        return instance;
    }

    public LiveData<List<Category>> getCategories() {
        return budgetPlannerDAO.getAllCategories();
    }

    public void createCategory(Category category) {
        executorService.execute(() -> budgetPlannerDAO.createCategory(category));
    }
}
