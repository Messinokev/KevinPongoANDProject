package github.com.Messinokev.kevinpongo_and_project.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import github.com.Messinokev.kevinpongo_and_project.ui.goals.Goal;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalDAO;

@Database(entities = {Goal.class}, version = 2)
public abstract class ProjectDatabase extends RoomDatabase {

    private static ProjectDatabase instance;
    public abstract GoalDAO goalDAO();

    public static synchronized ProjectDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProjectDatabase.class, "project_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
