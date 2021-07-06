public class HW08_4108056018_2 extends Buy_Phone_v2 
{
	//first brutal, then mergeSort
	
	/*
	public static void main(String args[])
	{
		HW08_4108056018_1 test = new HW08_4108056018_1();
		int a[][] = {{8,7,7,4,2,1},{2,4,4,6,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9}};
		int ans[][] = test.bestPhone(a);
		
		for(int i = 0; i < ans.length; i++)
		{
			System.out.println(ans[i][0] + ":" + ans[i][1] + ":" + ans[i][2] + ":" + ans[i][3] + ":" + ans[i][4] + ":" + ans[i][5]);
		}
		
	}
	*/
	
	
	static final int tempArr[][] = new int[100000][6];
	public static final void mergeSort(final int[][] inputArr, int front, int end)
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
		final int ans[][] = new int[len][6];
		int ansCount = 0;
		for(int i = 0; i < len; i++)
		{
			if(inputArr[i] == null)
			{
				continue;
			}
			boolean canPutIn = true;
			for(int j = i+1; j < len; j++)
			{
				if(inputArr[j] == null)
				{
					continue;
				}
				if(inputArr[j][0] >= inputArr[i][0] && inputArr[j][1] >= inputArr[i][1] && inputArr[j][2] >= inputArr[i][2] && inputArr[j][3] >= inputArr[i][3] && inputArr[j][4] >= inputArr[i][4] && inputArr[j][5] >= inputArr[i][5])//find a [j] element all more than i
				{
					canPutIn = false;
					break;
				}
				if(inputArr[j][0] <= inputArr[i][0] && inputArr[j][1] <= inputArr[i][1] && inputArr[j][2] <= inputArr[i][2] && inputArr[j][3] <= inputArr[i][3] && inputArr[j][4] <= inputArr[i][4] && inputArr[j][5] <= inputArr[i][5])//[i] is all more than [j]
				{
					inputArr[j] = null;
				}
			}
			if(canPutIn == true)
			{
				ans[ansCount++] = inputArr[i];
			}
		}
		final int finalAns[][] = new int[ansCount][6];
		System.arraycopy(ans, 0, finalAns, 0, ansCount);
		mergeSort(finalAns, 0, ansCount-1);
		return finalAns;
		
	}
}