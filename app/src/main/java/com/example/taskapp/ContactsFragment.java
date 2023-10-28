package com.example.taskapp;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactsFragment extends Fragment {
    private RecyclerView rvContacts;
    private ArrayList<Duty> list, addList;
    private String[] types = {"familia","estudios","trabajo"};
    private ArrayAdapter<String> spAdapter;
    private Spinner spTypes;
    private TextView tvNameT, tvdescT, tvTypeT;
    private String nameTask, descTask, typeTask;
    private Button btnAdd;
    private Duty newDuty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        list =  new ArrayList<>();

        list.add(new Duty(false, "test", "test duty", "testing", 0, R.drawable.ic_launcher_foreground));
        SaveDuty(list);
        btnAdd = view.findViewById(R.id.btnAdd);

        spTypes = view.findViewById(R.id.spTypes);
        spAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,types);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypes.setAdapter(spAdapter);

        rvContacts = (RecyclerView) view.findViewById(R.id.rvTasks);
        rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));

        com.example.dutyapp.DutyAdapter adapter = new com.example.dutyapp.DutyAdapter(list);
        rvContacts.setHasFixedSize(true);
        rvContacts.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getActivity(), AddActivity.class);
                startActivity(i);
            }
        });
        /*if(){

        }*/
        addList = devolverPrefs();
        list.add(addList.get(0));
        return view;
    }
    public ArrayList<Duty> devolverPrefs(){//recuperar el objeto
        SharedPreferences preferences = getContext().getSharedPreferences("data", 0);// getSharedElementEnterTransition("data", 0);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String datosRecuperados = preferences.getString("duty", "");
        Duty[] tareas = gson.fromJson(datosRecuperados, Duty[].class);
        return new ArrayList<Duty>(Arrays.asList(tareas));
    }
    private void SaveDuty(ArrayList<Duty> list) {
        SharedPreferences sp = getContext().getSharedPreferences("data", 0);
        SharedPreferences.Editor saveDuty = sp.edit();
        Gson gson = new Gson();
        String spDuty = gson.toJson(list);
        saveDuty.putString("duty", spDuty);
        saveDuty.apply();
    }
}