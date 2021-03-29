package github.com.Messinokev.kevinpongo_and_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import github.com.Messinokev.kevinpongo_and_project.ui.goals.Goal;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalsViewModel;

public class CreateGoalActivity extends AppCompatActivity {

    CalendarView startDateCalendarView;
    long startDate;
    CalendarView endDateCalendarView;
    long endDate;
    TextView startDateText;
    TextView endDateText;

    EditText editTitle;
    EditText editPrice;
    EditText editDescription;

    GoalsViewModel viewModel;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(GoalsViewModel.class);

        startDateCalendarView = findViewById(R.id.startDateCalendarView);
        endDateCalendarView = findViewById(R.id.endDateCalendarView);
        startDateText = findViewById(R.id.startDateTextView);
        endDateText = findViewById(R.id.endDateTextView);

        editTitle = findViewById(R.id.editTitle);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);

        endDateCalendarView.setVisibility(View.INVISIBLE);

        startDateText.setOnClickListener(v -> {
            startDateCalendarView.setVisibility(View.VISIBLE);
            endDateCalendarView.setVisibility(View.INVISIBLE);
        });

        endDateText.setOnClickListener(v -> {
            startDateCalendarView.setVisibility(View.INVISIBLE);
            endDateCalendarView.setVisibility(View.VISIBLE);
        });

        startDateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                startDateCalendarView.setDate(calendar.getTimeInMillis());
            }
        });

        endDateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                endDateCalendarView.setDate(calendar.getTimeInMillis());
            }
        });
    }

    public void addGoal(View view) {
        startDate = startDateCalendarView.getDate();
        endDate = endDateCalendarView.getDate();
        Goal newGoal = new Goal(editTitle.getText().toString(), Integer.parseInt(editPrice.getText().toString()), editDescription.getText().toString(), new Date(startDate) , new Date(endDate));
        viewModel.addGoal(newGoal);
    }
}