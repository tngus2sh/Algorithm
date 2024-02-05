package Baekjoon.Tree;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 휴대폰자판 / 플레4 / 2시간 / 24.02.05
 */

public class BJ_5670_휴대폰자판 {

    static class TrieNode {
        HashMap<Character, TrieNode> child;
        boolean endOfWord;
        public TrieNode() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }


    }

    static class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode();
        }

        public void insert (String str) {
            TrieNode node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                node.child.putIfAbsent(c, new TrieNode());
                node = node.child.get(c);
            }
            node.endOfWord = true;
        }

        public int search(String str) {
            TrieNode node = this.root.child.get(str.charAt(0));

            int click = 1;

            for (int i = 1; i < str.length(); i++) {
                char c = str.charAt(i);

                if (node.child.containsKey(c)) {
                    if (node.child.size() > 1) {
                        click++;
                    }
                    else if (node.endOfWord) click++;
                    node = node.child.get(c);
                }else {
                    return 0;
                }
            }
            return click;
        }
    }

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = br.readLine()) != null) {

                int N = Integer.parseInt(str);

                Trie trie = new Trie();
                String[] text = new String[N];
                for (int i = 0; i < N; i++) {
                    text[i] = br.readLine();
                    trie.insert(text[i]);
                }

                double answer = 0;
                for (int i = 0; i < text.length; i++) {
                    answer += trie.search(text[i]);
                }
                String result = String.format("%.2f", answer/text.length);
                sb.append(result).append("\n");
            }
            System.out.print(sb.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
