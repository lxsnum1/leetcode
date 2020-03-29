package p208;

import java.util.HashMap;
import java.util.Map;

//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树

public class ImplementTriePrefixTree {

    public static void main(String[] args) {
        Trie trie = new ImplementTriePrefixTree().new Trie();
    }

    class Trie {
        private TrieNode root;

        private class TrieNode {
            // only for a-z
            private static final int ALPHABET_SIZE = 26;
            boolean isKey;
            TrieNode[] children;

            TrieNode() {
                children = new TrieNode[ALPHABET_SIZE];
            }

            private void put(char c, TrieNode newNode) {
                children[c - 'a'] = newNode;
            }

            private boolean containsKey(char c) {
                return children[c - 'a'] != null;
            }

            private TrieNode get(char c) {
                return children[c - 'a'];
            }

            private void setKey() {
                isKey = true;
            }

            private boolean isKey() {
                return isKey;
            }
        }

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null) {
                return;
            }

            TrieNode currNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (currNode.containsKey(word.charAt(i))) {
                    currNode = currNode.get(word.charAt(i));
                } else {
                    TrieNode newNode = new TrieNode();
                    currNode.put(word.charAt(i), newNode);
                    currNode = newNode;
                }
            }
            currNode.setKey();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if (word == null || root == null) {
                return false;
            }

            TrieNode currNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (!currNode.containsKey(word.charAt(i))) {
                    return false;
                } else {
                    currNode = currNode.get(word.charAt(i));
                }
            }
            return currNode.isKey();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || root == null) {
                return false;
            }

            TrieNode currNode = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (!currNode.containsKey(prefix.charAt(i))) {
                    return false;
                } else {
                    currNode = currNode.get(prefix.charAt(i));
                }
            }
            return true;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */
