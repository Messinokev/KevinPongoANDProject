package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import github.com.Messinokev.kevinpongo_and_project.model.ProjectDatabase;
import github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner.Category;

public class DepositHistoryRepository {

    private DepositHistoryDAO depositHistoryDAO;
    private static DepositHistoryRepository instance;
    private ExecutorService executorService;

    private DepositHistoryRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getInstance(application);
        depositHistoryDAO = database.depositHistoryDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized DepositHistoryRepository getInstance(Application application) {
        if (instance == null) {
            instance = new DepositHistoryRepository(application);
        }
        return instance;
    }

    public LiveData<List<DepositHistory>> getAllDepositHistory() {
        return depositHistoryDAO.getAllDepositHistory();
    }

    public void createDepositHistory(DepositHistory depositHistory) {
        executorService.execute(() -> depositHistoryDAO.createDepositHistory(depositHistory));
    }
}
