package github.com.Messinokev.kevinpongo_and_project.ui.goals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {

    List<Goal> goals;
    OnListItemClickListener listener;

    public GoalAdapter(OnListItemClickListener listener) {
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
        holder.title.setText(goals.get(position).getTitle());
        
        int percentage = goals.get(position).calculatePercentage();


        holder.percentage.setText(percentage + "%");
        holder.progressBar.setProgress(percentage);

        String background = goals.get(position).backgroundColor();
        if (background.equals("Red")){
            holder.percentage.setBackgroundResource(R.drawable.rounded_rightcorner_view_red);
            holder.title.setBackgroundResource(R.drawable.rounded_leftcorner_view_red);
        }
        if (background.equals("Green")){
            holder.percentage.setBackgroundResource(R.drawable.rounded_rightcorner_view_green);
            holder.title.setBackgroundResource(R.drawable.rounded_leftcorner_view_green);
        }
        if (background.equals("Orange")){
            holder.percentage.setBackgroundResource(R.drawable.rounded_rightcorner_view_orange);
            holder.title.setBackgroundResource(R.drawable.rounded_leftcorner_view_orange);
        }
    }

    @Override
    public int getItemCount() {
        if(goals == null){
            return 0;
        }else{
            return goals.size();
        }
    }

    /**
     *
     * @param goals list, this method is used in the fragment so when the goal list changes the recycle view observes it and changes as well
     */
    public void updateData(List<Goal> goals) {
        this.goals = goals;
        notifyDataSetChanged();
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

    /**
     * The recycle view items are clickable so an OnClickListener is set
     */
    public interface OnListItemClickListener {
        void onClick(int position);
    }
}
