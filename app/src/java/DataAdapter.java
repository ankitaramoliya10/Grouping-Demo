package com.example.demoprj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Home on 04-Aug-18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Person> data = new ArrayList<>();
    private Context context;

    /**
     * @param context
     */
    public DataAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
//        data = Arrays.asList(context.getResources().getStringArray(R.array.animationArray));
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_motivation, parent, false);
        return new ViewHolder(v);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMotivation.setText(data.get(position).getName());
//        holder.tvCity.setText(data.get(position).getCity());
//        holder.tvAge.setText(data.get(position).getAge());

    }

    /**
     * remove record from given position
     *
     * @param position
     */
    public void remove(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public boolean checkSize() {
        if (data.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addAll(List<Person> lines) {
        data.addAll(lines);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvMotivation)
        TextView tvMotivation;
        @BindView(R.id.tvCity)
        TextView tvCity;
        @BindView(R.id.tvAge)
        TextView tvAge;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
