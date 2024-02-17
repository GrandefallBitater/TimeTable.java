package kai.example.timeTable.services;

import kai.example.timeTable.entity.Audience;
import kai.example.timeTable.entity.StudentGroup;
import kai.example.timeTable.entity.Subject;
import kai.example.timeTable.entity.Teacher;
import kai.example.timeTable.enums.Equipment;
import kai.example.timeTable.enums.TypeSubject;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DoSomeList {
    private final String splitBy = ",";
    @Getter
    private final List<Subject> subjects = new ArrayList<>();
    @Getter
    private final List<Audience> audiences = new ArrayList<>();
    @Getter
    private final List<Teacher> teachers = new ArrayList<>();
    @Getter
    private final List<StudentGroup> groups = new ArrayList<>();
    private String urlAudience = "src/main/resources/files/audience.txt";
    private String urlTeachers = "src/main/resources/files/teachers.txt";
    private String urlSubjects = "src/main/resources/files/plan.txt";
    private String urlGroups = "src/main/resources/files/groups.txt";

    public DoSomeList() {
    }

    public DoSomeList(String urlAudience, String urlTeachers, String urlSubjects, String urlGroups) {
        this.urlAudience = urlAudience;
        this.urlTeachers = urlTeachers;
        this.urlSubjects = urlSubjects;
        this.urlGroups = urlGroups;
    }

    public void makeAllLists() {
        getAudienceFromFile();
        getSubjectsFromFile();
        getTeachersFromFile();
        getGroupsFromFile();
    }

    //Название,тип предмета(лекция// лекция, практическая работа),всегоЧасов, курс предмета, нужноеОборудование(его может быть много)
    //химия,леrция,30,компьютеры,доска
    private void getSubjectsFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                subjects.add(new Subject(parts[0], getTypeSubject(parts[1]), Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), getEquipments(Arrays.copyOfRange(parts, 4, parts.length - 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ФИО, Наименование предмета, тип преподаваемого предмета(лекция// лекция, практическая работа)
    private void getTeachersFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlTeachers))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                String FIO = parts[0];
                List<TypeSubject> typeOfTeaching = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    typeOfTeaching.add(getTypeSubject(parts[i]));
                }
                List<Subject> subject = getSubjForTeacher(parts[1], typeOfTeaching);
                Teacher teacher = new Teacher(FIO, subject, typeOfTeaching);
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Номер аудитории,вместимость,оборудование(его может быть много)
    private void getAudienceFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlAudience))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                audiences.add(new Audience(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                        getEquipments(Arrays.copyOfRange(parts, 2, parts.length - 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getGroupsFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlGroups))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                groups.add(new StudentGroup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TypeSubject getTypeSubject(String type) {
        return switch (type) {
            case "лекция" -> TypeSubject.LECTURE;
            case "практическая работа" -> TypeSubject.PRACTICE;
            case "лабораторная работа" -> TypeSubject.LABORATORY;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private List<Equipment> getEquipments(String[] parts) {
        List<Equipment> equipment = new ArrayList<>();
        for (String part : parts) {
            switch (part) {
                case "компьютеры" -> equipment.add(Equipment.COMPUTERS);
                case "доска" -> equipment.add(Equipment.BOARD);
                case "проектор" -> equipment.add(Equipment.PROJECTOR);
            }
        }
        return equipment;
    }

    private List<Subject> getSubjForTeacher(String subjectName, List<TypeSubject> typeSubjects) {
        List<Subject> subs = new ArrayList<>();
        for (Subject sub : subjects) {
            if (sub.getSubjectName().equals(subjectName) && typeSubjects.contains(sub.getTypeSubject())) {
                subs.add(sub);
            }
        }
        return subs;
    }
}
