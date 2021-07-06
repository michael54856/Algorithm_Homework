public class HW10_4108056018_1 extends SortingArray
{
	/*
	public static void main(String args[])
	{
		HW10_4108056018_1 test = new HW10_4108056018_1();
		int a[] = {-1,2,5,9,8,6,7,1,3,2};
		a = test.sorting(a);
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
	}
	*/
	public final int[] sorting(final int[] inputArr)
	{
		final int len =  inputArr.length;
		
		//default use bucket sort , if the input is too big , use quick sort
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		boolean canCount = true;
		for(int i = 0; i < len; i++)
		{
			if(inputArr[i] > 100000 || inputArr[i] < -100000)
			{
				canCount = false;
				break;
			}
			
			if (inputArr[i] < min) 
			{
				min = inputArr[i];
			}
			if (inputArr[i] > max) 
			{
				max = inputArr[i];
			}
		}
		
		
		if(canCount == false)
		{
			quickSort(inputArr, 0, len - 1);
		}
		else
		{
			final int[] arr = new int[max - min + 1];
			for(int i = 0; i < len; i++)
			{
				arr[inputArr[i] - min]++;
			}
			int k = 0;
			for (int i = 0; i < arr.length; i++) 
			{
				for (int j = 0; j < arr[i]; j++) 
				{
					inputArr[k++] = i + min;
				}
			}
			
		}
		
		return inputArr;
	}
	
	public static final void quickSort(int[] nums, int l, int r) 
	{
    	if (l >= r) return;
        int mid = partition(nums, l, r);
        quickSort(nums, l, mid-1);
        quickSort(nums, mid + 1, r);
    }
	
	 public static final int partition(int[] nums, int l, int r) 
	 {
		 final int tt = l + (((r-l)*3)>>2);
		 int pivot = nums[tt];
         int temp = nums[tt];
         nums[tt] = nums[l];
         nums[l] = temp;
         
	     while (l < r)
	     {
	         while (l < r && nums[r] >= pivot) 
	         {
	        	 r--;
	         } 
	         nums[l] = nums[r];
	         while (l < r && nums[l] <= pivot)
	         {
	        	 l++;
	         } 
	         nums[r] = nums[l];
	     }
	     nums[l] = pivot;
	     return l;
	 }
}