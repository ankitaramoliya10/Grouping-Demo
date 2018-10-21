package com.example.demoprj;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvAnimation)
    RecyclerView rvAnimation;
    @BindView(R.id.spFilter)
    Spinner spFilter;
    @BindView(R.id.spOrderBy)
    Spinner spOrderBy;
    private Context context;
    private LinearLayoutManager layoutManager;
    private GroupAdapter linesAdapter;
    private String strFilter = "", strOrder = "";
    private String city;
    private List<Person> people = new ArrayList<>();
    private Map<String, ArrayList<Person>> personByCity = new HashMap<>();
    private String[] filterArraySpinner;
    private String[] orderArraySpinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;

//        addPersonData();
        initRecyclerView();


        filterArraySpinner = new String[]{"Filter By", "City", "Name", "Age"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filterArraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFilter.setAdapter(adapter);
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Log.i("onItemClick: ", filterArraySpinner[0]);
                    strFilter = "";
                } else if (i == 1) {
                    Log.i("onItemClick: ", filterArraySpinner[1]);
                    strFilter = filterArraySpinner[1];
                } else if (i == 2) {
                    Log.i("onItemClick: ", filterArraySpinner[2]);
                    strFilter = filterArraySpinner[2];

                } else if (i == 3) {
                    Log.i("onItemClick: ", filterArraySpinner[3]);
                    strFilter = filterArraySpinner[3];

                } else {
                    Log.i("onItemClick: ", filterArraySpinner[0]);
                    strFilter = "";

                }
                linesAdapter.clear();
                refreshData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        orderArraySpinner = new String[]{"Order By", "Asc", "Desc"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orderArraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spOrderBy.setAdapter(adapter);
        spOrderBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Log.i("onItemClick: ", orderArraySpinner[0]);
                    strOrder = "";
                } else if (i == 1) {
                    Log.i("onItemClick: ", orderArraySpinner[1]);
                    strOrder = orderArraySpinner[1];
                } else if (i == 2) {
                    Log.i("onItemClick: ", orderArraySpinner[2]);
                    strOrder = orderArraySpinner[2];
                } else {
                    Log.i("onItemClick: ", orderArraySpinner[0]);
                    strOrder = "";
                }
                linesAdapter.clear();
                refreshData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        List<Person> sortList = linesAdapter.getAll();
//        Collections.sort(sortList);
//
//        for (Person toSort : sortList) {
//            Log.i("onCreate: ", toSort.toString());
//        }


//        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        rvAnimation.setLayoutManager(layoutManager);
//        DataAdapter linesAdapter = new DataAdapter(context);
//        rvAnimation.setAdapter(linesAdapter);
//        linesAdapter.addAll(people);

    }

    private void refreshData() {
        if (strFilter.equalsIgnoreCase("") && strOrder.equalsIgnoreCase("")) {
            addPersonData();
            linesAdapter.addAll(people);
        } else {
            addPersonData();
            for (Person p : people) {
                if (strFilter.equals("City")) {
                    city = p.getCity();
                } else if (strFilter.equals("Name")) {
                    city = p.getName();
                } else if (strFilter.equals("Age")) {
                    city = String.valueOf(p.getAge());
                }
                setFilterBy(personByCity, p, city);
            }
            if (strOrder.equals("Asc")) {
                linesAdapter.sortAsc(city);
            } else if (strOrder.equals("Desc")) {
                linesAdapter.sortDesc(city);
            }
        }
    }

    private void initRecyclerView() {
        linesAdapter = new GroupAdapter(context);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvAnimation.setLayoutManager(layoutManager);
        rvAnimation.setAdapter(linesAdapter);
    }

    private void addPersonData() {
        people.add(new Person("John", "London", 21));
        people.add(new Person("Kevin", "London", 23));
        people.add(new Person("Monobo", "Tokyo", 12));
        people.add(new Person("Sam", "Paris", 23));
        people.add(new Person("Nadal", "Paris", 31));
        people.add(new Person("Swann", "London", 21));

        people.add(new Person("John", "London", 24));
        people.add(new Person("Kevin", "Tokyo", 25));
        people.add(new Person("Monobo", "Tokyo", 23));
        people.add(new Person("Sam", "Paris", 25));
        people.add(new Person("Nadal", "Paris", 26));
        people.add(new Person("Swann", "Paris", 21));
    }

    private void setFilterBy(Map<String, ArrayList<Person>> personByCity, Person p, String filterBy) {
        if (!personByCity.containsKey(filterBy)) {
            personByCity.put(filterBy, new ArrayList<Person>());
//                personByCity.put(p.getCity(), new ArrayList<>());
            linesAdapter.add(p);
        }
        personByCity.get(filterBy).add(p);
    }
}
