public class HW02_4108056018_5 extends ThreeSum {
	volatile static int ans;

	private static final void bucketSort(int[] nums) {
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

	public final int T_sum(int[] A) {
		final int threadCount = 12;
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		int negCounts = 0;
		int posCounts = 0;
		ans = 0;

		for (int num : A) {
			if (num < minVal)
				minVal = num;
			if (num > maxVal)
				maxVal = num;

			if (num > 0)
				posCounts++;
			else
				negCounts++;
		}

		if (minVal >= 0 || maxVal <= 0)
			return 0;

		final int[] negNumList = new int[negCounts];
		final int[] posNumList = new int[posCounts];
		int posStart = 0;

		if (maxVal + 2 * minVal > 0)
			maxVal = -2 * minVal;
		if (minVal + 2 * maxVal < 0)
			minVal = -2 * maxVal;

		final byte[] numMap = new byte[maxVal - minVal + 1];

		negCounts = 0;
		posCounts = 0;
		for (int num : A) {
			if (num >= minVal && num <= maxVal) {

				if (numMap[num - minVal]++ != 0) {

				} else {
					if (num > 0) {
						posNumList[posCounts++] = num;
					} else if (num < 0) {
						negNumList[negCounts++] = num;
					}
				}
			}
		}

		final int negNums = negCounts;
		final int posNums = posCounts;

		bucketSort(posNumList);
		bucketSort(negNumList);

		final int segement = negNums / threadCount;

		if (A.length >= threadCount) {
			class Worker extends Thread {
				int number;
				int MIN;

				public Worker(int i, int min_) {
					this.number = i;
					this.MIN = min_;
				}

				public void run() {
					int count = 0;
					int posStart = 0;
					final int start_ = (number == threadCount - 1) ? (negNums - 1) : ((number + 1) * segement - 1);
					final int end_ = (number == 0) ? 0 : (number * segement);
					for (int i = start_; i >= end_; i--) {
						final int negVal = negNumList[i];
						final int minposVal = (-negVal) / 2;

						while (posStart < posNums && posNumList[posStart] < minposVal)
							posStart++;

						for (int j = posStart; j < posNums; j++) {
							final int posVal = posNumList[j];
							final int ln = 0 - negVal - posVal;

							if (ln >= negVal && ln <= posVal) {

								if (numMap[ln - MIN] == 0) {
									continue;
								} else if (ln == posVal || ln == negVal) {

								} else {
									count++;
								}
							} else if (ln < negVal) {
								break;
							}
						}
					}

					ans += count;
				}
			}
			Worker[] workers = new Worker[threadCount];
			for (int i = 0; i < threadCount; i++) {
				workers[i] = new Worker(i, minVal);
				workers[i].start();
			}
			try {
				for (int i = 0; i < threadCount; i++) {
					workers[i].join();
				}
			} catch (InterruptedException e) {

			}
		} else {
			for (int i = negNums - 1; i >= 0; i--) {
				final int negVal = negNumList[i];
				final int minPosVal = (-negVal) / 2;

				while (posStart < posNums && posNumList[posStart] < minPosVal)
					posStart++;

				for (int j = posStart; j < posNums; j++) {
					final int posVal = posNumList[j];
					final int ln = 0 - negVal - posVal;

					if (ln >= negVal && ln <= posVal) {

						if (numMap[ln - minVal] == 0) {
							continue;
						} else if (ln == posVal || ln == negVal) {

						} else {
							ans++;
						}
					} else if (ln < negVal) {
						break;
					}
				}
			}
		}

		return ans;
	}
}
