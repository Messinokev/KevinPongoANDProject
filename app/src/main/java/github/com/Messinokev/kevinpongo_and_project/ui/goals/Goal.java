package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity(tableName = "goal_table")
public class Goal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Title;
    private int price;
    private int deposit;
    private String description;
    private long numberOfDays;
    private long startDate;
    private long endDate;

    public Goal(){
    }

    public Goal(String title, int price, String description, long startDate, long endDate) {
        Title = title;
        this.price = price;
        this.description = description;
        this.startDate =  startDate;
        this.endDate = endDate;
        long diff = endDate - startDate;
        numberOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        deposit = 0;
    }

    public int calculatePercentage() {
        double dDeposit = deposit;
        double dPrice = price;
        int percentage = (int) Math.round(((dDeposit / dPrice) * 100));

        if (percentage > 100) {
            percentage = 100;
        }
        return percentage;
    }

    public long calculateAveragePerDay() {
        long daysLeft = getDaysLeft();
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    public long calculateAveragePerWeek() {
        long daysLeft = getDaysLeft() / 7;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    public long calculateAveragePerMonth() {
        long daysLeft = getDaysLeft() / 30;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    public long calculateAveragePerYear() {
        long daysLeft = getDaysLeft() / 365;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    public long getDaysLeft() {
        long startDay = startDate;
        long endDay = endDate;


        Date today = new Date();
        long daysLeft;
        long diff;

        if (today.getTime() > endDay) {
            daysLeft = 0;
        } else {
            if (startDay > today.getTime()) {
                diff = endDate - startDate;
            } else {
                diff = endDate - today.getTime();
            }
            daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) +1 ;
        }

        return daysLeft;
    }

    public void addDeposit(int deposit) {
        this.deposit += deposit;
        calculatePercentage();
    }

    public String backgroundColor() {
        long diff = endDate - startDate;
        long totalDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        long daysLeft = getDaysLeft();

        double calculate = (double) (totalDays-daysLeft) / totalDays;

        if (daysLeft != 0) {
            if (calculate < 0.75) {
                return "Green";
            }

            if (calculate < 0.9) {
                return "Orange";
            }
        }
        return "Red";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int maxDeposit() {
        return price - deposit;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", price=" + price +
                '}';
    }
}
