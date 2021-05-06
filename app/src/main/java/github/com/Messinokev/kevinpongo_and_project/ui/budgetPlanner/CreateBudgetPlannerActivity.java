package github.com.Messinokev.kevinpongo_and_project.ui.budgetPlanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    private TextInputEditText title;
    private TextInputLayout titleLayout;
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

    private BudgetPlannerViewModel viewModel;
    private List<Category> categories;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_planner);

        this.setTitle(R.string.create_budget_plannerName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(BudgetPlannerViewModel.class);

        title = findViewById(R.id.createBudgetTitle);
        titleLayout = findViewById(R.id.createBudgetTitleLayout);
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

        preferences = getSharedPreferences("prefs",MODE_PRIVATE);

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

        titleError();

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

        categories = new ArrayList<>();

        if (startDate >= endDate) {
            errorMessage.setTextColor(getResources().getColor(R.color.error));
            errorMessage.setText(getResources().getString(R.string.dateErrorMessage));
        } else {
            errorMessage.setText("");

            if (!title.getText().toString().equals("")) {

                categoryCheck();

                if (categories.size() == 0) {
                    errorMessage.setTextColor(getResources().getColor(R.color.error));
                    errorMessage.setText(getResources().getString(R.string.categoryErrorMessage));
                } else {
                    MutableLiveData<List<Category>> categoriesLive = new MutableLiveData<>();
                    categoriesLive.setValue(categories);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title", title.getText().toString());
                    editor.apply();

                    for (int i = 0; i < categories.size(); i++) {
                        viewModel.createCategory(categories.get(i));
                    }

                    setFieldsToEmpty();

                    errorMessage.setTextColor(getResources().getColor(R.color.success));
                    errorMessage.setText(getResources().getString(R.string.budgetPlannerSuccessMessage));
                }
            }
        }
    }

    public void categoryCheck(){
        if (foodCheck.isChecked() && !foodField.getText().toString().equals("")) {
            Category food = new Category(getResources().getString(R.string.foodText), Integer.parseInt(foodField.getText().toString()), startDate, endDate);
            categories.add(food);
        }
        if (rentCheck.isChecked() && !rentField.getText().toString().equals("")) {
            Category rent = new Category(getResources().getString(R.string.rentText), Integer.parseInt(rentField.getText().toString()), startDate, endDate);
            categories.add(rent);
        }
        if (hobbyCheck.isChecked() && !hobbyField.getText().toString().equals("")) {
            Category hobby = new Category(getResources().getString(R.string.hobbyText), Integer.parseInt(hobbyField.getText().toString()), startDate, endDate);
            categories.add(hobby);
        }
        if (socialCheck.isChecked() && !socialField.getText().toString().equals("")) {
            Category social = new Category(getResources().getString(R.string.socialText), Integer.parseInt(socialField.getText().toString()), startDate, endDate);
            categories.add(social);
        }
        if (travelCheck.isChecked() && !travelField.getText().toString().equals("")) {
            Category travel = new Category(getResources().getString(R.string.travelText), Integer.parseInt(travelField.getText().toString()), startDate, endDate);
            categories.add(travel);
        }
        if (otherCheck.isChecked() && !otherField.getText().toString().equals("")) {
            Category other = new Category(getResources().getString(R.string.otherText), Integer.parseInt(otherField.getText().toString()), startDate, endDate);
            categories.add(other);
        }
    }

    public void setFieldsToEmpty(){
        title.setText(getResources().getString(R.string.successFullyCreatedText));
        foodCheck.setChecked(false);
        foodField.setText("");
        rentCheck.setChecked(false);
        rentField.setText("");
        travelCheck.setChecked(false);
        travelField.setText("");
        socialCheck.setChecked(false);
        socialField.setText("");
        otherCheck.setChecked(false);
        otherField.setText("");
        hobbyCheck.setChecked(false);
        hobbyField.setText("");
    }

    public void titleError() {
        title.addTextChangedListener(new TextWatcher() {
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
}