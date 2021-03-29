package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Goal {

    private String Title;
    private int price;
    private String description;
    private long numberOfDays;
    private Date startDate;
    private Date endDate;

    public Goal(String title, int price, String description, Date startDate, Date endDate) {
        Title = title;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        long diff = endDate.getTime() - startDate.getTime();
        numberOfDays =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
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
}
