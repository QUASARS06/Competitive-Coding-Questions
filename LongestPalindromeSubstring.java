/**
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

Link: https://leetcode.com/problems/longest-palindromic-substring/

Author: Chirag Jain
*/

public class LongestPalindromeSubstring{

    //Checks whether given string is palindrome or not
    public boolean isPalindrome(String s){
        int len = s.length();
        int mid = (int)(len/2);    
        
        String firstHalf = s.substring(0,mid);
        String reverseFirst = new StringBuilder(firstHalf).reverse().toString();
        
        String secondHalf = s.substring(mid+1);
        if(len%2 == 0){
            secondHalf = s.substring(mid);
        }
        
        return secondHalf.equals(reverseFirst);
    }
    
    // Takes an input String s and two pointers which represent the center of the palindromic string
    // These two pointers move in opp direction until they point to different characters
    // In short they find the longest palindrome such that pt1,pt2 are the centers of that palindrome
    public String temp(String s,int pt1, int pt2){
        
        char ch1 = s.charAt(pt1);
        char ch2 = s.charAt(pt2);

        while(ch1 == ch2){
            pt1-=1;
            pt2+=1;

            if(pt1 >=0 && pt2 < s.length()){
                ch1 = s.charAt(pt1);
                ch2 = s.charAt(pt2);    
            } else{
                break;
            }                
        }
        return s.substring(pt1+1, pt2);
    }
    
    public String longestPalindrome(String s) {

        // Base Conditions
        if(s.length() == 0 || s.length() == 1) return s;
        else if(isPalindrome(s)) return s;
        else if(s.length() == 2 && (s.charAt(0) == s.charAt(1))) return s;
        else if(s.length() == 2 && (s.charAt(0) != s.charAt(1))) return s.substring(0,1);
        
        String ans = s.substring(0,1);
        for(int i=1;i<s.length()-1;i++){
            
            String t = "";
            
            //Find the prev curr and next character 
            char curr = s.charAt(i);
            char prev = s.charAt(i-1);
            char next = s.charAt(i+1);
            
            int pt1 = -1;
            int pt2 = -1;
            
            
            //The logic is that for the string to be a palindrome we have to find the midpoint of that palindrome
            //Which is possible if:
            //Even Palindrome the middle two characters are same Eg : a b aa b a
            //Odd Palindrome the characters on either side of middle element are same Eg : a b 'a' 1 'a' b a
            //The if conditions below checks these conditions and if they are true they find the max len palindrome
            //Such that the given index acts as midpoint of that palindrome
            //Each condition is checked and then the returned palindrome is checked for maximization

            if(prev == next){
                pt1 = i-1;
                pt2 = i+1;
                
                t = temp(s,pt1,pt2);
                if(t.length() >= ans.length()){
                    ans = t;
                }
            }
            
            if(curr == prev){
                pt1 = i-1;
                pt2 = i;
                
                t = temp(s,pt1,pt2);
                if(t.length() >= ans.length()){
                    ans = t;
                }
            }
            
            if(curr == next){
                pt1 = i;
                pt2 = i+1;
                
                t = temp(s,pt1,pt2);
                if(t.length() >= ans.length()){
                    ans = t;
                }
            }  
            
        }
        
        return ans;
        
    }


    public static void main(String[] args) {
        LongestPalindromeSubstring p = new LongestPalindromeSubstring();
        String str = "abbccbbaeffa";
        System.out.println(p.longestPalindrome(str));
    }
}
    