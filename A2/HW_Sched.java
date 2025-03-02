import java.util.*;

public class HW_Sched {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected HW_Sched(int[] weights, int[] deadlines, int size) {
        for (int i = 0; i < size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m = size;
    }

    public int[] SelectAssignments() {
        // Sort assignments by weight (descending), then by deadline (descending)
        Collections.sort(Assignments, new Assignment());

        // Initialize homeworkPlan with -1
        int[] homeworkPlan = new int[lastDeadline];
        Arrays.fill(homeworkPlan, -1);

        int[] available = new int[lastDeadline];
        for (int i = 0; i < lastDeadline; i++) {
            available[i] = i;
        }

        for (Assignment hw : Assignments) {
            int t = find(hw.deadline - 1, available);
            if (t != -1) {
                homeworkPlan[t] = hw.number;
                available[t] = find(t - 1, available);
            }
        }

        return homeworkPlan;
    }

    private int find(int t, int[] availableSlot) {
        if (t < 0) return -1;
        if (availableSlot[t] == t) return t;
        return availableSlot[t] = find(availableSlot[t], availableSlot);
    }
}
