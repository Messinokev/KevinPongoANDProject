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

    /**
     *
     * @return an int which shows the percentage of the goal saving process
     */
    public int calculatePercentage() {
        double dDeposit = deposit;
        double dPrice = price;

        int percentage = (int) Math.round(((dDeposit / dPrice) * 100));

        return percentage;
    }

    /**
     *
     * @return a long which gives how much the user should save per day
     */
    public long calculateAveragePerDay() {
        long daysLeft = getDaysLeft();
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    /**
     *
     * @return a long which gives how much the user should save per week (if the there are enough days for week(s))
     */
    public long calculateAveragePerWeek() {
        long daysLeft = getDaysLeft() / 7;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    /**
     *
     * @return a long which gives how much the user should save per month (if the there are enough days for month(s))
     */
    public long calculateAveragePerMonth() {
        long daysLeft = getDaysLeft() / 30;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    /**
     *
     * @return a long which gives how much the user should save per year (if the there are enough days for year(s))
     */
    public long calculateAveragePerYear() {
        long daysLeft = getDaysLeft() / 365;
        if (daysLeft == 0) {
            return 0;
        } else {
            return maxDeposit() / daysLeft;
        }
    }

    /**
     *
     * @return a long which gives how many days left until the end date of the goal
     */
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

    /**
     *
     * @return a String with a color name what is used in the adapter later to set the background color of the goal
     */
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

    /**
     *
     * @return an int which is the leftover deposit to achieve the goal
     */
    public int maxDeposit() {
        return price - deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
