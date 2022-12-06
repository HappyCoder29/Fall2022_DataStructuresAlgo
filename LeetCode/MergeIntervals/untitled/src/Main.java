import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
//https://leetcode.com/problems/merge-intervals/description/
public class Main {
    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(2,6));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(15,18));

        ArrayList<Interval> merged = mergeIntervals(intervals);

        System.out.println("Hello world!");
    }


    private static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals){
        if(intervals== null || intervals.size() <= 1){
            return intervals;
        }
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        Stack<Interval> stack = new Stack<>();
        stack.push(intervals.get(0));
        for(int i = 1; i < intervals.size(); i ++){
            Interval top = stack.peek();
            Interval current = intervals.get(i);
            if(top.end < current.start){
                // they dont intersect
                stack.push(current);
            }else if (top.end < current.end){
                top.end = current.end;
                stack.pop();
                stack.push(top);
            }
        }
        ArrayList<Interval> merged = new ArrayList<>();
        while(!stack.isEmpty()){
            merged.add(0, stack.pop());
        }

        return merged;
    }

}