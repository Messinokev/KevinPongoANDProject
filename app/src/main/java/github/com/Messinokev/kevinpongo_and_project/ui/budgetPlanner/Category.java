package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Category {

    String name;
    int price;
    int deposit;
    private long numberOfDays;
    private Date startDate;
    private Date endDate;

    public Category(String name, int price, Date startDate, Date endDate) {
        this.name = name;
        this.price = price;
        deposit = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        long diff = endDate.getTime() - startDate.getTime();
        numberOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public long calculateUserAveragePerDay() {
        Date today = new Date();
        long diff = today.getTime() - startDate.getTime();
        long daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        if (daysLeft == 0) {
            return 0;
        } else {
            return (price-deposit)/daysLeft;
        }
    }

    public long calculateAveragePerDay() {
        long diff = endDate.getTime() - startDate.getTime();
        long daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
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
        long startDay = startDate.getTime();
        long endDay = endDate.getTime();

        Date today = new Date();
        long daysLeft;
        long diff;

        if (today.getTime() > endDay) {
            daysLeft = 0;
        } else {
            if (startDay > today.getTime()) {
                diff = endDate.getTime() - startDate.getTime();
            } else {
                diff = endDate.getTime() - today.getTime();
            }
            daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        }

        return daysLeft;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
