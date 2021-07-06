public class HW06_4108056018_5 extends Dessert_Desert 
{
	public int[] maxBlocks(int[][] inputArr) 
	{
		final int n = inputArr.length;// n test case
		final int ans[] = new int[n];// n ans
		
		final int threadCount = 2;

		if(n >= threadCount)
		{
			final int segement = n / threadCount;
			class Worker extends Thread 
			{
				int number;
				public Worker(int i) {
					this.number = i;
				}
				public void run() 
				{

					final int start = number * segement;
					final int end = (number == threadCount - 1) ? n - 1 : (start + segement - 1);
					
					int maxWidth = 1;
					for (int i = start; i <= end; i++) 
					{
						final int len = inputArr[i].length;
						if(len > maxWidth)
						{
							maxWidth = len;
						}
					}
					final int max_from_Left[] = new int[maxWidth];
					final int min_from_right[] = new int[maxWidth];
					
					for (int i = start; i <= end; i++) 
					{
						final int len = inputArr[i].length;

						// record the maximum of the right sub array
						max_from_Left[0] = inputArr[i][0];

						min_from_right[len - 1] = inputArr[i][len - 1];

						for (int j = 1; j < len; j++) {
							max_from_Left[j] = (inputArr[i][j] > max_from_Left[j-1]) ? inputArr[i][j] : max_from_Left[j-1];
						}

						int count = 1;

						for (int j = len - 2; j >= 0; j--) {
							// record the minimum of the left sub array
							min_from_right[j] = (inputArr[i][j] < min_from_right[j+1]) ? inputArr[i][j] : min_from_right[j+1];
							if (max_from_Left[j] <= min_from_right[j + 1]) {
								count++;
							}
						}
						ans[i] = count;
					}
				}
			}
			
			Worker[] workers = new Worker[threadCount];
			for (int i = 0; i < threadCount; i++) {
				workers[i] = new Worker(i);
				workers[i].start();
			}
			try {
				for (int i = 0; i < threadCount; i++) {
					workers[i].join();
				}
			} catch (InterruptedException e) {

			}
			return ans;
		}
		else 
		{
			int maxWidth = 1;
			for (int i = 0; i < n; i++) 
			{
				final int len = inputArr[i].length;
				if(len > maxWidth)
				{
					maxWidth = len;
				}
			}
			final int max_from_Left[] = new int[maxWidth];
			final int min_from_right[] = new int[maxWidth];

			for (int i = 0; i < n; i++) 
			{
				final int len = inputArr[i].length;

				// record the maximum of the right sub array
				max_from_Left[0] = inputArr[i][0];

				min_from_right[len - 1] = inputArr[i][len - 1];

				for (int j = 1; j < len; j++) {
					max_from_Left[j] = (inputArr[i][j] > max_from_Left[j-1]) ? inputArr[i][j] : max_from_Left[j-1];
				}

				int count = 1;

				for (int j = len - 2; j >= 0; j--) {
					// record the minimum of the left sub array
					min_from_right[j] = (inputArr[i][j] < min_from_right[j+1]) ? inputArr[i][j] : min_from_right[j+1];
					if (max_from_Left[j] <= min_from_right[j + 1]) {
						count++;
					}
				}
				ans[i] = count;
			}
			return ans;
		}

	}
}