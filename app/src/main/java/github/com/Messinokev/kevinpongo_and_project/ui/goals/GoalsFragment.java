package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.CreateGoalActivity;
import github.com.Messinokev.kevinpongo_and_project.R;

public class GoalsFragment extends Fragment implements GoalAdapter.OnListItemClickListener {

    private GoalsViewModel goalsViewModel;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goals, container, false);

        recyclerView = root.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        GoalAdapter adapter = new GoalAdapter(goalsViewModel.getAllGoals(),this);
        recyclerView.setAdapter(adapter);

        /*final TextView textView = root.findViewById(R.id.text_home);
        goalsViewModel.getAllGoals().observe(getViewLifecycleOwner(), new Observer<List<Goal>>() {
            @Override
            public void onChanged(@Nullable List<Goal> goals) {
                if (!goals.isEmpty()) {
                    textView.setText("");
                    for (Goal g : goals) {
                        textView.append(g.getTitle() + "\n");
                    }
                } else {
                    textView.setText("Empty");
                }
            }
        });*/

        FloatingActionButton floatingActionButton = root.findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onClick(int position) {
        /*Toast.makeText(this, "Title: " + goalsViewModel.getAllGoals().getValue().get(position).getTitle(), Toast.LENGTH_SHORT).show();*/
    }
}