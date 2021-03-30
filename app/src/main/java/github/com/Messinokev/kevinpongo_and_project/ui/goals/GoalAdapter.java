package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {

    LiveData<List<Goal>> goals;
    OnListItemClickListener listener;

    public GoalAdapter(LiveData<List<Goal>> goals, OnListItemClickListener listener) {
        this.goals = goals;
        this.listener = listener;
    }


    @NonNull
    @Override
    public GoalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.goal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAdapter.ViewHolder holder, int position) {
        holder.title.setText(goals.getValue().get(position).getTitle());
        
        int percentage = goals.getValue().get(position).calculatePercentage();

        holder.percentage.setText(percentage + "%");
        holder.progressBar.setProgress(percentage);
    }

    @Override
    public int getItemCount() {
        return goals.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView percentage;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            title = itemView.findViewById(R.id.title);
            progressBar = itemView.findViewById(R.id.progressBar);
            percentage = itemView.findViewById(R.id.percentage);
        }
    }

    public interface OnListItemClickListener {
        void onClick(int position);
    }
}
