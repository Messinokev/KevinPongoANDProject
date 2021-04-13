package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class GoalViewActivity extends AppCompatActivity{

    private TextView title;
    private TextView price;
    private TextView description;
    private TextView startDate;
    private TextView endDate;
    private TextView daysLeft;
    private TextView savePerDay;
    private TextView savePerWeek;
    private TextView savePerMonth;
    private TextView savePerYear;
    private TextView progress;
    private  TextView inDeposit;
    private  TextView depositLeft;
    private TextView errorMessage;
    private EditText deposit;

    private GoalsViewModel goalsViewModel;

    private Goal goalGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_view);

        title = findViewById(R.id.goalViewTitle);
        price = findViewById(R.id.goalViewPrice);
        description = findViewById(R.id.goalViewDescription);
        startDate = findViewById(R.id.goalViewStartDate);
        endDate = findViewById(R.id.goalViewEndDate);
        daysLeft = findViewById(R.id.goalViewDaysLeft);
        savePerDay = findViewById(R.id.goalViewSavePerDay);
        savePerWeek = findViewById(R.id.goalViewSavePerWeek);
        savePerMonth = findViewById(R.id.goalViewSavePerMonth);
        savePerYear = findViewById(R.id.goalViewSavePerYear);
        progress = findViewById(R.id.goalViewProgress);
        inDeposit = findViewById(R.id.goalViewInDeposit);
        depositLeft = findViewById(R.id.goalViewDepositLeft);
        errorMessage = findViewById(R.id.goalViewErrorMessage);
        deposit = findViewById(R.id.goalViewAddMoney);

        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        goalGoal = goalsViewModel.getGoalById(position).getValue();

        this.setTitle(goalGoal.getTitle());

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String startDateString = simpleDateFormat.format(goalGoal.getStartDate());
        String endDateString = simpleDateFormat.format(goalGoal.getEndDate());

        title.setText(goalGoal.getTitle());
        price.setText(String.valueOf(goalGoal.getPrice()));
        description.setText(goalGoal.getDescription());
        startDate.setText(startDateString);
        endDate.setText(endDateString);
        daysLeft.setText(String.valueOf(goalGoal.getDaysLeft()));

        refreshCalculations();
        calculateAverages();
    }

    private void updateData(List<Goal> goals) {

    }


    public void depositMoney(View view) {
        if (!deposit.getText().toString().equals("")) {
            if (Integer.parseInt(deposit.getText().toString()) > goalGoal.maxDeposit()) {
                errorMessage.setTextColor(Color.parseColor("#FF0000"));
                errorMessage.setText("Max you can deposit: " + goalGoal.maxDeposit());
            } else {
                goalGoal.addDeposit(Integer.parseInt(deposit.getText().toString()));
                refreshCalculations();
                calculateAverages();
                deposit.setText("");
                errorMessage.setTextColor(Color.parseColor("#018786"));
                errorMessage.setText("Successfully deposited money!");
            }
        } else {
            errorMessage.setTextColor(Color.parseColor("#FF0000"));
            errorMessage.setText("Fill in Deposit Money field!");
        }

    }

    private void calculateAverages(){
        savePerDay.setText(String.valueOf(goalGoal.calculateAveragePerDay()));
        savePerWeek.setText(String.valueOf(goalGoal.calculateAveragePerWeek()));
        savePerMonth.setText(String.valueOf(goalGoal.calculateAveragePerMonth()));
        savePerYear.setText(String.valueOf(goalGoal.calculateAveragePerYear()));
    }

    private void refreshCalculations(){
        progress.setText(goalGoal.calculatePercentage() + "%");
        inDeposit.setText(String.valueOf(goalGoal.getDeposit()));
        depositLeft.setText(String.valueOf(goalGoal.maxDeposit()));
    }
}