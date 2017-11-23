package com.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.courseproject.model.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends Activity implements View.OnClickListener {

    private final int LAYOUT = R.layout.activity_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showInformation:
            {
                Intent intent = new Intent(this, MainPageUser.class);
                this.startActivity(intent);
                break;
            }
            case R.id.load:
            {
                getInformation();
                break;
            }
        }
    }

    public void getInformation() {
        List<Subject> subjects = new ArrayList<>();
        Type listType = new TypeToken<List<Subject>>() {}.getType();
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "coursework");
        sdPath.mkdirs();
        File file = new File(sdPath, "sybject.json");
        try (FileReader fileReader = new FileReader(file))
        {
            subjects = new Gson().fromJson(new JsonReader(fileReader), listType);
        } catch (IOException e) {
            Toast.makeText(this, "Упс! Что-то не так..." + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, subjects.get(0).toString(), Toast.LENGTH_SHORT).show();
        ((EditText)findViewById(R.id.edit_text_nuber_studak)).setText(subjects.get(0).toString());
    }
}
