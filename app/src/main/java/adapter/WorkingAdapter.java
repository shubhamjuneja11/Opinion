package adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supergeek.opinion.R;

import java.util.ArrayList;

import model.DataModel;

/**
 * Created by Junejas on 4/15/2017.
 */

public class WorkingAdapter extends RecyclerView.Adapter<WorkingAdapter.MyViewHolder> {
ArrayList<DataModel> dataModels;
    public WorkingAdapter(ArrayList<DataModel> dataModels){
        this.dataModels=dataModels;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data;
        public MyViewHolder(View itemView) {
            super(itemView);
            data=(TextView)itemView.findViewById(R.id.data);
            Log.e("papamycount","q");
        }
    }
    @Override
    public WorkingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(WorkingAdapter.MyViewHolder holder, int position) {
    DataModel dataModel=dataModels.get(position);
        holder.data.setText(dataModel.getText());
        Log.e("postext12",position+"");
        Log.e("postext",dataModel.getText());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


}
