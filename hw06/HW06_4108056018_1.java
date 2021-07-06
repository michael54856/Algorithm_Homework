public class HW06_4108056018_1 extends Dessert_Desert {
	public int[] maxBlocks(int[][] inputArr) {

		final int n = inputArr.length;// n test case
		final int ans[] = new int[n];// n ans
		
		int maxWidth = 1;
		int max_from_Left[] = new int[1];
		int min_from_right[] = new int[1];
		for (int i = 0; i < n; i++) 
		{
			final int len = inputArr[i].length;
			if(len > maxWidth)//the length is more than previous,so we need to have a longer one
			{
				maxWidth = len;
				max_from_Left = new int[len];
				min_from_right = new int[len];
			}

			// record the maximum of the right sub array
			int myMax = inputArr[i][0];
			max_from_Left[0] = inputArr[i][0];

			int myMin = inputArr[i][len - 1];
			min_from_right[len - 1] = inputArr[i][len - 1];

			for (int j = 1; j < len; j++) {
				if (inputArr[i][j] > myMax) {
					myMax = inputArr[i][j];
				}
				max_from_Left[j] = myMax;
			}

			int count = 1;

			for (int j = len - 2; j >= 0; j--) {
				// record the minimum of the left sub array
				if (inputArr[i][j] < myMin) {
					myMin = inputArr[i][j];
				}
				min_from_right[j] = myMin;
				if (max_from_Left[j] <= min_from_right[j + 1]) {
					count++;
				}
			}
			ans[i] = count;
		}
		return ans;
	}
}