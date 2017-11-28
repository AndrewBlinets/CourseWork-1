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

// базовое активити, стартовое для приложения
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
        textView = findViewById(R.id.message);
    }

    @Override
    public void onClick(View v) {// обработчик нажатия на кнопки
        switch (v.getId()) {
            case R.id.showInformation:
            {
                String stydentCard = ((EditText)findViewById(R.id.edit_text_nuber_studak)).getText().toString();// чекаем студак
                if(stydentCard.length() == 6) {
                    try{
                        StudentDataBaseHadler studentDataBaseHadler = new StudentDataBaseHadler(this);

                    long id = studentDataBaseHadler.getByStydentCard(stydentCard);
                    if(id != 0) {
                        Intent intent = new Intent(this, MainPageUser.class);
                        intent.putExtra(ConstantsForIntent.idStudent, id);
                        this.startActivityForResult(intent,1);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Неверный номер bD" + studentDataBaseHadler.getById(0).getName(), Toast.LENGTH_SHORT).show();
                    }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
                ParserJsonClassSubject parserJsonClassSubject = new ParserJsonClassSubject();// парсим файлы
                parserJsonClassSubject.execute();
                break;
            }
        }
    }

    // так как парсинг JSON это ресрсно затратная операция, его необходимо производить в отдельном потоке от UI
    public class ParserJsonClassSubject extends AsyncTask<Void, Void, Void > {

        private String mesasgeAboutEror = "";

        @Override
        protected void onPreExecute() {//метод выполняющийся до начала основной работы класса
            super.onPreExecute();
            textView.setText("Загрузка данных...");
        }

        @Override
        protected Void doInBackground(Void... voids) {// парсинг
                Type listTypeSchedule = new TypeToken<Schedules>() {
                }.getType();// указываем тип данных для парсинга
                Type listTypeStudent = new TypeToken<StudentList>() {
                }.getType();
                Type listTypeMark = new TypeToken<MarkList>() {
                }.getType();
                File sdPath = Environment.getExternalStorageDirectory();// открываем файл в памяти тела
                sdPath = new File(sdPath.getAbsolutePath() + "/" + "coursework");
                File file = new File(sdPath, "schedule.json");
                try (FileReader fileReader = new FileReader(file)) {
                    schedules = new Gson().fromJson(new JsonReader(fileReader), listTypeSchedule);// читаем и парсим файл
                } catch (Exception e) {
                    mesasgeAboutEror += "Ошибка - расписание";
                }
                File fileStudent = new File(sdPath, "student.json");
                try (FileReader fileReader = new FileReader(fileStudent)) {
                    students = new Gson().fromJson(new JsonReader(fileReader), listTypeStudent);
                } catch (Exception e) {
                    mesasgeAboutEror += "Ошибка - студенты";
                }
                File fileMark = new File(sdPath, "mark.json");
                try (FileReader fileReader = new FileReader(fileMark)) {
                    marks = new Gson().fromJson(new JsonReader(fileReader), listTypeMark);
                } catch (Exception e) {
                    mesasgeAboutEror += "Ошибка - оценки";
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {// метод выполняемый после завершения основной работы класса(потока)
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

    private void loadToDB() {// метод для записи данных в БД
        ScheduleDataBaseHadler scheduleDataBaseHadler = new ScheduleDataBaseHadler(this);
        scheduleDataBaseHadler.deleteAll();
        for (DayClass dayClass: schedules.getSchedules()) {
            scheduleDataBaseHadler.add(dayClass);
        }
        StudentDataBaseHadler studentDataBaseHadler = new StudentDataBaseHadler(this);
        for(StudentForReadJsonFile student: students.getStudents())
        {// чекаем есть ли студент с таким id
            if (studentDataBaseHadler.getById(student.getId()) == null) {
                studentDataBaseHadler.add(new Student(student.getId(), student.getName(), student.getSurName(),
                        student.getSecondName(), new Group(student.getIdGroup()), student.getNumberStudentCard(),
                        student.getFoto()));
                }
            else
            {
                studentDataBaseHadler.update(new Student(student.getId(), student.getName(), student.getSurName(),
                        student.getSecondName(), new Group(student.getIdGroup()), student.getNumberStudentCard(),
                        student.getFoto()));
            }
        }
        MarkDataBaseHadler markDataBaseHadler = new MarkDataBaseHadler(this);
        for (MarkForReadJSONFile mark:marks.getMarks()) {// чекаем есть ли оценки с таким id
            if (markDataBaseHadler.getById(mark.getId()) == null) {
                markDataBaseHadler.add(new Mark(mark.getId(), new Student(mark.getIdStudent()), new Subject(mark.getIdSubject()), mark.getMark()));
            }
            else
            {
                markDataBaseHadler.update(new Mark(mark.getId(), new Student(mark.getIdStudent()), new Subject(mark.getIdSubject()), mark.getMark()));
            }
        }
    }

}


