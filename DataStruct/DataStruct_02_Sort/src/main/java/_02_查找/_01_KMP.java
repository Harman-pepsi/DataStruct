package _02_查找;

/**
 * @Classname _01_KMP
 * @Description TODO
 * @Date 2020/11/21 22:24
 * @Created by XJC·AW
 */
public class _01_KMP {
    public static void main(String[] args) {
        String contant = "AAABBBCCCDDD";
        String dest = "CCC";
        System.out.println(KMPSearch(contant, dest, KMPNext(dest)));
    }

    private static int[] KMPNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    private static int KMPSearch(String contant, String dest, int[] next) {
        for (int i = 0, j = 0; i < contant.length(); i++) {
            while (j > 0 && contant.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (contant.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
