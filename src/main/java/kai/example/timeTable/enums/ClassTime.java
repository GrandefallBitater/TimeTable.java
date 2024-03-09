package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@AllArgsConstructor
public enum ClassTime {
    FIRST_CLASS(1, "8:00"), SECOND_CLASS(2, "9:40"),
    THIRD_CLASS(3, "11:20"), FOURTH_CLASS(4, "13:30"),
    FIFTH_CLASS(5, "15:10"), SIXTH_CLASS(6, "16:40");
    @Getter
    private final int sequence;
    private final String startTime;

    public static String getById(int id){
        return Arrays.stream(ClassTime.values()).filter(x->x.sequence==id).toList().get(0).startTime;
    }
    @Override
    public String toString() {
        return startTime;
    }

    public static class ClassTimeComparator implements Comparator<ClassTime> {
        @Override
        public int compare(ClassTime o1, ClassTime o2) {
            return o1.sequence - o2.sequence;
        }
    }
}
