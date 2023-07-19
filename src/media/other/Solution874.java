package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2023/7/19
 *
 * @author x.z
 */
public class Solution874 {
    /**
     * 预处理所有障碍物，按照横纵来区分
     * 按照 commands 指令逐一执行计算，保留最大欧式距离平方
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        final HashMap<Integer, List<Integer>> row = new HashMap<>();
        final HashMap<Integer, List<Integer>> col = new HashMap<>();
        for (int[] obstacle : obstacles) {
            row.computeIfAbsent(obstacle[1], var -> new ArrayList<>()).add(obstacle[0]);
            col.computeIfAbsent(obstacle[0], var -> new ArrayList<>()).add(obstacle[1]);
        }
        int res = 0;
        int[] pos = {0, 0};
        int dir = 0;
        boolean skip = false;
        for (int i = 0; i < commands.length; i++) {
            int command = commands[i];
            if(command < 0){
                if(command == -1){
//                    顺时针 90
                    dir += 90;
                }else{
//                    逆时针 90
                    dir -= 90;
                }
                dir %= 360;
                skip = false;
                continue;
            }
            if(skip){
                continue;
            }
            boolean flag = dir % 180 == 0;
            boolean go = (dir >= 0 && dir <= 90) || dir == -270;
            Map<Integer, List<Integer>> mayObs = flag ? col : row;
            int distance = checkObs(pos, dir, flag, command, mayObs);
            skip = distance != command;
            if(flag){
                pos[1] += go ? distance : distance * -1;
            }else{
                pos[0] += go ? distance : distance * -1;
            }
            res = (int) Math.max(res, Math.pow(pos[0], 2) + Math.pow(pos[1], 2));
        }
        return res;
    }

    private int checkObs(int[] pos, int dir, boolean flag, int move, Map<Integer, List<Integer>> mayObs) {
        if(mayObs.isEmpty()){
            return move;
        }
        int res = move;
        boolean go = (dir >= 0 && dir <= 90) || dir == -270;
        int check = flag ? pos[0] : pos[1];
        int checkPath = flag ? pos[1] : pos[0];
        if(mayObs.containsKey(check)){
            final List<Integer> list = mayObs.get(check);
            for (Integer item : list) {
                if(go){
                    if(item <= checkPath){
                        continue;
                    }
                    res = item - checkPath > move ? res : Math.min(item - checkPath - 1, res);
                }else{
                    if(item >= checkPath){
                        continue;
                    }
                    res = checkPath - item > move ? res : Math.min(checkPath - item - 1, res);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution874 demo = new Solution874();

//        示例
//        System.out.println(demo.robotSim(Common.string2IntArr("[4,-1,4,-2,4]"), Common.string2IntIntArr("[[2,4]]")));

//        case 45 错误
//        [-2,-1,-2,3,7]
//        [[1,-3],[2,-3],[4,0],[-2,5],[-5,2],[0,0],[4,-4],[-2,-5],[-1,-2],[0,2]]
        System.out.println(demo.robotSim(
                Common.string2IntArr("[-2,-1,-2,3,7]"),
                Common.string2IntIntArr("[[1,-3],[2,-3],[4,0],[-2,5],[-5,2],[0,0],[4,-4],[-2,-5],[-1,-2],[0,2]]")
        ));
    }
}
