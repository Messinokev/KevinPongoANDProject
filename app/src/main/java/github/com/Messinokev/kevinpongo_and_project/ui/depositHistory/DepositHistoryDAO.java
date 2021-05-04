package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DepositHistoryDAO {

    @Query("SELECT * FROM depositHistory_table ORDER BY id DESC")
    LiveData<List<DepositHistory>> getAllDepositHistory();

    @Insert
    void createDepositHistory(DepositHistory depositHistory);
}
