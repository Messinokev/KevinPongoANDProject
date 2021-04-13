package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import github.com.Messinokev.kevinpongo_and_project.MainActivity;
import github.com.Messinokev.kevinpongo_and_project.R;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.Goal;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalsFragment;
import github.com.Messinokev.kevinpongo_and_project.ui.goals.GoalsViewModel;

public class CreateGoalActivity extends AppCompatActivity {

    private CalendarView startDateCalendarView;
    private long startDate;
    private CalendarView endDateCalendarView;
    private long endDate;
    private TextView startDateText;
    private TextView endDateText;

    private TextView errorMessage;

    private EditText editTitle;
    private EditText editPrice;
    private EditText editDescription;

    private GoalsViewModel viewModel;

    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        this.setTitle("Create new Goal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = new ViewModelProvider(this).get(GoalsViewModel.class);

        startDateCalendarView = findViewById(R.id.startDateCalendarView);
        endDateCalendarView = findViewById(R.id.endDateCalendarView);
        startDateText = findViewById(R.id.startDateTextView);
        endDateText = findViewById(R.id.endDateTextView);
        errorMessage = findViewById(R.id.errorMessage);
        editTitle = findViewById(R.id.editTitle);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);

        endDateCalendarView.setVisibility(View.INVISIBLE);
        endDateText.setTypeface(null, Typeface.NORMAL);

        startDateText.setOnClickListener(v -> {
            startDateCalendarView.setVisibility(View.VISIBLE);
            endDateCalendarView.setVisibility(View.INVISIBLE);
            startDateText.setTypeface(null, Typeface.BOLD);
            endDateText.setTypeface(null, Typeface.NORMAL);
        });

        endDateText.setOnClickListener(v -> {
            startDateCalendarView.setVisibility(View.INVISIBLE);
            endDateCalendarView.setVisibility(View.VISIBLE);
            endDateText.setTypeface(null, Typeface.BOLD);
            startDateText.setTypeface(null, Typeface.NORMAL);
        });

        startDateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                startDateCalendarView.setDate(calendar.getTimeInMillis());
            }
        });

        endDateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                endDateCalendarView.setDate(calendar.getTimeInMillis());
            }
        });
    }

    public void addGoal(View view) {
        startDate = startDateCalendarView.getDate();
        //end date 1sec earlier to be sure that the user do not choose the same date
        endDate = endDateCalendarView.getDate() - 1000;

        if (!editTitle.getText().toString().equals("") && !editPrice.getText().toString().equals("")) {
            if(editTitle.getText().toString().length() > 30){
                errorMessage.setTextColor(Color.parseColor("#FF0000"));
                errorMessage.setText("Title must NOT be longer than 30 character!");
            }else{
                if (startDate >= endDate) {
                    errorMessage.setTextColor(Color.parseColor("#FF0000"));
                    errorMessage.setText("End Date must be later than Start Date!");
                } else {
                    if (editDescription.getText().toString().equals("")) {
                        editDescription.setText("");
                    }

                    Goal newGoal = new Goal(editTitle.getText().toString(), Integer.parseInt(editPrice.getText().toString()), editDescription.getText().toString(), startDate, endDate);
                    viewModel.addGoal(newGoal);
                    editTitle.setText("");
                    editPrice.setText("");
                    editDescription.setText("");

                    errorMessage.setTextColor(Color.parseColor("#018786"));
                    errorMessage.setText("Goal successfully created!");
                }
            }
        } else {
            errorMessage.setTextColor(Color.parseColor("#FF0000"));
            errorMessage.setText("Fill in Title and Price fields!");
        }
    }
}