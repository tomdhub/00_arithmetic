package Recursion;

/**
 * @author tomable
 * @create 2021-08-31-19:01
 */
public class MiGong {
    public static void main(String[] args) {
        //地图，二维数组模拟
        int [][] map = new int[8][7];
        //1：墙面
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        } for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
        map[2][2] = 1;

        //显示地图
        System.out.println("地图情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯找路
        setWay(map, 1, 1);
        //显示新地图
        System.out.println("新地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param map 地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 找到返回true，否则返回false
     * 0：没走过，2通路可以走，3：走过不通
     * 策略：下 》 右 》 上 》 左
     */
    public static boolean setWay(int[][] map, int i, int j){
        if (map[6][5] == 2){ //通关
            return true;
        }else {
            if (map[i][j] == 0){ //当前点没有走过，执行策略
                map[i][j] = 2; //假定可以走通
                if (setWay(map, i + 1, j)){ //下
                    return true;
                } else if (setWay(map, i, j + 1)){ //右
                    return true;
                } else if (setWay(map, i - 1, j)){ //上
                    return true;
                } else if (setWay(map, i, j - 1)){ //左
                    return true;
                } else { //走不通
                    map[i][j] = 3;
                    return false;
                }
            } else { //可能为1，2，3
                return false;
            }
        }
    }

}
