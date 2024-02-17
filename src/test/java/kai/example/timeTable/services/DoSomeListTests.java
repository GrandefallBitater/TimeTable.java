package kai.example.timeTable.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;


public class DoSomeListTests {
    @TempDir(cleanup = CleanupMode.ALWAYS)
    static Path tempDir;
    static String urlGroups;
    static String urlAudiences;
    static String urlTeachers;
    static String urlSubjects;
    DoSomeList doSomeList = new DoSomeList(urlAudiences, urlTeachers, urlSubjects, urlGroups);

    @BeforeAll
    static void createTempDirUrl() {
        urlGroups = tempDir.toFile().getPath() + "/groups.txt";
        urlAudiences = tempDir.toFile().getPath() + "/audiences.txt";
        urlTeachers = tempDir.toFile().getPath() + "/teachers.txt";
        urlSubjects = tempDir.toFile().getPath() + "/plan.txt";
        fillFiles();
    }

    private static void fillFiles() {
        try {
            FileWriter writer = new FileWriter(urlGroups, false);
            // запись всей строки
            String text = """
                    4410,13,4
                    4411,15,4
                    4417,7,4""";
            writer.write(text);
            writer.flush();
            writer = new FileWriter(urlAudiences, false);
            text = """
                    337,55,доска,компьютеры
                    327,86,доска
                    333,40,доска
                    403,90,проектор""";
            writer.write(text);
            writer.flush();
            writer = new FileWriter(urlTeachers, false);
            text = """
                    Сотыч,ООП,лекция,лабораторная работа
                    Анисимова,Матан,лекция,лабораторная работа""";
            writer.write(text);
            writer.flush();
            writer = new FileWriter(urlSubjects, false);
            text = """
                    ООП,лекция,50,4,доска
                    ООП,лабораторная работа,50,4,доска,компьютеры
                    Матан,лекция,50,4,проектор
                    Матан,лабораторная работа,50,4,доска""";
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void makeAllListsTest() {
        doSomeList.makeAllLists();
        doSomeList.getGroups().forEach(System.out::println);
        doSomeList.getSubjects().forEach(System.out::println);
        doSomeList.getTeachers().forEach(System.out::println);
        doSomeList.getAudiences().forEach(System.out::println);
    }
}
