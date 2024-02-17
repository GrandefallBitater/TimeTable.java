package kai.example.timeTable.createTT;

import kai.example.timeTable.entity.Audience;
import kai.example.timeTable.entity.StudentGroup;
import kai.example.timeTable.entity.Subject;
import kai.example.timeTable.entity.Teacher;
import kai.example.timeTable.enums.Equipment;
import kai.example.timeTable.enums.TypeSubject;

import java.util.ArrayList;
import java.util.List;

public class ClassForCreateList {
    List<Audience> createAudiences() {
        List<Audience> audiences = new ArrayList<>();
        audiences.add(new Audience(337, 55,
                new ArrayList<>(List.of(Equipment.BOARD, Equipment.COMPUTERS))));
        audiences.add(new Audience(327, 86,
                new ArrayList<>(List.of(Equipment.BOARD))));
        audiences.add(new Audience(333, 40,
                new ArrayList<>(List.of(Equipment.BOARD))));
        audiences.add(new Audience(403, 90,
                new ArrayList<>(List.of(Equipment.PROJECTOR))));
        return audiences;
    }

    List<Subject> createSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("ООП", TypeSubject.LECTURE, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD))));
        subjects.add(new Subject("ООП", TypeSubject.LABORATORY, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD, Equipment.COMPUTERS))));
        subjects.add(new Subject("Матан", TypeSubject.LECTURE, 50,4,
                new ArrayList<>(List.of(Equipment.PROJECTOR))));
        subjects.add(new Subject("Матан", TypeSubject.LABORATORY, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD))));
        subjects.forEach(x -> x.fillGroupMap(createGroups()));
        return subjects;
    }

    List<Teacher> createTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        List<Subject> sotnya = new ArrayList<>();
        List<Subject> anisimova = new ArrayList<>();
        sotnya.add(new Subject("ООП", TypeSubject.LABORATORY, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD, Equipment.COMPUTERS))));
        sotnya.add(new Subject("ООП", TypeSubject.LECTURE, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD))));
        anisimova.add(new Subject("Матан", TypeSubject.LECTURE, 50,4,
                new ArrayList<>(List.of(Equipment.PROJECTOR))));
        anisimova.add(new Subject("Матан", TypeSubject.LABORATORY, 50,4,
                new ArrayList<>(List.of(Equipment.BOARD))));
        teachers.add(new Teacher("Сотыч",sotnya, new ArrayList<>()));
        teachers.add(new Teacher("Анисимова", anisimova, new ArrayList<>()));
        return teachers;
    }

    List<StudentGroup> createGroups() {
        List<StudentGroup> groups = new ArrayList<>();
        groups.add(new StudentGroup(4410, 13, 4));
        groups.add(new StudentGroup(4411, 15, 4));
        groups.add(new StudentGroup(4417, 7, 4));
        return groups;
    }
}
