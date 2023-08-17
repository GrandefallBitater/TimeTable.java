package kai.example.timeTable.services;

import kai.example.timeTable.entity.Audience;
import kai.example.timeTable.entity.Subject;
import kai.example.timeTable.entity.Teacher;
import kai.example.timeTable.enums.Equipment;
import lombok.Getter;

import java.io.*;
import java.util.*;

@Getter
public class DoSomeList {
    private final String urlAudience = "src/main/resources/files/audience.txt";
    private final String urlTeachers = "src/main/resources/files/teachers.txt";
    private final String urlSubjects = "src/main/resources/files/plan.txt";
    private final String splitBy = ",";
    private List<Subject> subjects;
    private List<Audience> audiences;
    private List<Teacher> teachers;

    //Название,всегоЧасов,часыЛекций,часыПрактик,часыЛаб,нужноеОборудование(его может быть много)
    //химия,40,20,10,10,компьютеры,доска
    public void getSubjectsFromFile() {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                subjects.add(new Subject(parts[0], Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                        getEquipments(Arrays.copyOfRange(parts, 5, parts.length - 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ФИО, Наименование предмета
    public void getTeachersFromFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(urlSubjects))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                teachers.add(new Teacher(parts[0], getSubjForTeacher(parts[1])));
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
