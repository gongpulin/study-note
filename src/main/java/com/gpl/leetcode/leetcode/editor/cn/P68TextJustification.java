//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。 
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。 
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。 
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。 
//
// 说明: 
//
// 
// 单词是指由非空格字符组成的字符序列。 
// 每个单词的长度大于 0，小于等于 maxWidth。 
// 输入单词数组 words 至少包含一个单词。 
// 
//
// 示例: 
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
// 
//
// 示例 2: 
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。       
//     第二行同样为左对齐，这是因为这行只包含一个单词。
// 
//
//
//]示例 3:
////
//// 输入:
////words = ["Science","is","what","we","understand","well","enough","to","explain
////",
////         "to","a","computer.","Art","is","everything","else","we","do"]
////maxWidth = 20
////输出:
////[
////  "Science  is  what we",
////  "understand      well",
////  "enough to explain to",
////  "a  computer.  Art is",
////  "everything  else  we",
////  "do                  "
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：文本左右对齐
public class P68TextJustification{
    public static void main(String[] args) {
        Solution solution = new P68TextJustification().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        String[] words;
        int maxWidth;

        List<String> res =new ArrayList<>();

        public List<String> fullJustify(String[] words, int maxWidth) {

            this.words=words;
            this.maxWidth = maxWidth;
            dfs(0);
            return res;
        }

        private void dfs(int index){

            if (index==words.length)
                return;

            int length = 0;
            int count = 0;
            int i;
            for (i=index;i<words.length;i++){
                if (length+words[i].length()+count<=maxWidth){
                    count++;
                    length+=words[i].length();
                }
                else
                    break;
            }
            res.add(construct(index,count,length));
            dfs(i);

        }

        private String construct(int index, int count, int length){

            StringBuilder stringBuilder = new StringBuilder();

            if (index+count>=words.length){ // 0 1 2 3 4 5
                for (int i=0;i<count;i++){
                    stringBuilder.append(words[index+i]);
                    if (i!=count-1)
                        stringBuilder.append(" ");
                }
                while (stringBuilder.length()<maxWidth)
                    stringBuilder.append(" ");
                return new String(stringBuilder);
            }

            if (count==1){
                stringBuilder.append(words[index]);
                while (stringBuilder.length()<maxWidth) {
                    stringBuilder.append(" ");
                }
                return new String(stringBuilder);
            }

            int space = maxWidth-length;
            if (space==count-1){
                for (int i=0;i<count;i++){
                    stringBuilder.append(words[index+i]);
                    if (i!=count-1) {
                        stringBuilder.append(" ");
                    }
                }
            }
            else {
                int extra = space%(count-1);
                int per = space/(count-1);
                for (int i=0;i<count;i++){
                    stringBuilder.append(words[index+i]);
                    if (i<extra) {
                        stringBuilder.append(" ");
                    }
                    if (i!=count-1){
                        for (int j=0;j<per;j++) {
                            stringBuilder.append(" ");
                        }

                    }
                }
            }
            return new String(stringBuilder);
        }








    public List<String> fullJustify1(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        if (words == null) {
            return ans;
        }
        int len = words.length;
        int  i = 0, rowLen = 0, wordNum = 0;
        List<String> row = new ArrayList<>();
        while (i < len) {
            if (rowLen + words[i].length() < maxWidth) {
                row.add(words[i]);
                i++;
                wordNum++;
            } else {
                String s = buildRow(row, wordNum, rowLen, maxWidth);
                ans.add(s);
                row = new ArrayList<>();
                rowLen = 0;
                wordNum = 0;
            }
        }
        return ans;
    }
    private String buildRow(List<String> row, int wordNum, int rowLen, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int spaceTotalNum = maxWidth - rowLen;
        int spaceNum = spaceTotalNum / (wordNum - 1);
        for (int i = 0; i < row.size(); i++) {
            sb.append(row.get(i));
            if (spaceTotalNum > spaceNum) {
                appendSpace(sb, spaceNum);
                spaceTotalNum -= spaceNum;
            } else {
                appendSpace(sb, spaceTotalNum);
                spaceTotalNum = 0;
            }
        }
        return sb.toString();
    }
    private void appendSpace(StringBuilder sb, int spaceNum) {
        for (int i = 0; i < spaceNum; i++) {
            sb.append(" ");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}