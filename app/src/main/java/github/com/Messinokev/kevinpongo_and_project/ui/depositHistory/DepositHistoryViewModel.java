package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner.BudgetPlannerRepository;
import github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner.Category;

public class DepositHistoryViewModel extends AndroidViewModel {

    private DepositHistoryRepository repository;

    public DepositHistoryViewModel(Application application) {
        super(application);
        repository = DepositHistoryRepository.getInstance(application);
    }

    public LiveData<List<DepositHistory>> getAllDepositHistory() {
        return repository.getAllDepositHistory();
    }

    public void createDepositHistory(final DepositHistory depositHistory){
        repository.createDepositHistory(depositHistory);
    }
}