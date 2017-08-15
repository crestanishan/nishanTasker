package com.nishan.tasker.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nishan.tasker.Activity.MapsActivity;
import com.nishan.tasker.R;

import java.util.Calendar;
import java.util.Date;


public class AddTaskFragment extends TaskerFragment implements View.OnClickListener{

    private Button selectLocation , selectDate, selectTime , taskDetail;
    public ImageView checkLocation, checkDate , checkTime , checkDetails;

    private Button addTask;

    int sYear;
    int sMonth;
    int sDay;
    private Date date;
    String datePicked = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_add_task, null);

        initiliseView(view);
        initialiseListener();
        return view;
    }

    @Override
    protected void initiliseView(View view) {
        selectLocation = (Button) view.findViewById(R.id.select_location);
        selectDate = (Button) view.findViewById(R.id.select_date);
        selectTime = (Button) view.findViewById(R.id.select_time);
        taskDetail = (Button) view.findViewById(R.id.task_details);
        addTask = (Button) view.findViewById(R.id.btn_add_task);

        checkLocation = (ImageView) view.findViewById(R.id.check_location);
        checkDate = (ImageView) view.findViewById(R.id.check_date);
        checkTime = (ImageView) view.findViewById(R.id.check_time);
        checkDetails = (ImageView) view.findViewById(R.id.check_details);



    }

    @Override
    protected void initialiseListener() {

        selectLocation.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        taskDetail.setOnClickListener(this);
        addTask.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.select_location:
                funSelectLocation();
                break;
            case R.id.select_date:
                funSelectDate();
                break;
            case R.id.select_time:
                funSelectTime();
                break;

            case R.id.task_details:
                funTaskDetails();
                break;

            case  R.id.btn_add_task:

                funAddTask();
                break;


        }

    }



    private void funSelectLocation() {

        Intent intent = new Intent(getContext(), MapsActivity.class);
        startActivity(intent);


    }

    private void funSelectDate() {

        final Calendar c = Calendar.getInstance();
        sYear = c.get(Calendar.YEAR);
        sMonth = c.get(Calendar.MONTH);
        sDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        datePicked = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        checkDate.setColorFilter(Color.RED);

                        //datepicked ?? this will give selected date


                    }
                }, sYear, sMonth, sDay);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();


        // Add to shared pref or database ... selected time


    }

    private void funSelectTime() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


//                .setText( selectedHour + ":" + selectedMinute);
                checkTime.setColorFilter(Color.RED);

                // do something .. the above comment will give selected time


            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void funTaskDetails() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.task_details, null);
        builder.setView(itemView);
        builder.create();
        final AlertDialog alertDialog = builder.show();

        final EditText task1 = (EditText) itemView.findViewById(R.id.task1);
        final EditText task2 = (EditText) itemView.findViewById(R.id.task2);

        final Button btnAdd = itemView.findViewById(R.id.btn_add_details);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String taskOne = task1.getText().toString().trim();
                final String taskTwo = task2.getText().toString().trim();

                if (taskOne.isEmpty() || taskTwo.isEmpty()){

                    Toast.makeText(getContext(), " Task fields required ", Toast.LENGTH_SHORT).show();

                }else {


                    alertDialog.hide();

                    checkDetails.setColorFilter(Color.RED);


                    // add to db or do something





                }




            }
        });




    }

    private void funAddTask() {

        // do at last adding task and starting service

        Toast.makeText(getContext(), "Add Task", Toast.LENGTH_SHORT).show();



    }



}
