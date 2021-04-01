package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BudgetPlanner {

    private String Title;
    private LiveData<List<Category>> categories;


    public BudgetPlanner(String title, LiveData<List<Category>> categories) {
        Title = title;
        this.categories = categories;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public void setCategories(LiveData<List<Category>> categories) {
        this.categories = categories;
    }

}
