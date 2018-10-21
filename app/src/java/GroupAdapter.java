package com.example.demoprj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Home on 04-Aug-18.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Person> data = new ArrayList<>();
    private Context context;

    /**
     * @param context
     */
    public GroupAdapter(Context context) {
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
        holder.tvCity.setText(data.get(position).getCity());
        holder.tvAge.setText(data.get(position).getAge() + "");

    }

    public void sortAsc(final String strFilter) {

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person object1, Person object2) {
                String city = object1.getCity();
                String city1 = object2.getCity();
                if (strFilter.equals("City")) {
                    city = object1.getCity();
                    city1 = object2.getCity();
                } else if (strFilter.equals("Name")) {
                    city = object1.getName();
                    city1 = object2.getName();
                } else if (strFilter.equals("Age")) {
                    city = String.valueOf(object1.getAge());
                    city1 = String.valueOf(object2.getAge());
                }
                return ((String) city).compareTo(city1);
            }
        };
        Collections.sort(data, comparator);
        notifyDataSetChanged();
    }

    public void sortDesc(final String strFilter) {

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person object1, Person object2) {
                String city = object1.getCity();
                String city1 = object2.getCity();
                if (strFilter.equals("City")) {
                    city = object1.getCity();
                    city1 = object2.getCity();
                } else if (strFilter.equals("Name")) {
                    city = object1.getName();
                    city1 = object2.getName();
                } else if (strFilter.equals("Age")) {
                    city = String.valueOf(object1.getAge());
                    city1 = String.valueOf(object2.getAge());
                }
                return ((String) city1).compareTo(city);
            }
        };
        Collections.sort(data, comparator);
        notifyDataSetChanged();
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
        data.clear();
        data.addAll(lines);
        notifyDataSetChanged();
    }

    public void add(Person p) {
        data.add(p);
    }

    public List<Person> getAll() {
        return data;
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
