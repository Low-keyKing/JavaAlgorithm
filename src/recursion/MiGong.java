package recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建迷宫盘
        int[][] map = new int[8][7];
        //添加墙壁
        //竖直的墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //水平的墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //额外的墙
        map[3][1] = 1;
        map[3][2] = 1; 
        map[2][2] = 1;

        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }

        //寻找出路
        setWay(map, 1, 1);
        System.out.println("寻找出路后");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //寻路方法
    public static boolean setWay(int[][] map, int i, int j) {//map：地图 [i][j]起始点 如果找到出路就返回true，否则返回false
        //该点为1等于墙；该点为2表示已走过是通路；该点为3表示已走过，是死路
        //已经寻找到终点
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//该点尚未走过
                //将该点标为已走过
                map[i][j] = 2;
                //从该点向下走
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {//从该点向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//从该点向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//从该点向左走
                    return true;
                } else {//上下左右都走不通
                    map[i][j] = 3;
                }
            } else {//该点已经走过
                return false;
            }
        }
        return false;
    }
}
