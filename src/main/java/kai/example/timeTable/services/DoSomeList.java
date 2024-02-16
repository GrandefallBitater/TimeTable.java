package kai.example.timeTable.services;

import kai.example.timeTable.entity.Audience;
import kai.example.timeTable.entity.Subject;
import kai.example.timeTable.entity.Teacher;
import kai.example.timeTable.enums.Equipment;
import kai.example.timeTable.enums.TypeSubject;
import lombok.Getter;

import java.io.*;
import java.util.*;


public class DoSomeList {
    private final String urlAudience = "src/main/resources/files/audience.txt";
    private final String urlTeachers = "src/main/resources/files/teachers.txt";
    private final String urlSubjects = "src/main/resources/files/plan.txt";
    private final String splitBy = ",";
    @Getter
    private List<Subject> subjects;
    @Getter
    private List<Audience> audiences;
    @Getter
    private List<Teacher> teachers;

    //Название,тип предмета(лекция// лекция, практическая работа),всегоЧасов, нужноеОборудование(его может быть много)
    //химия,леrция,30,компьютеры,доска
    public void getSubjectsFromFile() {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                subjects.add(new Subject(parts[0],getTypeSubject(parts[1]), Integer.parseInt(parts[2]),
                        getEquipments(Arrays.copyOfRange(parts, 3, parts.length - 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ФИО, Наименование предмета, тип преподаваемого предмета(лекция// лекция, практическая работа)
    public void getTeachersFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                String FIO = parts[0];
                Subject subject = getSubjForTeacher(parts[1]);
                List<TypeSubject> typeOfTeaching = new ArrayList<>();
                for(int i = 2; i < parts.length; i++) {
                    typeOfTeaching.add(getTypeSubject(parts[i]));
                }
                Teacher teacher = new Teacher(FIO,subject,typeOfTeaching);
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Номер аудитории,вместимость,оборудование(его может быть много)
    public void getAudienceFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                audiences.add(new Audience(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),
                        getEquipments(Arrays.copyOfRange(parts, 2, parts.length - 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private TypeSubject getTypeSubject(String type){
         return switch (type){
            case "лекция" -> TypeSubject.LECTURE;
            case "практическая работа" -> TypeSubject.PRACTICE;
            case "лабораторная работа" -> TypeSubject.LABORATORY;
             default -> throw new IllegalStateException("Unexpected value: " + type);
         };
    }

    private List<Equipment> getEquipments(String[] parts){
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
    private Subject getSubjForTeacher(String subjectName) {
        for (Subject sub : subjects) {
            if (sub.getSubjectName().equals(subjectName)) {
                return sub;
            }
        }
        return null;
    }
}
