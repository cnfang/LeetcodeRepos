package idv.cnfang.leetcode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;

/**
Leetcode <Problem 468> Validate IP Address

Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".


Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".


Example 3:
Input: "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.

====================== Solution ======================


*/
public class Problem468 {
    public String validIPAddress(String IP) {
        Pattern ipv4 = Pattern.compile("^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$");
        Matcher matcher4 = ipv4.matcher(IP);
        
        if (matcher4.find())
            return "IPv4";
        
        Pattern ipv6 = Pattern.compile("^(([0-9a-f]{1,4})\\:){7}[0-9a-f]{1,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher6 = ipv6.matcher(IP);
        
        return matcher6.find()? "IPv6": "Neither";
    }
    
    @Test
    public void example1() {
        String IP = "172.16.254.41";
        String expected = "IPv4";
        Problem468 sol = new Problem468();
        MatcherAssert.assertThat(sol.validIPAddress(IP), Matchers.is(expected));
    }
    
    @Test
    public void example2() {
        String IP = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        String expected = "IPv6";
        Problem468 sol = new Problem468();
        MatcherAssert.assertThat(sol.validIPAddress(IP), Matchers.is(expected));
    }
    
    @Test
    public void example3() {
        String IP = "256.256.256.256";
        String expected = "Neither";
        Problem468 sol = new Problem468();
        MatcherAssert.assertThat(sol.validIPAddress(IP), Matchers.is(expected));
    }
    
}