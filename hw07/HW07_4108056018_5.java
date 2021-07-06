public class HW07_4108056018_5 extends Buy_Phone 
{
	/*
	public static void main(String args[])
	{
		HW07_4108056018_1 test = new HW07_4108056018_1();
		int a[][] = {{1,111},{2,40},{2,10},{5,342},{4,8},{5,5},{8,4},{9,3},{9,2},{10,2},{10,1}};
		int ans[][] = test.bestPhone(a);
		for(int i = 0; i < ans.length; i++)
		{
			System.out.println(ans[i][0] + ":" + ans[i][1]);
		}
	}
	*/
	
	
	public static final void quickSort(int[][] inputArr, int l, int r) 
	{
		if (l >= r)
		{
			return;
		}
		int mid = partition(inputArr,l, r);
		quickSort(inputArr,l, mid-1);
		quickSort(inputArr,mid + 1, r);
	}

	public static final int partition(int[][] inputArr,int l, int r) 
	{
		final int tt = l + ((3*(r-l)) >> 2);
		int pivot[] = inputArr[tt];
        int temp[] = inputArr[tt];
        inputArr[tt] = inputArr[l];
        inputArr[l] = temp;

		while (l < r) 
		{
			while (l < r && inputArr[r][0] >= pivot[0]) 
			{
				r--;
			}
			inputArr[l] = inputArr[r];
			while (l < r && inputArr[l][0] <= pivot[0])
			{
				l++;
			}
			inputArr[r] = inputArr[l];
		}
		inputArr[l] = pivot;
		return l;
	}
	
	public final int[][] bestPhone(int[][] inputArr)
	{
		final int len = inputArr.length;
		quickSort(inputArr,0,len-1);
		
		int currentNumber = inputArr[len-1][0];
		int currentMax = inputArr[len-1][1];
		int rightMax = -2147483647;
		
		final int reverseAnsX[] = new int[len];
		final int reverseAnsY[] = new int[len];
		int ansCount = 0;
		
		for(int i = len-2; i >= 0; i--)
		{
			if(inputArr[i][0] != currentNumber)//find a new number
			{
				if(currentMax > rightMax)
				{
					reverseAnsX[ansCount] = currentNumber;
					reverseAnsY[ansCount++] = currentMax;
					rightMax = currentMax;
				}
				currentNumber = inputArr[i][0];
				currentMax = inputArr[i][1];
			}
			else//same number,compare their y
			{
				if(inputArr[i][1] > currentMax)
				{
					currentMax = inputArr[i][1];
				}
			}
		}
		//compare the last one
		if(currentMax > rightMax)
		{
			reverseAnsX[ansCount] = currentNumber;
			reverseAnsY[ansCount++] = currentMax;
		}
		
		final int ans[][] = new int[ansCount][2];
	
		
		for(int i = 0; i < ansCount; i++)
		{
			ans[i][0] = reverseAnsX[ansCount-1-i];
			ans[i][1] = reverseAnsY[ansCount-1-i];
		}
		
		return ans;
		
	}
}