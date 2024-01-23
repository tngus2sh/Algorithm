package Baekjoon.Tree;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 문자열집합 / 실버4 / 15분 / 24.01.23
 */

class TrieNode {
    HashMap<Character, TrieNode> child;
    boolean endOfWord;
    public TrieNode() {
        this.child = new HashMap<>();
        this.endOfWord = false;
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String str) {
        TrieNode node = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            node.child.putIfAbsent(c, new TrieNode());
            node = node.child.get(c);
        }
        node.endOfWord = true;
    }

    public boolean search(String str) {
        TrieNode node = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (node.child.containsKey(c)) {
                node = node.child.get(c);
            } else {
                return false;
            }
        }
        return node.endOfWord;
    }
}

public class BJ_14425_문자열집합 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            if (trie.search(br.readLine())) result++;
        }

        System.out.println(result);
    }
}

