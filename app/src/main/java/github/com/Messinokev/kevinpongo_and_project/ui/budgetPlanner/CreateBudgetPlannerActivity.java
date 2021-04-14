package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class CreateBudgetPlannerActivity extends AppCompatActivity {

    private CalendarView startDateCalendarView;
    private long startDate;
    private CalendarView endDateCalendarView;
    private long endDate;
    private TextView startDateText;
    private TextView endDateText;
    private Calendar calendar = Calendar.getInstance();

    private EditText title;
    private TextView errorMessage;

    private EditText foodField;
    private EditText rentField;
    private EditText hobbyField;
    private EditText socialField;
    private EditText travelField;
    private EditText otherField;

    private CheckBox foodCheck;
    private CheckBox rentCheck;
    private CheckBox hobbyCheck;
    private CheckBox socialCheck;
    private CheckBox travelCheck;
    private CheckBox otherCheck;

    BudgetPlannerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_planner);

        this.setTitle("Create new Budget Planner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(BudgetPlannerViewModel.class);

        title = findViewById(R.id.createBudgetTitle);
        errorMessage = findViewById(R.id.createBudgetErrorMessage);

        foodField = findViewById(R.id.createBudgetFood);
        rentField = findViewById(R.id.createBudgetRent);
        hobbyField = findViewById(R.id.createBudgetHobby);
        socialField = findViewById(R.id.createBudgetSocial);
        travelField = findViewById(R.id.createBudgetTravel);
        otherField = findViewById(R.id.createBudgetOther);
        foodCheck = findViewById(R.id.createBudgetFoodCheck);
        rentCheck = findViewById(R.id.createBudgetRentCheck);
        hobbyCheck = findViewById(R.id.createBudgetHobbyCheck);
        socialCheck = findViewById(R.id.createBudgetFriendsCheck);
        travelCheck = findViewById(R.id.createBudgetTravelCheck);
        otherCheck = findViewById(R.id.createBudgetOtherCheck);

        startDateCalendarView = findViewById(R.id.createBudgetStartDate);
        endDateCalendarView = findViewById(R.id.createBudgetEndDate);
        startDateText = findViewById(R.id.createBudgetStartDateTextView);
        endDateText = findViewById(R.id.createBudgetEndDateTextView);

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

    public void createBudgetPlanner(View view) {
        startDate = startDateCalendarView.getDate();
        //end date 1sec earlier to be sure that the user do not choose the same date
        endDate = endDateCalendarView.getDate() - 1000;

        List<Category> categories = new ArrayList<>();

        if (!title.getText().toString().equals("")) {

            if (title.getText().toString().length() > 30) {
                errorMessage.setTextColor(Color.parseColor("#FF0000"));
                errorMessage.setText("Title must NOT be longer than 30 character!");
            } else {
                if (startDate >= endDate) {
                    errorMessage.setTextColor(Color.parseColor("#FF0000"));
                    errorMessage.setText("End Date must be later than Start Date!");
                } else {

                    if (foodCheck.isChecked() && !foodField.getText().toString().equals("")) {
                        Category food = new Category("Food", Integer.parseInt(foodField.getText().toString()), startDate, endDate);
                        categories.add(food);
                    }
                    if (rentCheck.isChecked() && !rentField.getText().toString().equals("")) {
                        Category rent = new Category("Rent", Integer.parseInt(rentField.getText().toString()), startDate, endDate);
                        categories.add(rent);
                    }
                    if (hobbyCheck.isChecked() && !hobbyField.getText().toString().equals("")) {
                        Category hobby = new Category("Hobby", Integer.parseInt(hobbyField.getText().toString()), startDate, endDate);
                        categories.add(hobby);
                    }
                    if (socialCheck.isChecked() && !socialField.getText().toString().equals("")) {
                        Category social = new Category("Social", Integer.parseInt(socialField.getText().toString()), startDate, endDate);
                        categories.add(social);
                    }
                    if (travelCheck.isChecked() && !travelField.getText().toString().equals("")) {
                        Category travel = new Category("Travel", Integer.parseInt(travelField.getText().toString()),startDate, endDate);
                        categories.add(travel);
                    }
                    if (otherCheck.isChecked() && !otherField.getText().toString().equals("")) {
                        Category other = new Category("Other", Integer.parseInt(otherField.getText().toString()), startDate, endDate);
                        categories.add(other);
                    }

                    if (categories.size() == 0){
                        errorMessage.setTextColor(Color.parseColor("#FF0000"));
                        errorMessage.setText("You must choose at least one category!");
                    }
                    else {
                        MutableLiveData<List<Category>> categoriesLive = new MutableLiveData<>();
                        categoriesLive.setValue(categories);
                        //BudgetPlanner newBudgetPlanner = new BudgetPlanner(title.getText().toString(), categoriesLive);
                        for (int i = 0; i < categories.size(); i++) {
                            viewModel.createCategory(categories.get(i));
                        }


                        title.setText("");
                        errorMessage.setTextColor(Color.parseColor("#018786"));
                        errorMessage.setText("Budget Planner successfully created!");
                    }
                }
            }
        } else {
            errorMessage.setTextColor(Color.parseColor("#FF0000"));
            errorMessage.setText("Fill in Title field!");
        }
    }
}