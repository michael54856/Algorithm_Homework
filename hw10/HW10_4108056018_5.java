public class HW10_4108056018_5 extends SortingArray
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
		quickSort(inputArr, 0, inputArr.length - 1);
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
         final int tt = l + ((r-l)>>2);
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