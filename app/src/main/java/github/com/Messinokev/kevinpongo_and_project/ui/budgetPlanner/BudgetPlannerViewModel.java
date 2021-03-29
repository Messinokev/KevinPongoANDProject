package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BudgetPlannerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BudgetPlannerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is budget planner fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}