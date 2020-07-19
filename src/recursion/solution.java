package recursion;

//剑指offer
//矩阵中的路径
/*
* 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
* 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
* 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
* 例如
* a b c e
* s f c s
* a d e e
*矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
* 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
* */
public class solution {
    public static void main(String[] args) {
        char[] matrix = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
        int rows = 3;
        int cols = 4;
        boolean i = hasPath(matrix, rows, cols, str);
        System.out.println(i);
    }

    public static int[][] visit;

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        visit = new int[rows][cols];
        char[][] string = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                string[i][j] = matrix[i * cols + j];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (setWay(string, rows, cols, str, i, j, 0)) {
                    return true;
                }

            }
        }
        return false;
    }

    public static boolean setWay(char[][] string, int rows, int cols, char[] str, int i, int j, int k) {
        if (k >= str.length) {
            return true;
        }

        if (i < 0 || i >= rows || j < 0 || j >= cols || string[i][j] != str[k] || visit[i][j] == 1) {
            return false;
        }

        visit[i][j] = 1;
        boolean flag =
                setWay(string, rows, cols, str, i - 1, j, k + 1) ||
                setWay(string, rows, cols, str, i + 1, j, k + 1) ||
                setWay(string, rows, cols, str, i, j - 1, k + 1) ||
                setWay(string, rows, cols, str, i, j + 1, k + 1);

        visit[i][j] = 0;

        return flag;
    }

}
