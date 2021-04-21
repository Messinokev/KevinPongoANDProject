package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BudgetPlannerDAO {

    @Query("SELECT * FROM category_table")
     LiveData<List<Category>> getAllCategories();

    @Insert
     void createCategory(Category category);

    @Query("UPDATE category_table SET deposit = deposit + :deposit WHERE id = :id")
    void depositMoney(int id, int deposit);

}
