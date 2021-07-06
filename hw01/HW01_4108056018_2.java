public class HW01_4108056018_2 extends ArrayData {
	
	int maxVal;
	int minVal;
	public HW01_4108056018_2(int[] A)
	{
		maxVal = A[0];
		minVal = A[0];
		for(int i = 1, len = A.length; i != len; i++)
		{
			if(A[i] > maxVal)
			{
				maxVal = A[i];
			}
			if(A[i] < minVal)
			{
				minVal = A[i];
			}
		}
	}
	
	//override max()
	public int max()
	{
		return maxVal;
	}
	
	//override min()
	public int min()
	{
		return minVal;
	}
	
}