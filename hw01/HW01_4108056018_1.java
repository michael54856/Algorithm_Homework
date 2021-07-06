public class HW01_4108056018_1 extends ArrayData {

	public HW01_4108056018_1(int[] A)
	{
		this.A = A;
	}
	
	//override max()
	public int max()
	{
		int m = A[0];
		for(int i = 1, len = A.length; i != len; i++)
		{
			if(A[i] > m)
			{
				m = A[i];
			}
		}
		return m;
	}
	
	//override min()
	public int min()
	{
		int m = A[0];
		for(int i = 1, len = A.length; i != len; i++)
		{
			if(A[i] < m)
			{
				m = A[i];
			}
		}
		return m;
	}
	
}