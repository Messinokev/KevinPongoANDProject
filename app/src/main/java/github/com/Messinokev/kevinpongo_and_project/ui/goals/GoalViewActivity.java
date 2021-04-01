package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import github.com.Messinokev.kevinpongo_and_project.R;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.Goal;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalsViewModel;

public class GoalViewActivity extends AppCompatActivity {

    TextView title;
    TextView price;
    TextView description;
    TextView startDate;
    TextView endDate;
    TextView daysLeft;
    TextView savePerDay;
    TextView savePerWeek;
    TextView savePerMonth;
    TextView savePerYear;
    TextView progress;
    TextView inDeposit;
    TextView depositLeft;
    TextView errorMessage;
    EditText deposit;

    Goal goal;

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

        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        goal = goalsViewModel.getAllGoals().getValue().get(position);

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

        refreshCalculations();
        calculateAverages();
    }

    public void depositMoney(View view) {
        if (!deposit.getText().toString().equals("")) {
            if (Integer.parseInt(deposit.getText().toString()) > goal.maxDeposit()) {
                errorMessage.setTextColor(Color.parseColor("#FF0000"));
                errorMessage.setText("Max you can deposit: " + goal.maxDeposit());
            } else {
                goal.addDeposit(Integer.parseInt(deposit.getText().toString()));
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
        savePerDay.setText(String.valueOf(goal.calculateAveragePerDay()));
        savePerWeek.setText(String.valueOf(goal.calculateAveragePerWeek()));
        savePerMonth.setText(String.valueOf(goal.calculateAveragePerMonth()));
        savePerYear.setText(String.valueOf(goal.calculateAveragePerYear()));
    }

    private void refreshCalculations(){
        progress.setText(goal.calculatePercentage() + "%");
        inDeposit.setText(String.valueOf(goal.getDeposit()));
        depositLeft.setText(String.valueOf(goal.maxDeposit()));
    }
}