/**
 * 字典树,又称为单词查找树,Tire树,是一种树形结构,它是一种哈希树的变种
 * @see <a href="https://images2015.cnblogs.com/blog/713721/201606/713721-20160624160557531-261335182.png">典型的Trie树</a>
 *
 * 基本性质
 *  1.每个节点的所有子节点包含的字符都不相同
 *  2.除根节点外的每一个子节点都包含一个字符
 * 应用场景
 *  1.典型应用是用于统计,排序和保存大量的字符串(不仅限于字符串),经常被搜索引擎系统用于文本词频统计
 * 优点
 *  1.利用字符串的公共前缀来减少查询时间,最大限度的减少无谓的字符串比较,查询效率比哈希树高
 *
 */
public class TrieTree {

    private static final int MAX_CHILD_SIZE = 26;
    private TreeNode rootNode;

    /**
     * 这里全是public是为了简化代码
     */
    public static class TreeNode{
        public char value;
        public int num;
        public TreeNode[] children;
        public boolean isLeaf;

        TreeNode(){
            num = 1;
            children = new TreeNode[MAX_CHILD_SIZE];
            isLeaf = false;
        }
    }

    TrieTree()
    {
        rootNode = new TreeNode();
    }

    /**
     * 假设全是小写字母
     * @param args
     */
    public static void main(String[] args)
    {
        String[] data = new String[]{
            "cat",
            "cash",
            "good"
        };
        TrieTree trieTree = new TrieTree();
        for (String item : data)
        {
            trieTree.insert(item);
        }

        trieTree.printTrieTreePretty(trieTree.rootNode());

        System.out.println();

        String stringPrefix = "ca";
        System.out.println(stringPrefix + " match word count is: " + trieTree.countPrefix(stringPrefix));

        System.out.println("-------给定一个词,判断是否为完全匹配-------");
        System.out.println(trieTree.completeMatch("good"));
        System.out.println(trieTree.completeMatch("cat"));
        System.out.println(trieTree.completeMatch("cash"));
        System.out.println(trieTree.completeMatch("god"));
        System.out.println(trieTree.completeMatch("ca"));
        System.out.println(trieTree.completeMatch("cas"));

    }

    /**
     * @param word
     * @return
     */
    public boolean completeMatch(String word){

        if(word == null || word.trim().length() == 0)
        {
            return false;
        }
        TreeNode treeNode = rootNode;
        char[] charArray = word.toCharArray();
        for (int k = 0; k<charArray.length; k++) {
            int index = charArray[k] - 'a';
            if (treeNode.children[index] == null) {
                return false;
            }
            else
            {
                treeNode = treeNode.children[index];
            }
        }
        return treeNode.isLeaf;
    }

    /**
     * @param prefix
     * @return
     */
    public int countPrefix(String prefix)
    {
        if(prefix == null || prefix.trim().length() == 0)
        {
            return 0;
        }
        char[] charArray = prefix.toCharArray();
        TreeNode treeNode = rootNode;

        for (int j = 0; j<charArray.length; j++)
        {
            int index = charArray[j]-'a';
            if(treeNode.children[index] == null)
            {
                return 0;
            }
            else
            {
                treeNode = treeNode.children[index];
            }
        }
        return treeNode.num;//匹配到路径前缀后,当前的node的num正好是前缀匹配个数
    }

    /**
     * @param content
     */
    public void insert(String content){
        if(content == null || content.trim().length() == 0)
        {
            return;
        }
        TreeNode treeNode = rootNode;
        char[] contentChar = content.toCharArray();

        for (int i = 0; i<contentChar.length; i++){
            int index = contentChar[i]-'a';
            if(treeNode.children[index] == null)
            {
                treeNode.children[index] = new TreeNode();
                treeNode.children[index].value = contentChar[i];
            }
            else
            {
                treeNode.children[index].num++;
            }
            treeNode = treeNode.children[index];
        }
        treeNode.isLeaf = true;
    }

    /**
     * @return
     */
    public TreeNode rootNode(){
        return rootNode;
    }

    /**
     * 前序遍历
     */
    public void printTrieTreePretty(TreeNode rootNode){
        if(rootNode != null)
        {
            System.out.print(rootNode.value + "|");
            for (TreeNode treeNode : rootNode.children){
                printTrieTreePretty(treeNode);
            }
        }
    }
}
