package com.milkflo.app.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.milkflo.app.R;


public class Milestones extends Fragment {


    public Milestones() {
        // Required empty public constructor
    }


    public static Milestones getInstance() {
        Milestones fragment = new Milestones();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_milestones, container, false);


        CheckBox checkBox1 = root.findViewById(R.id.m1checkbox);
        CheckBox checkBox2 = root.findViewById(R.id.m2checkbox);
        CheckBox checkBox3 = root.findViewById(R.id.m3checkbox);
        CheckBox checkBox4 = root.findViewById(R.id.m4checkbox);
        CheckBox checkBox5 = root.findViewById(R.id.m5checkbox);
        CheckBox checkBox6 = root.findViewById(R.id.m6checkbox);
        CheckBox checkBox7 = root.findViewById(R.id.m7checkbox);
        CheckBox checkBox8 = root.findViewById(R.id.m8checkbox);
        CheckBox checkBox9 = root.findViewById(R.id.m9checkbox);
        CheckBox checkBox10 = root.findViewById(R.id.m10checkbox);
        CheckBox checkBox11 = root.findViewById(R.id.m11checkbox);
        CheckBox checkBox12 = root.findViewById(R.id.m12checkbox);
        CheckBox checkBox13 = root.findViewById(R.id.m13checkbox);
        CheckBox checkBox14 = root.findViewById(R.id.m14checkbox);
        CheckBox checkBox15 = root.findViewById(R.id.m15checkbox);
        CheckBox checkBox16 = root.findViewById(R.id.m16checkbox);
        CheckBox checkBox17 = root.findViewById(R.id.m17checkbox);
        CheckBox checkBox18 = root.findViewById(R.id.m18checkbox);
        CheckBox checkBox19 = root.findViewById(R.id.m19checkbox);
        CheckBox checkBox20 = root.findViewById(R.id.m20checkbox);


        EditText editText1 = root.findViewById(R.id.m1comment);
        EditText editText2 = root.findViewById(R.id.m2comment);
        EditText editText3 = root.findViewById(R.id.m3comment);
        EditText editText4 = root.findViewById(R.id.m4comment);
        EditText editText5 = root.findViewById(R.id.m5comment);
        EditText editText6 = root.findViewById(R.id.m6comment);
        EditText editText7 = root.findViewById(R.id.m7comment);
        EditText editText8 = root.findViewById(R.id.m8comment);
        EditText editText9 = root.findViewById(R.id.m9comment);
        EditText editText10 = root.findViewById(R.id.m10comment);
        EditText editText11 = root.findViewById(R.id.m11comment);
        EditText editText12 = root.findViewById(R.id.m12comment);
        EditText editText13 = root.findViewById(R.id.m13comment);
        EditText editText14 = root.findViewById(R.id.m14comment);
        EditText editText15 = root.findViewById(R.id.m15comment);
        EditText editText16 = root.findViewById(R.id.m16comment);
        EditText editText17 = root.findViewById(R.id.m17comment);
        EditText editText18 = root.findViewById(R.id.m18comment);
        EditText editText19 = root.findViewById(R.id.m19comment);
        EditText editText20 = root.findViewById(R.id.m20comment);


        Button fButton= root.findViewById(R.id.saveButton);


        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                @SuppressLint("CommitPrefEdits")
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("PREF1", Context.MODE_PRIVATE).edit();


                editor.putBoolean("CK1",checkBox1.isChecked());
                editor.putBoolean("CK2",checkBox2.isChecked());
                editor.putBoolean("CK3",checkBox3.isChecked());
                editor.putBoolean("CK4",checkBox4.isChecked());
                editor.putBoolean("CK5",checkBox5.isChecked());
                editor.putBoolean("CK6",checkBox6.isChecked());
                editor.putBoolean("CK7",checkBox7.isChecked());
                editor.putBoolean("CK8",checkBox8.isChecked());
                editor.putBoolean("CK9",checkBox9.isChecked());
                editor.putBoolean("CK10",checkBox10.isChecked());
                editor.putBoolean("CK11",checkBox11.isChecked());
                editor.putBoolean("CK12",checkBox12.isChecked());
                editor.putBoolean("CK13",checkBox13.isChecked());
                editor.putBoolean("CK14",checkBox14.isChecked());
                editor.putBoolean("CK15",checkBox15.isChecked());
                editor.putBoolean("CK16",checkBox16.isChecked());
                editor.putBoolean("CK17",checkBox17.isChecked());
                editor.putBoolean("CK18",checkBox18.isChecked());
                editor.putBoolean("CK19",checkBox19.isChecked());
                editor.putBoolean("CK20",checkBox20.isChecked());


                editor.putString("TEXT1", editText1.getText().toString());
                editor.putString("TEXT2", editText2.getText().toString());
                editor.putString("TEXT3", editText3.getText().toString());
                editor.putString("TEXT4", editText4.getText().toString());
                editor.putString("TEXT5", editText5.getText().toString());
                editor.putString("TEXT6", editText6.getText().toString());
                editor.putString("TEXT7", editText7.getText().toString());
                editor.putString("TEXT8", editText8.getText().toString());
                editor.putString("TEXT9", editText9.getText().toString());
                editor.putString("TEXT10", editText10.getText().toString());
                editor.putString("TEXT11", editText11.getText().toString());
                editor.putString("TEXT12", editText12.getText().toString());
                editor.putString("TEXT13", editText13.getText().toString());
                editor.putString("TEXT14", editText14.getText().toString());
                editor.putString("TEXT15", editText15.getText().toString());
                editor.putString("TEXT16", editText16.getText().toString());
                editor.putString("TEXT17", editText17.getText().toString());
                editor.putString("TEXT18", editText18.getText().toString());
                editor.putString("TEXT19", editText19.getText().toString());
                editor.putString("TEXT20", editText20.getText().toString());


                editor.apply();
            }
        });


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF1", Context.MODE_PRIVATE);


        boolean checkBox1_state = sharedPreferences.getBoolean("CK1",false);
        boolean checkBox2_state = sharedPreferences.getBoolean("CK2",false);
        boolean checkBox3_state = sharedPreferences.getBoolean("CK3",false);
        boolean checkBox4_state = sharedPreferences.getBoolean("CK4",false);
        boolean checkBox5_state = sharedPreferences.getBoolean("CK5",false);
        boolean checkBox6_state = sharedPreferences.getBoolean("CK6",false);
        boolean checkBox7_state = sharedPreferences.getBoolean("CK7",false);
        boolean checkBox8_state = sharedPreferences.getBoolean("CK8",false);
        boolean checkBox9_state = sharedPreferences.getBoolean("CK9",false);
        boolean checkBox10_state = sharedPreferences.getBoolean("CK10",false);
        boolean checkBox11_state = sharedPreferences.getBoolean("CK11",false);
        boolean checkBox12_state = sharedPreferences.getBoolean("CK12",false);
        boolean checkBox13_state = sharedPreferences.getBoolean("CK13",false);
        boolean checkBox14_state = sharedPreferences.getBoolean("CK14",false);
        boolean checkBox15_state = sharedPreferences.getBoolean("CK15",false);
        boolean checkBox16_state = sharedPreferences.getBoolean("CK16",false);
        boolean checkBox17_state = sharedPreferences.getBoolean("CK17",false);
        boolean checkBox18_state = sharedPreferences.getBoolean("CK18",false);
        boolean checkBox19_state = sharedPreferences.getBoolean("CK19",false);
        boolean checkBox20_state = sharedPreferences.getBoolean("CK20",false);


        String editText1_state = sharedPreferences.getString("TEXT1", editText1.getText().toString());
        String editText2_state = sharedPreferences.getString("TEXT2", editText1.getText().toString());
        String editText3_state = sharedPreferences.getString("TEXT3", editText1.getText().toString());
        String editText4_state = sharedPreferences.getString("TEXT4", editText1.getText().toString());
        String editText5_state = sharedPreferences.getString("TEXT5", editText1.getText().toString());
        String editText6_state = sharedPreferences.getString("TEXT6", editText1.getText().toString());
        String editText7_state = sharedPreferences.getString("TEXT7", editText1.getText().toString());
        String editText8_state = sharedPreferences.getString("TEXT8", editText1.getText().toString());
        String editText9_state = sharedPreferences.getString("TEXT9", editText1.getText().toString());
        String editText10_state = sharedPreferences.getString("TEXT10", editText1.getText().toString());
        String editText11_state = sharedPreferences.getString("TEXT11", editText1.getText().toString());
        String editText12_state = sharedPreferences.getString("TEXT12", editText1.getText().toString());
        String editText13_state = sharedPreferences.getString("TEXT13", editText1.getText().toString());
        String editText14_state = sharedPreferences.getString("TEXT14", editText1.getText().toString());
        String editText15_state = sharedPreferences.getString("TEXT15", editText1.getText().toString());
        String editText16_state = sharedPreferences.getString("TEXT16", editText1.getText().toString());
        String editText17_state = sharedPreferences.getString("TEXT17", editText1.getText().toString());
        String editText18_state = sharedPreferences.getString("TEXT18", editText1.getText().toString());
        String editText19_state = sharedPreferences.getString("TEXT19", editText1.getText().toString());
        String editText20_state = sharedPreferences.getString("TEXT20", editText1.getText().toString());


        checkBox1.setChecked(checkBox1_state);
        checkBox2.setChecked(checkBox2_state);
        checkBox3.setChecked(checkBox3_state);
        checkBox4.setChecked(checkBox4_state);
        checkBox5.setChecked(checkBox5_state);
        checkBox6.setChecked(checkBox6_state);
        checkBox7.setChecked(checkBox7_state);
        checkBox8.setChecked(checkBox8_state);
        checkBox9.setChecked(checkBox9_state);
        checkBox10.setChecked(checkBox10_state);
        checkBox11.setChecked(checkBox11_state);
        checkBox12.setChecked(checkBox12_state);
        checkBox13.setChecked(checkBox13_state);
        checkBox14.setChecked(checkBox14_state);
        checkBox15.setChecked(checkBox15_state);
        checkBox16.setChecked(checkBox16_state);
        checkBox17.setChecked(checkBox17_state);
        checkBox18.setChecked(checkBox18_state);
        checkBox19.setChecked(checkBox19_state);
        checkBox20.setChecked(checkBox20_state);


        editText1.setText(editText1_state);
        editText2.setText(editText2_state);
        editText3.setText(editText3_state);
        editText4.setText(editText4_state);
        editText5.setText(editText5_state);
        editText6.setText(editText6_state);
        editText7.setText(editText7_state);
        editText8.setText(editText8_state);
        editText9.setText(editText9_state);
        editText10.setText(editText10_state);
        editText11.setText(editText11_state);
        editText12.setText(editText12_state);
        editText13.setText(editText13_state);
        editText14.setText(editText14_state);
        editText15.setText(editText15_state);
        editText16.setText(editText16_state);
        editText17.setText(editText17_state);
        editText18.setText(editText18_state);
        editText19.setText(editText19_state);
        editText20.setText(editText20_state);


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}