package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

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