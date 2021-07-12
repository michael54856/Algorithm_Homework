# 題目
Your program will take a bunch of string arrays as input.
Please determine whether a given array comes from {0^k1^k | k is Z+ }
( the input arrays do not contain empty string and non 0,1 characters)

For example,
        1.Input: ["0011",”00111” ] , Output: [true,false]
        2.Input: ["01",”1100” ,”1111”] , Output: [true,false,false]


# 解題
O(n)

這題應該沒什麼特殊解法,要看前50%是不是都是0,後50%是不是都是1

頂多一開始可以先把長度為奇數的string直接排除掉



# 排名
![image](https://github.com/michael54856/Algorithm_Homework/blob/main/hw04/rank.png)

