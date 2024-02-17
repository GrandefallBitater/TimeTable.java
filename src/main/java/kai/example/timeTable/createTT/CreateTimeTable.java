package kai.example.timeTable.createTT;

import kai.example.timeTable.entity.*;
import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.services.DoSomeList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static kai.example.timeTable.enums.ClassTime.*;

public class CreateTimeTable {
    private final List<ClassTime> classTimeList = new ArrayList<>(List.of(FIRST_CLASS, SECOND_CLASS, THIRD_CLASS,
            FOURTH_CLASS, FIFTH_CLASS, SIXTH_CLASS));
    private final DoSomeList doSomeList = new DoSomeList();
    @Setter
    private List<StudentGroup> groups = new ArrayList<>();
    private List<Subject> subjects = doSomeList.getSubjects();
    private List<Audience> audiences = doSomeList.getAudiences();
    private List<Teacher> teachers = doSomeList.getTeachers();
    @Getter
    private final Week week = new Week();

    public CreateTimeTable(List<StudentGroup> groups, List<Subject> subjects, List<Audience> audiences, List<Teacher> teachers) {
        this.groups = groups;
        this.subjects = subjects;
        this.audiences = audiences;
        this.teachers = teachers;
    }


    public void createTimeTable() {
        // Создание расписания на всю неделю
        for (DayOfWeek day : DayOfWeek.values()) {
            for (StudentGroup group : groups) {
                for (ClassTime time : classTimeList) {
                    if (!(isGroupAvailable(day, time, group))) {
                        continue;
                    }
                    for (Audience audience : audiences) {
                        if (!(isGroupAvailable(day, time, group))) {
                            break;
                        }
                        for (Subject subject : subjects) {
                            if (isSubjectAvailable(subject, group)) {
                                continue;
                            }
                            if (!(isGroupAvailable(day, time, group))) {
                                break;
                            }
                            //Проверка не поставится ли лаба на последнюю возможную пару в день(шестую)
                            if (subject.getTypeSubject().equals(TypeSubject.LABORATORY) && time.equals(SIXTH_CLASS)) {
                                continue;
                            }
                            //Проверка доступности Аудитории
                            if (isClassroomAvailable(day, time, subject, audience, group)) {
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

    private boolean isSubjectAvailable(Subject subject, StudentGroup group) {
        if(subject.getCourseOfSubject() == group.getNumberOfCourse() ) {
            // Проверка был ли поставлен предмет в неделе у данной группы
            return subject.getReservedGroupMap().get(group.getNumberGroup());
        }
        return true;
    }

    private boolean isClassroomAvailable(DayOfWeek day, ClassTime time, Subject subject, Audience audience, StudentGroup group) {
        // Проверка доступности аудитории для предмета для указанного дня недели и времени
        if (audience.getEquipments().containsAll(subject.getEquipments())) {
            if (getCountPeople(group, subject) > audience.getCapacity()) {
                return false;
            }
            return audience.getTimeTableMap().get(day).get(time);
        }
        return false;
    }

    private int getCountPeople(StudentGroup group, Subject subject) {
        int countPeople = 1;
        if (subject.getTypeSubject().equals(TypeSubject.LECTURE)) {
            for (StudentGroup gr : getGroupsOneCourse(group)) {
                countPeople += gr.getCountStudents();
            }
        } else {
            countPeople += group.getCountStudents();
        }
        return countPeople;
    }

    private boolean isTeacherAvailable(DayOfWeek day, ClassTime time, Subject subject, Teacher teacher) {
        // Проверка доступности учителя для указанного дня недели и времени
        if (teacher.getSubject().contains(subject)) {
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
        Day day = week.getDays().stream().filter(x -> x.getDayOfWeek().equals(dayOfWeek)).toList().get(0);
        if (subject.getTypeSubject().equals(TypeSubject.LECTURE)) {
            setTimeTableBlockForLecture(day, time, subject, audience, teacher, getGroupsOneCourse(group));
            return;
        }
        setTimeTableBlock(day, time, subject, audience, teacher, group);
        if (subject.getTypeSubject().equals(TypeSubject.LABORATORY)) {
            ClassTime newTime = classTimeList.get(classTimeList.indexOf(time) + 1);
            setTimeTableBlock(day, newTime, subject, audience, teacher, group);
        }
    }

    private void setTimeTableBlock(Day day, ClassTime time, Subject subject, Audience audience, Teacher teacher, StudentGroup group) {
        subject.changeGroupMap(group);
        day.addSubject(time, audience, teacher, subject, group);
        teacher.changeTimeTableMap(day.getDayOfWeek(), time);
        audience.changeTimeTableMap(day.getDayOfWeek(), time);
        group.changeTimeTableMap(day.getDayOfWeek(), time);
    }

    private void setTimeTableBlockForLecture(Day day, ClassTime time, Subject subject, Audience audience, Teacher teacher, List<StudentGroup> group) {
        day.addLecture(time, audience, teacher, subject, group);
        teacher.changeTimeTableMap(day.getDayOfWeek(), time);
        audience.changeTimeTableMap(day.getDayOfWeek(), time);
        group.forEach(x -> x.changeTimeTableMap(day.getDayOfWeek(), time));
        group.forEach(subject::changeGroupMap);
    }

    private List<StudentGroup> getGroupsOneCourse(StudentGroup group) {
        return groups.stream().filter(x -> x.getNumberOfCourse() == group.getNumberOfCourse())
                .toList();
    }


}
