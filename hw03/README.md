# 題目
Topic Description:

Input: An array. In this mission we  find the peak of the array, then find out which cutting position of the array which could produce a sorted array .

Final return: Cutting position of the array 
 
Please refer to the attached .jpg file to see the example

TestCase(input) will not include arrays like:

1.888888(All same)

2.1234567(whole ascending) .

![image](https://github.com/michael54856/Algorithm_Homework/blob/main/hw03/example1.png)

![image](https://github.com/michael54856/Algorithm_Homework/blob/main/hw03/example2.png)

# 解題
O(n)

最簡單就是linear search,如果找到數字降下來的位置即可

這題可用binary search來解決,但複雜度還是O(n),最差情況還是會到**n**,但average case可到**logn**

跑回圈時共有三種情況

* 右邊>中間 : 代表右邊那一端是升序,要縮減右邊範圍

* 中間>右邊 : 代表中間到右邊之間會有截斷處,要縮減左邊範圍

* 其他狀況(中間=右邊) : 無法確定中間到右邊之間是否有截斷,有可能是數字完全相同,也有可能有截斷,所以這邊讓右邊縮減一格就好



# 排名
![image](https://github.com/michael54856/Algorithm_Homework/blob/main/hw03/rank.png)

