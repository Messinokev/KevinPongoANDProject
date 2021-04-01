package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



public class BudgetPlannerDAO {

    private MutableLiveData<BudgetPlanner> budgetPlanner;
    private static BudgetPlannerDAO instance;

    private BudgetPlannerDAO() {
        budgetPlanner = new MutableLiveData<>();
    }

    public static BudgetPlannerDAO getInstance() {
        if (instance == null) {
            instance = new BudgetPlannerDAO();
        }
        return instance;
    }

    public LiveData<BudgetPlanner> getBudgetPlanner() {
        return budgetPlanner;
    }

    public void createBudgetPlanner(BudgetPlanner budgetPlanner){
        this.budgetPlanner.setValue(budgetPlanner);
    }
}
