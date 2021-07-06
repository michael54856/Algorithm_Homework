public class HW08_4108056018_4 extends Buy_Phone_v2 
{
	//first mergesot and count answer(this is wrong strategy)
	
	static final int tempArr[][] = new int[10000][6];
	public static final void mergeSort(final int[][] inputArr,final int front,final int end)
	{
		if (front < end) // if end == front , we dont need to sort
		{
			final int mid = (front + end) >> 1;
			mergeSort(inputArr, front, mid);
			mergeSort(inputArr, mid + 1, end);
			// merge:
			System.arraycopy(inputArr, front, tempArr, front, end-front+1);
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
	
	
	public final int[][] bestPhone(final int[][] inputArr)
	{
		final int len = inputArr.length;
		mergeSort(inputArr,0,len-1);

		int max1 = -1;
		int max2 = -1;
		int max3 = -1;
		int max4 = -1;
		int max5 = -1;
		
		int ansCount = 0;
		final int reverseAns0[] = new int[len];
		final int reverseAns1[] = new int[len];
		final int reverseAns2[] = new int[len];
		final int reverseAns3[] = new int[len];
		final int reverseAns4[] = new int[len];
		final int reverseAns5[] = new int[len];
		
		for(int i = len-1; i != -1; i--)
		{
			boolean canPutIn = false;
			if(inputArr[i][1] > max1)
			{
				max1 = inputArr[i][1];
				canPutIn = true;
			}
			if(inputArr[i][2] > max2)
			{
				max2 = inputArr[i][2];
				canPutIn = true;
			}
			if(inputArr[i][3] > max3)
			{
				max3 = inputArr[i][3];
				canPutIn = true;
			}
			if(inputArr[i][4] > max4)
			{
				max4 = inputArr[i][4];
				canPutIn = true;
			}
			if(inputArr[i][5] > max5)
			{
				max5 = inputArr[i][5];
				canPutIn = true;
			}
			
			if(canPutIn == true)
			{
				reverseAns0[ansCount] = inputArr[i][0];
				reverseAns1[ansCount] = inputArr[i][1];
				reverseAns2[ansCount] = inputArr[i][2];
				reverseAns3[ansCount] = inputArr[i][3];
				reverseAns4[ansCount] = inputArr[i][4];
				reverseAns5[ansCount++] = inputArr[i][5];
			}
		}
		final int finalAns[][] = new int[ansCount][6];
		final int bound = ansCount-1;
		for(int i = ansCount-1; i != -1; i--)
		{
			finalAns[i][0] = reverseAns0[bound-i];
			finalAns[i][1] = reverseAns1[bound-i];
			finalAns[i][2] = reverseAns2[bound-i];
			finalAns[i][3] = reverseAns3[bound-i];
			finalAns[i][4] = reverseAns4[bound-i];
			finalAns[i][5] = reverseAns5[bound-i];
		}
		
		return finalAns;
		
	}
}