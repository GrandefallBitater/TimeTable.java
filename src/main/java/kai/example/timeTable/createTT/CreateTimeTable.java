package kai.example.timeTable.createTT;

import kai.example.timeTable.entity.*;
import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.services.DoSomeList;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static kai.example.timeTable.enums.ClassTime.*;

public class CreateTimeTable {
    @Setter
    private final List<StudentGroup> groups = new ArrayList<>();
    private final List<ClassTime> classTimeList = new ArrayList<>(List.of(FIRST_CLASS, SECOND_CLASS, THIRD_CLASS,
            FOURTH_CLASS, FIFTH_CLASS, SIXTH_CLASS));
    DoSomeList doSomeList = new DoSomeList();
    @Setter
    private final List<Subject> subjects = doSomeList.getSubjects();
    @Setter
    private final List<Audience> audiences = doSomeList.getAudiences();
    @Setter
    private final List<Teacher> teachers = doSomeList.getTeachers();
    private Week week;


    //На вход подаются группы одного курса, также подаются преподаватели, которые ведут лекции, а также предметы по которым у данного курса лекции
    public void createTimeTable() {
        // Создание расписания на всю неделю
        for (DayOfWeek day : DayOfWeek.values()) {
            for (StudentGroup group : groups) {
                for (ClassTime time : classTimeList) {
                    if (isGroupAvailable(day, time, group)){
                        continue;
                    }
                    for (Audience audience : audiences) {
                        if (isGroupAvailable(day, time, group)){
                            break;
                        }
                        for (Subject subject : subjects) {
                            if (isGroupAvailable(day, time, group)){
                                break;
                            }
                            //Проверка не поставится ли лаба на последнюю возможную пару в день(шестую)
                            if (subject.getTypeSubject().equals(TypeSubject.LABORATORY) && time.equals(SIXTH_CLASS)) {
                                return;
                            }
                            //Проверка доступности Аудитории
                            if (isClassroomAvailable(day, time, subject, audience)) {
                                //Условность: не может быть преподов, ведущих одинаковые предметы
                                for (Teacher teacher : teachers) {
                                    // Проверка доступности Учителя
                                    if (isTeacherAvailable(day, time, subject, teacher)) {

                                        // Проверка доступности Группы
                                        if (isGroupAvailable(day, time, group)) {
                                            // Добавление предмета в расписание
                                            addSubjectToTimetable(day, time, subject, audience, teacher, group);
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }
        }
    }

    private boolean isClassroomAvailable(DayOfWeek day, ClassTime time, Subject subject, Audience audience) {
        // Проверка доступности аудитории для предмета для указанного дня недели и времени
        if (audience.getEquipments().containsAll(subject.getEquipments())) {
            return audience.getTimeTableMap().get(day).get(time);
        }
        return false;

    }

    private boolean isTeacherAvailable(DayOfWeek day, ClassTime time, Subject subject, Teacher teacher) {
        // Проверка доступности учителя для указанного дня недели и времени
        if (teacher.getSubject().equals(subject)) {
            return teacher.getTimeTableMap().get(day).get(time);
        }
        return false;
    }

    private boolean isGroupAvailable(DayOfWeek day, ClassTime time, StudentGroup group) {
        // Проверка доступности группы для указанного дня недели и времени
        return group.getTimeTableMap().get(day).get(time);
    }

    private void addSubjectToTimetable(DayOfWeek dayOfWeek, ClassTime time, Subject subject, Audience audience, Teacher teacher, StudentGroup group) {
        // Добавление предмета в расписание для указанного дня недели и времени
        Day day = null;
        int indexDay;
        for (Day d : week.getDays()) {
            if (d.getDayOfWeek().equals(dayOfWeek)) {
                day = d;
                indexDay = week.getDays().indexOf(d);
                week.getDays().remove(indexDay);
            }
        }
        if (day == null) {
            day = new Day(dayOfWeek);
        }
        setTimeTableBlock(day, time, subject, audience, teacher, group);
        if (subject.getTypeSubject().equals(TypeSubject.LABORATORY)) {
            ClassTime newTime = classTimeList.get(classTimeList.indexOf(time) + 1);
            setTimeTableBlock(day, newTime, subject, audience, teacher, group);
        }
    }
    private void setTimeTableBlock(Day day, ClassTime time, Subject subject, Audience audience, Teacher teacher, StudentGroup group){
        day.addSubject(time, subject, audience, teacher, group);
        teacher.changeTimeTableMap(day.getDayOfWeek(),time);
        audience.changeTimeTableMap(day.getDayOfWeek(),time);
        group.changeTimeTableMap(day.getDayOfWeek(),time);
        week.addDay(day);
    }


}
