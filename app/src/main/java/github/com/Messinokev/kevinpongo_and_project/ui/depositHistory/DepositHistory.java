package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "depositHistory_table")
public class DepositHistory {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int deposit;
    private long date;

    public DepositHistory() {
    }

    public DepositHistory(String title, int deposit, long date) {
        this.title = title;
        this.deposit = deposit;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
