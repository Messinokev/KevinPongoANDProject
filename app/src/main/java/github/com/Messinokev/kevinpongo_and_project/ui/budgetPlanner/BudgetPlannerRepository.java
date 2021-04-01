package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;


public class BudgetPlannerRepository {
    private BudgetPlannerDAO budgetPlannerDAO;
    private static BudgetPlannerRepository instance;

    private BudgetPlannerRepository() {
        budgetPlannerDAO = BudgetPlannerDAO.getInstance();
    }

    public static BudgetPlannerRepository getInstance() {
        if (instance == null) {
            instance = new BudgetPlannerRepository();
        }
        return instance;
    }

    public LiveData<BudgetPlanner> getBudgetPlanner() {
        return budgetPlannerDAO.getBudgetPlanner();
    }

    public void createBudgetPlanner(BudgetPlanner budgetPlanner) {
        budgetPlannerDAO.createBudgetPlanner(budgetPlanner);
    }
}
