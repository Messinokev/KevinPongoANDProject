package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface GoalDAO {

    @Query("SELECT * FROM goal_table")
    LiveData<List<Goal>> getAllGoals();

    @Insert
    void addGoal(Goal goal);

    @Query("SELECT * FROM goal_table WHERE id = :id")
    LiveData<Goal> getGoalById(int id);

    @Query("UPDATE goal_table SET deposit = deposit + :deposit WHERE id = :id")
    void depositMoney(int id, int deposit);

    @Query("DELETE FROM goal_table WHERE id = :id")
    void deleteGoal(int id);
}
