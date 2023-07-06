package media.other;

/**
 * 2023/6/25
 *
 * @author x.z
 */
public class Solution1401 {
    /**
     * 先看四个点是否在圆内
     * 否，则找到矩形任意相邻点是否在圆心两边
     * 是，则计算圆心到该边的距离
     * 否，则返回 false
     */
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        final boolean xFlag = xCenter >= x1 && xCenter <= x2;
        final boolean yFlag = yCenter >= y1 && yCenter <= y2;
        if(xFlag && yFlag){
            return true;
        }

        if (radius >= distance0(xCenter, yCenter, x1, y1) ||
                radius >= distance0(xCenter, yCenter, x2, y2) ||
                radius >= distance0(xCenter, yCenter, x1, y2) ||
                radius >= distance0(xCenter, yCenter, x2, y1)) {
            return true;
        }

        if(xFlag){
            return (yCenter - radius <= y2 && yCenter >= y2) || (yCenter + radius >= y1 && yCenter <= y1);
//            因为半径可能大于矩形，所以不可以使用区间判定，而是使用是否在边的两侧
//            return (bottom <= y2 && bottom >= y1) || (top >= y1 && top <= y2);
        }
        if(yFlag){
            return (xCenter >= x2 && xCenter - radius <= x2) || (xCenter + radius >= x1 && xCenter <= x1);
//            因为半径可能大于矩形，所以不可以使用区间判定，而是使用是否在边的两侧
//            return (right >= x1 && right <= x2) || (left >= x1 && left <= x2);
        }

        return false;
    }

    private double distance0(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * 我倒要看看官解怎么解这个case
     */
    public boolean checkOverlapAnswer(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        /* 圆心在矩形内部 */
        if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        /* 圆心在矩形上部 */
        if (x1 <= xCenter && xCenter <= x2 && y2 <= yCenter && yCenter <= y2 + radius) {
            return true;
        }
        /* 圆心在矩形下部 */
        if (x1 <= xCenter && xCenter <= x2 && y1 - radius <= yCenter && yCenter <= y1) {
            return true;
        }
        /* 圆心在矩形左部 */
        if (x1 - radius <= xCenter && xCenter <= x1 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        /* 圆心在矩形右部 */
        if (x2 <= xCenter && xCenter <= x2 + radius && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        /* 矩形左上角 */
        if (distance(xCenter, yCenter, x1, y2) <= radius * radius)  {
            return true;
        }
        /* 矩形左下角 */
        if (distance(xCenter, yCenter, x1, y1) <= radius * radius) {
            return true;
        }
        /* 矩形右上角 */
        if (distance(xCenter, yCenter, x2, y2) <= radius * radius) {
            return true;
        }
        /* 矩形右下角 */
        if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
            return true;
        }
        /* 无交点 */
        return false;
    }

    public long distance(int ux, int uy, int vx, int vy) {
        return (long)Math.pow(ux - vx, 2) + (long)Math.pow(uy - vy, 2);
    }

    public static void main(String[] args) {
        final Solution1401 demo = new Solution1401();
//        1206
//                -5597
//                -276
//                -5203
//                -1795
//                -4648
//        1721

        System.out.println(demo.checkOverlap(1206, -5597, -276, -5203, -1795, -4648, 1721));
    }
}
