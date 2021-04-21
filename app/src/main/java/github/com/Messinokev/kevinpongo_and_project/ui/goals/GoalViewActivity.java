package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button depositButton;

    private GoalsViewModel goalsViewModel;

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
        depositButton = findViewById(R.id.depositMoneyButton);

        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

         goalsViewModel.getGoalById(id).observe(this, goal -> {

            this.setTitle(goal.getTitle());

             String pattern = "dd/MM/yyyy";
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
             String startDateString = simpleDateFormat.format(goal.getStartDate());
             String endDateString = simpleDateFormat.format(goal.getEndDate());

             title.setText(goal.getTitle());
             price.setText(String.valueOf(goal.getPrice()));
             description.setText(goal.getDescription());
             startDate.setText(startDateString);
             endDate.setText(endDateString);
             daysLeft.setText(String.valueOf(goal.getDaysLeft()));

             refreshCalculations(goal);
             calculateAverages(goal);

                depositButton.setOnClickListener(v -> {
                    if (!deposit.getText().toString().equals("")) {
                        if (Integer.parseInt(deposit.getText().toString()) > goal.maxDeposit()) {
                            errorMessage.setTextColor(Color.parseColor("#FF0000"));
                            errorMessage.setText("Max you can deposit: " + goal.maxDeposit());
                        } else {
                            goalsViewModel.depositMoney(goal.getId(), Integer.parseInt(deposit.getText().toString()));
                            refreshCalculations(goal);
                            calculateAverages(goal);
                            deposit.setText("");
                            errorMessage.setTextColor(Color.parseColor("#018786"));
                            errorMessage.setText("Successfully deposited money!");
                        }
                    } else {
                        errorMessage.setTextColor(Color.parseColor("#FF0000"));
                        errorMessage.setText("Fill in Deposit Money field!");
                    }
                });
        });
    }

    private void calculateAverages(Goal goal){
        savePerDay.setText(String.valueOf(goal.calculateAveragePerDay()));
        savePerWeek.setText(String.valueOf(goal.calculateAveragePerWeek()));
        savePerMonth.setText(String.valueOf(goal.calculateAveragePerMonth()));
        savePerYear.setText(String.valueOf(goal.calculateAveragePerYear()));
    }

    private void refreshCalculations(Goal goal){
        progress.setText(goal.calculatePercentage() + "%");
        inDeposit.setText(String.valueOf(goal.getDeposit()));
        depositLeft.setText(String.valueOf(goal.maxDeposit()));
    }

}