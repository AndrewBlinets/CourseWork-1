package com.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.dao.MarkDataBaseHadler;
import com.courseproject.dao.ScheduleDataBaseHadler;
import com.courseproject.dao.StudentDataBaseHadler;
import com.courseproject.model.Group;
import com.courseproject.model.Subject;
import com.courseproject.model.mark.Mark;
import com.courseproject.model.mark.MarkForReadJSONFile;
import com.courseproject.model.mark.MarkList;
import com.courseproject.model.schedules.DayClass;
import com.courseproject.model.schedules.Schedules;
import com.courseproject.model.student.Student;
import com.courseproject.model.student.StudentForReadJsonFile;
import com.courseproject.model.student.StudentList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;

public class BaseActivity extends Activity implements View.OnClickListener {

    private final int LAYOUT = R.layout.activity_base;
    private Schedules schedules;
    private StudentList students;
    private MarkList marks;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        textView = (TextView)findViewById(R.id.message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showInformation:
            {
                String stydentCard = ((EditText)findViewById(R.id.edit_text_nuber_studak)).getText().toString();
                if(stydentCard.length() == 6) {
                    StudentDataBaseHadler studentDataBaseHadler = new StudentDataBaseHadler(this);
                    long id = studentDataBaseHadler.getByStydentCard(stydentCard);
                    if(id != 0) {
                        Intent intent = new Intent(this, MainPageUser.class);
                        intent.putExtra(ConstantsForIntent.idStudent, id);
                        this.startActivityForResult(intent,1);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Неверный номер", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Неверный номер", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.load:
            {
                ParserJsonClassSubject parserJsonClassSubject = new ParserJsonClassSubject();
                parserJsonClassSubject.execute();
                break;
            }
        }
    }

    public class ParserJsonClassSubject extends AsyncTask<Void, Void, Void > {

        private String mesasgeAboutEror = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("Загрузка данных...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Type listTypeSchedule = new TypeToken<Schedules>() {}.getType();
            Type listTypeStudent = new TypeToken<StudentList>() {}.getType();
            Type listTypeMark = new TypeToken<MarkList>() {}.getType();
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath.getAbsolutePath() + "/" + "coursework");
            File file = new File(sdPath, "schedule.json");
            try (FileReader fileReader = new FileReader(file))
            {
                schedules = new Gson().fromJson(new JsonReader(fileReader), listTypeSchedule);
            } catch (Exception e) {
                mesasgeAboutEror += "Ошибка - расписание";
            }
            File fileStudent = new File(sdPath, "student.json");
            try (FileReader fileReader = new FileReader(fileStudent))
            {
                students = new Gson().fromJson(new JsonReader(fileReader), listTypeStudent);
            } catch (Exception e) {
                mesasgeAboutEror += "Ошибка - студенты";
            }
            File fileMark = new File(sdPath, "mark.json");
            try (FileReader fileReader = new FileReader(fileMark))
            {
                marks = new Gson().fromJson(new JsonReader(fileReader), listTypeMark);
            } catch (Exception e) {
                mesasgeAboutEror += "Ошибка - оценки";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loadToDB();
            if(mesasgeAboutEror.length() == 0)
            {
                textView.setText("Данные загруженны успешно");
            }
            else
            {
                textView.setText(mesasgeAboutEror);
            }
        }
    }

    private void loadToDB() {
        ScheduleDataBaseHadler scheduleDataBaseHadler = new ScheduleDataBaseHadler(this);
        for (DayClass dayClass: schedules.getSchedules()) {
            scheduleDataBaseHadler.add(dayClass);
        }
        StudentDataBaseHadler studentDataBaseHadler = new StudentDataBaseHadler(this);
        for(StudentForReadJsonFile student: students.getStudents())
        {
            studentDataBaseHadler.add(new Student(student.getName(), student.getSurName(),
                    student.getSecondName(),new Group(student.getIdGroup()), student.getNumberStudentCard(),
                    student.getFoto()));
        }
        MarkDataBaseHadler markDataBaseHadler = new MarkDataBaseHadler(this);
        for (MarkForReadJSONFile mark:marks.getMarks())
        {
            markDataBaseHadler.add(new Mark(new Student(mark.getIdStudent()), new Subject(mark.getIdSubject()), mark.getMark()));
        }
    }

}


