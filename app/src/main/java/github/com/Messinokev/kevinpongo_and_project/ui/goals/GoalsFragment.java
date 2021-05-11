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
    private RecyclerView recyclerView;
    private GoalAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goals, container, false);

        recyclerView = root.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new GoalAdapter(this);
        recyclerView.setAdapter(adapter);

        goalsViewModel.getAllGoals().observe(getViewLifecycleOwner(), adapter::updateData);

        FloatingActionButton floatingActionButton = root.findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
            startActivity(intent);
        });

        return root;
    }

    /**
     * This method is responsible what should happen when a recycle view item is clicked
     * @param position to pass the clicked item id from its position, later we need this to retrieve the appropriate goal from the db
     */
    @Override
    public void onClick(int position) {

        Intent intent = new Intent(getActivity(), GoalViewActivity.class);
        int id = adapter.goals.get(position).getId();
        intent.putExtra("id", id);
        startActivity(intent);
    }
}