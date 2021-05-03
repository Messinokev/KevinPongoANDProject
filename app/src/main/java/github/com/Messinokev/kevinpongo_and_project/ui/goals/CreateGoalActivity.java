package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import github.com.Messinokev.kevinpongo_and_project.R;

public class CreateGoalActivity extends AppCompatActivity {

    private CalendarView startDateCalendarView;
    private long startDate;
    private CalendarView endDateCalendarView;
    private long endDate;
    private TextView startDateText;
    private TextView endDateText;

    private TextView errorMessage;

    private TextInputEditText editTitle;
    private TextInputLayout titleLayout;

    private TextInputEditText editPrice;
    private TextInputLayout priceLayout;

    private EditText editDescription;

    private GoalsViewModel goalsViewModel;

    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        this.setTitle(getResources().getString(R.string.createNewGoalText));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        goalsViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);

        startDateCalendarView = findViewById(R.id.startDateCalendarView);
        endDateCalendarView = findViewById(R.id.endDateCalendarView);
        startDateText = findViewById(R.id.startDateTextView);
        endDateText = findViewById(R.id.endDateTextView);
        errorMessage = findViewById(R.id.errorMessage);
        editTitle = findViewById(R.id.createGoalTitle);
        titleLayout = findViewById(R.id.createGoalTitleLayout);
        editPrice = findViewById(R.id.createGoalPrice);
        priceLayout = findViewById(R.id.createGoalPriceLayout);
        editDescription = findViewById(R.id.createGoalDescription);

        titleError();
        priceError();

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


        if (startDate >= endDate) {
            errorMessage.setTextColor(Color.parseColor("#FF0000"));
            errorMessage.setText(getResources().getString(R.string.dateErrorMessage));
        } else {
            errorMessage.setText("");
            if (!editTitle.getText().toString().equals("") && !editPrice.getText().toString().equals("")) {

                if (editDescription.getText().toString().equals("")) {
                    editDescription.setText("");
                }
                Goal newGoal = new Goal(editTitle.getText().toString(), Integer.parseInt(editPrice.getText().toString()), editDescription.getText().toString(), startDate, endDate);
                goalsViewModel.addGoal(newGoal);
                editTitle.setText(" ");
                editPrice.setText(" ");
                editDescription.setText("");

                errorMessage.setTextColor(Color.parseColor("#018786"));
                errorMessage.setText(getResources().getString(R.string.goalSuccessMessage));
            }
        }
    }

    public void titleError() {
        editTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0) {
                    titleLayout.setError(null);
                } else {
                    titleLayout.setError("Required!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void priceError() {
        editPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0) {
                    priceLayout.setError(null);
                } else {
                    priceLayout.setError("Required!");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


}