package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import github.com.Messinokev.kevinpongo_and_project.R;

public class DepositHistoryFragment extends Fragment {

    private DepositHistoryViewModel depositHistoryViewModel;
    private RecyclerView recyclerView;
    private DepositHistoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        depositHistoryViewModel =
                new ViewModelProvider(this).get(DepositHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = root.findViewById(R.id.historyRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new DepositHistoryAdapter();
        recyclerView.setAdapter(adapter);

        depositHistoryViewModel.getAllDepositHistory().observe(getViewLifecycleOwner(), adapter::updateData);

        return root;
    }
}