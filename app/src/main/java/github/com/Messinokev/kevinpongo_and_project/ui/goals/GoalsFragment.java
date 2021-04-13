package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        GoalAdapter adapter = new GoalAdapter(this);
        recyclerView.setAdapter(adapter);

        goalsViewModel.getAllGoals().observe(getViewLifecycleOwner(), goals -> adapter.updateData(goals));

        FloatingActionButton floatingActionButton = root.findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onClick(int position) {

        Intent intent = new Intent(getActivity(), GoalViewActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}