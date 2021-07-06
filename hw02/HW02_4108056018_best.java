public class HW02_4108056018_1 extends ThreeSum {

	private void bucketSort(int[] nums) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int num : nums) {
			if (num < min) {
				min = num;
			}
			if (num > max) {
				max = num;
			}
		}

		final int[] arr = new int[max - min + 1];

		for (int num : nums) {
			arr[num - min]++;
		}

		int k = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i]; j++) {
				nums[k] = i + min;
				k++;
			}
		}
	}

	public int T_sum(int[] A) {
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		int negNums = 0;
		int posNums = 0;
		int ans = 0;

		for (int num : A) {
			if (num < minVal)
				minVal = num;
			if (num > maxVal)
				maxVal = num;

			if (num > 0)
				posNums++;
			else
				negNums++;
		}

		if (minVal >= 0 || maxVal <= 0)
			return 0;

		final int[] negNumList = new int[negNums];
		final int[] posNumList = new int[posNums];
		int posStart = 0;

		if (maxVal + 2 * minVal > 0)
			maxVal = -2 * minVal;
		if (minVal + 2 * maxVal < 0)
			minVal = -2 * maxVal;

		final byte[] numMap = new byte[maxVal - minVal + 1];

		negNums = 0;
		posNums = 0;
		for (int num : A) {
			if (num >= minVal && num <= maxVal) {
				numMap[num - minVal]++;
				if (num > 0) {
					posNumList[posNums++] = num;
				} else if (num < 0) {
					negNumList[negNums++] = num;
				}
			}
		}

		bucketSort(posNumList);
		bucketSort(negNumList);

		for (int i = negNums - 1; i >= 0; i--) {
			final int negVal = negNumList[i];
			final int minposVal = (-negVal) / 2;

			while (posStart < posNums && posNumList[posStart] < minposVal)
				posStart++;

			for (int j = posStart; j < posNums; j++) {
				final int posVal = posNumList[j];
				final int ln = 0 - negVal - posVal;

				if (ln > negVal && ln < posVal) {
					if (numMap[ln - minVal] == 1) {
						ans++;
					}
				} else if (ln < negVal) {
					break;
				}
			}
		}

		return ans;
	}
}
