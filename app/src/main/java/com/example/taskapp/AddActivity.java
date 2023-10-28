package com.example.taskapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    private Button btnPopup, btnBack;
    private String nameT, descT, typeT;
    private Bitmap imgURI;

    private ArrayList<Duty> addList = new ArrayList<>();
    private ImageView ivNewTask;
    private EditText etNameTask, etDescTask, etTypeTask;
    private Button btnAddTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnPopup = (Button) findViewById(R.id.btnOpenPopup);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddActivity.this, "click", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_layout, null);

                int w = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int h = RelativeLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                final PopupWindow popupWindow = new PopupWindow(popupView, w, h, focusable);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);


                ivNewTask = popupView.findViewById(R.id.ivPopup);
                btnAddTask = popupView.findViewById(R.id.btnAddPopup);
                etNameTask = popupView.findViewById(R.id.etNameTPopup);
                etDescTask = popupView.findViewById(R.id.etDescTPopup);
                etTypeTask = popupView.findViewById(R.id.etTypeTPopup);
                ivNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivNewTask.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                abrirCamara();
                            }
                        });
                    }
                });
                btnAddTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameT = etNameTask.getText().toString().trim();
                        descT = etDescTask.getText().toString().trim();
                        typeT = etTypeTask.getText().toString().trim();
                        Duty d = new Duty(false, nameT, descT, typeT, 1, null);
                        addList.add(d);
                        SaveDuty(addList);
                        popupWindow.dismiss();
                    }
                });
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void SaveDuty(ArrayList<Duty> list) {
        SharedPreferences sp = getSharedPreferences("data", 0);
        SharedPreferences.Editor saveDuty = sp.edit();
        Gson gson = new Gson();
        String spDuty = gson.toJson(list);
        saveDuty.putString("duty", spDuty);
        saveDuty.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bundle extras = data.getExtras();
            imgURI = (Bitmap) extras.get("data");
            ivNewTask.setImageBitmap(imgURI);
        }
    }
        private void abrirCamara () {
            Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intentCamara, 1);
        }
    }