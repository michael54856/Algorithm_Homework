public class HW07_4108056018_1 extends Buy_Phone 
{
	/*
	public static void main(String args[])
	{
		HW07_4108056018_6 test = new HW07_4108056018_6();
		int a[][] = {{1,100},{2,234},{2,10},{5,40},{4,80},{5,5},{8,4},{10,2},{10,100}};
		int ans[][] = test.bestPhone(a);
		for(int i = 0; i < ans.length; i++)
		{
			System.out.println(ans[i][0] + ":" + ans[i][1]);
		}
	}
	*/
	
	
	static final int tempArr[][] = new int[100000][2];
	
	public static final void mergeSort(int[][] inputArr, int front, int end)
	{
		if (front < end) // if end == front , we dont need to sort
		{
			final int mid = (front + end) >> 1;
			mergeSort(inputArr, front, mid);
			mergeSort(inputArr, mid + 1, end);
			// merge:
			for (int i = front; i <= end; i++)// copy array from nodeArr to tempArr
			{
				tempArr[i] = inputArr[i];
			}
			int i = front;
			int start_1 = front;
			int start_2 = mid + 1;
			while (start_1 <= mid && start_2 <= end) {
				inputArr[i++] = tempArr[start_1][0] <= tempArr[start_2][0] ? tempArr[start_1++] : tempArr[start_2++];
			}
			while (start_1 <= mid) {
				inputArr[i++] = tempArr[start_1++];
			}
			while (start_2 <= end) {
				inputArr[i++] = tempArr[start_2++];
			}
		}

	}
	
	public final int[][] bestPhone(int[][] inputArr)
	{
		final int len = inputArr.length;
		mergeSort(inputArr,0,len-1);
		
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