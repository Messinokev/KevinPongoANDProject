package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity(tableName = "category_table")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int price;
    private int deposit;
    private long numberOfDays;
    private long startDate;
    private long endDate;

    public Category(String name, int price, long startDate, long endDate) {
        this.name = name;
        this.price = price;
        deposit = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        long diff = endDate - startDate;
        numberOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    public long calculateUserAveragePerDay() {
        long daysLeft = getDaysLeft();
        if (daysLeft == 0) {
            return deposit / (numberOfDays + 1);
        } else {
            daysLeft += 1;
            if (deposit > price) {
                return deposit / (numberOfDays + 1);
            } else {
                return (price - deposit) / daysLeft;
            }
        }
    }

    public long calculateAveragePerDay() {
        long diff = endDate - startDate;
        long daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 2;
        if (daysLeft == 0) {
            return 0;
        } else {
            return price / daysLeft;
        }
    }

    public int maxDeposit() {
        return price - deposit;
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
            daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        }

        return daysLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit += deposit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
