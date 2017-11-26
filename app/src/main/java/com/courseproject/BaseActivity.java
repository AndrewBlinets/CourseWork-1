package com.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.courseproject.dao.SubjectDataBaseHadler;
import com.courseproject.modelforparser.Subject;
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
    private List<String> subjectsName;

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
                SubjectDataBaseHadler subjectDataBaseHadler = new SubjectDataBaseHadler(this);
                for (String str:subjectsName)
                {
                    subjectDataBaseHadler.add(new com.courseproject.model.Subject(str));
                }
                List<com.courseproject.model.Subject> list = subjectDataBaseHadler.getAll();
                Intent intent = new Intent(this, MainPageUser.class);
                this.startActivity(intent);
                break;
            }
            case R.id.load:
            {
               // ScheduleDataBaseHadler scheduleDataBaseHadler = new ScheduleDataBaseHadler(this);
                //scheduleDataBaseHadler.add(new Schedule());
              //  ((TextView)findViewById(R.id.message)).setText("Данные из файлов загружены.");
                //getInformation();
                ParserJsonClassSubject parserJsonClassSubject = new ParserJsonClassSubject();
                parserJsonClassSubject.execute();
                int a = 0;
                a++;
                a++;
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

    public class ParserJsonClassSubject extends AsyncTask<Void, Void, Void > {

        @Override
        protected Void doInBackground(Void... voids) {
          //  List<String> subjectsa = new ArrayList<>();
            Type listType = new TypeToken<List<String>>() {}.getType();
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath.getAbsolutePath() + "/" + "coursework");
            //sdPath.mkdirs();
            File file = new File(sdPath, "sybject.json");
            try (FileReader fileReader = new FileReader(file))
            {
                subjectsName = new Gson().fromJson(new JsonReader(fileReader), listType);
            } catch (IOException e) {

            }
            return null;
        }
    }

}


