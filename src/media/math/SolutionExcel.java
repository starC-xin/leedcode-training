package media.math;

public class SolutionExcel {

    public static void main(String[] args){
        String str = "AB";
        SolutionExcel temp = new SolutionExcel();
        System.out.println(temp.titleToNumber(str));
    }

    public int titleToNumber(String s) {
        char baseCh = 'A';
        int baseInt = 26;
        char[] arr = s.toCharArray();
        int length = arr.length;
        int result = 0;
        for (char ch : arr){
            result += Math.pow(baseInt, -- length) * (ch - baseCh + 1);
        }
        return result;
    }
}
