package github.com.Messinokev.kevinpongo_and_project.ui.depositHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;

import github.com.Messinokev.kevinpongo_and_project.R;

public class DepositHistoryAdapter extends RecyclerView.Adapter<DepositHistoryAdapter.ViewHolder> {

    List<DepositHistory> depositHistories;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_list_item, parent, false);
        return new DepositHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(depositHistories.get(position).getDate());

        holder.title.setText(depositHistories.get(position).getTitle());
        holder.date.setText(date);
        holder.deposit.setText(String.valueOf(depositHistories.get(position).getDeposit()));
    }

    @Override
    public int getItemCount() {
        if (depositHistories == null){
            return 0;
        }
        else {
            return depositHistories.size();
        }
    }

    public void updateData(List<DepositHistory> depositHistories) {
        this.depositHistories = depositHistories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView deposit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.historyListTitle);
            date = itemView.findViewById(R.id.historyListDate);
            deposit = itemView.findViewById(R.id.historyListDeposit);

        }
    }
}
