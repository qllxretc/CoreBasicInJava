/**
 * 子串的定位操作通常称做模式匹配,其中子串称做模式串,主串称做目标串
 * <b>朴素的模式匹配算法</b>即是模式匹配的一种算法
 * 其优点是简单易懂,易于理解,某些应用场合效率较高
 * 缺点是需要多次回溯,对于数据较大的文本文件而言效率极低
 *
 */
public class PlainPatternSubstringQuery {

    /**
     * <b>分析时间复杂度</b>
     *
     * 最坏的时候,最后匹配成功,比如,0000000000001和00001,比较每次都在00001的1开始不匹配,指针回溯到开头,主串也回溯 i-j+1
     * 若模式子串的长度是m,目标串的长度是n,这时最坏的情况是每遍比较都在最后出现不等,即每遍最多比较m次,最多比较n-m+1遍,总的比较次数最多为m(n-m+1)
     * 因此朴素的模式匹配算法为 o(m*n),虽然,朴素的模式匹配,时间复杂度比较大,但是实际中,一般情况(除非模式串和主串之间存在很多的部分匹配的时候
     * 因为此时每遍需要比较的次数很多,相乘不能近似),真正的执行时间是近似于o(n+m)的,故当今仍然有他的用处!
     * @param mainString
     * @param subString
     * @return
     */
    public int subIndex(String mainString, String subString)
    {
        char[] mainChar = mainString.toCharArray();
        char[] subChar = subString.toCharArray();
        int mainJ = 0;
        int subJ = 0;
        while (mainJ < mainChar.length && subJ < subChar.length)
        {
            if(mainChar[mainJ] == subChar[subJ])
            {
                mainJ++;
                subJ++;
            }
            else
            {
                mainJ = mainJ - subJ + 1;//上次匹配的位置的下一个位置
                subJ = 0;
            }
        }
        if(subJ > subChar.length-1)
        {
            return mainJ-subJ;
        }
        else
        {
            return -1;
        }
    }

    /**
     * 朴素的模式匹配算法(过多回溯)
     *
     * @param args
     */
    public static void main(String[] args)
    {

        System.out.println(new PlainPatternSubstringQuery().subIndex("abababc", "abc"));
        System.out.println(new PlainPatternSubstringQuery().subIndex("abababc", "abcd"));
        System.out.println(new PlainPatternSubstringQuery().subIndex("abababc", "ab"));

    }
}
