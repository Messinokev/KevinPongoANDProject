package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;


public class BudgetPlannerViewModel extends AndroidViewModel {

    private String budgetPlannerTitle = "";

    private BudgetPlannerRepository repository;

    public BudgetPlannerViewModel(Application application) {
        super(application);
        repository = BudgetPlannerRepository.getInstance(application);
    }

    public LiveData<List<Category>> getCategories() {
        return repository.getCategories();
    }

    public void createCategory(final Category category){
        repository.createCategory(category);
    }

    public void depositMoney(int id, int deposit){
        repository.depositMoney(id,deposit);
    }

    public void deleteCategories(){
        repository.deleteCategories();
    }

    public String getBudgetPlannerTitle() {
        return budgetPlannerTitle;
    }

    public void setBudgetPlannerTitle(String budgetPlannerTitle) {
        this.budgetPlannerTitle = budgetPlannerTitle;
    }

}