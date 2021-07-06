public class HW02_4108056018_4 extends ThreeSum {

	public int T_sum(int[] A) {
		if (A.length < 3) {
			return 0;
		}

		boolean haveZero = false;
		int positiveCount = 0;
		int negativeCount = 0;
		int min = 2147483647;
		int max = -2147483647;
		for (int i = 0, len = A.length; i < len; i++) {
			if (A[i] < min) {
				min = A[i];
			}
			if (A[i] > max) {
				max = A[i];
			}
			if (A[i] == 0) {
				haveZero = true;
			}
			if (A[i] > 0) {
				positiveCount++;
			}
			if (A[i] < 0) {
				negativeCount++;
			}
		}
		if (max <= 0 || min >= 0) {
			return 0;
		}
		int x = 0;
		int y = 0;
		boolean positiveHash[] = new boolean[max + 1];
		boolean negativeHash[] = new boolean[(-min) + 1];
		int positiveList[] = new int[positiveCount];
		int negativeList[] = new int[negativeCount];
		for (int i = 0, len = A.length; i < len; i++) {
			if (A[i] > 0) {
				positiveHash[A[i]] = true;
				positiveList[x] = A[i];
				x++;
			}
			if (A[i] < 0) {
				negativeHash[-A[i]] = true;
				negativeList[y] = A[i];
				y++;
			}
		}

		int ans_1 = 0;
		int ans_2 = 0;
		int zeroAns = 0;

		if (negativeCount < positiveCount) {
			if (negativeCount >= 1000) {
				int quarter = negativeCount / 4;
				Split_Negative AA = new Split_Negative(0, quarter, haveZero, positiveHash, negativeList, positiveList,
						negativeCount, positiveCount, max);
				Split_Negative BB = new Split_Negative(quarter + 1, (2 * quarter) + 1, haveZero, positiveHash,
						negativeList, positiveList, negativeCount, positiveCount, max);
				Split_Negative CC = new Split_Negative((2 * quarter) + 2, (3 * quarter) + 2, haveZero, positiveHash,
						negativeList, positiveList, negativeCount, positiveCount, max);
				Split_Negative DD = new Split_Negative((3 * quarter) + 3, negativeCount - 1, haveZero, positiveHash,
						negativeList, positiveList, negativeCount, positiveCount, max);

				Thread t1 = new Thread(AA);
				Thread t2 = new Thread(BB);
				Thread t3 = new Thread(CC);
				Thread t4 = new Thread(DD);
				t1.start();
				t2.start();
				t3.start();
				t4.start();

				try {
					t4.join();
					t3.join();
					t2.join();
					t1.join();
				} catch (Exception e) {

				}

				zeroAns = AA.getANS0() + BB.getANS0() + CC.getANS0() + DD.getANS0();
				ans_1 = AA.getANS1() + BB.getANS1() + CC.getANS1() + DD.getANS1();
				ans_2 = AA.getANS2() + BB.getANS2() + CC.getANS2() + DD.getANS2();
			} else {
				for (int i = 0; i < negativeCount; i++) {
					if (haveZero == true && -negativeList[i] <= max && positiveHash[-negativeList[i]] == true) {
						zeroAns++;
					}

					for (int j = i + 1; j < negativeCount; j++) {
						int sum = negativeList[i] + negativeList[j];
						if (-sum <= max && positiveHash[-sum] == true) {
							ans_1++;
						}
					}

					for (int j = 0; j < positiveCount; j++) {
						int sum = negativeList[i] + positiveList[j];
						if (sum < 0 && -sum <= max && -sum != positiveList[j] && positiveHash[-sum] == true) {
							ans_2++;
						}
					}

				}
			}

		} else {
			if (positiveCount >= 1000) {
				int quarter = positiveCount / 4;
				Split_Positive AA = new Split_Positive(0, quarter, haveZero, negativeHash, negativeList, positiveList,
						negativeCount, positiveCount, min);
				Split_Positive BB = new Split_Positive(quarter + 1, (2 * quarter) + 1, haveZero, negativeHash,
						negativeList, positiveList, negativeCount, positiveCount, min);
				Split_Positive CC = new Split_Positive((2 * quarter) + 2, (3 * quarter) + 2, haveZero, negativeHash,
						negativeList, positiveList, negativeCount, positiveCount, min);
				Split_Positive DD = new Split_Positive((3 * quarter) + 3, positiveCount - 1, haveZero, negativeHash,
						negativeList, positiveList, negativeCount, positiveCount, min);
				Thread t1 = new Thread(AA);
				Thread t2 = new Thread(BB);
				Thread t3 = new Thread(CC);
				Thread t4 = new Thread(DD);
				t1.start();
				t2.start();
				t3.start();
				t4.start();

				try {
					t4.join();
					t3.join();
					t2.join();
					t1.join();
				} catch (Exception e) {

				}

				zeroAns = AA.getANS0() + BB.getANS0() + CC.getANS0() + DD.getANS0();
				ans_1 = AA.getANS1() + BB.getANS1() + CC.getANS1() + DD.getANS1();
				ans_2 = AA.getANS2() + BB.getANS2() + CC.getANS2() + DD.getANS2();
			} else {
				for (int i = 0; i < positiveCount; i++) {
					if (haveZero == true && positiveList[i] <= -min && negativeHash[positiveList[i]] == true) {
						zeroAns++;
					}

					for (int j = i + 1; j < positiveCount; j++) {
						int sum = positiveList[i] + positiveList[j];
						if (sum <= -min && negativeHash[sum] == true) {
							ans_1++;
						}
					}

					for (int j = 0; j < negativeCount; j++) {
						int sum = positiveList[i] + negativeList[j];
						if (sum > 0 && sum <= -min && -sum != negativeList[j] && negativeHash[sum] == true) {
							ans_2++;
						}
					}

				}
			}

		}

		return ((ans_2 / 2) + zeroAns + ans_1);
	}
}

class Split_Negative implements Runnable {
	private int start;
	private int end;

	private boolean haveZero;
	private boolean positiveHash[];
	private int negativeList[];
	private int positiveList[];
	private int negativeCount;
	private int positiveCount;
	private int max;

	private volatile int ans0 = 0;
	private volatile int ans1 = 0;
	private volatile int ans2 = 0;

	public Split_Negative(int start, int end, boolean haveZero, boolean positiveHash[], int negativeList[],
			int positiveList[], int negativeCount, int positiveCount, int max) {
		this.start = start;
		this.end = end;

		this.haveZero = haveZero;
		this.positiveHash = positiveHash;
		this.negativeList = negativeList;
		this.positiveList = positiveList;
		this.negativeCount = negativeCount;
		this.positiveCount = positiveCount;
		this.max = max;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			if (haveZero == true && -negativeList[i] <= max && positiveHash[-negativeList[i]] == true) {
				ans0++;
			}

			for (int j = i + 1; j < negativeCount; j++) {
				int sum = negativeList[i] + negativeList[j];
				if (-sum <= max && positiveHash[-sum] == true) {
					ans1++;
				}
			}

			for (int j = 0; j < positiveCount; j++) {
				int sum = negativeList[i] + positiveList[j];
				if (sum < 0 && -sum <= max && -sum != positiveList[j] && positiveHash[-sum] == true) {
					ans2++;
				}
			}
		}
	}

	public int getANS0() {
		return ans0;
	}

	public int getANS1() {
		return ans1;
	}

	public int getANS2() {
		return ans2;
	}
}

class Split_Positive implements Runnable {
	private int start;
	private int end;

	private boolean haveZero;
	private boolean negativeHash[];
	private int negativeList[];
	private int positiveList[];
	private int negativeCount;
	private int positiveCount;
	private int min;

	private volatile int ans0 = 0;
	private volatile int ans1 = 0;
	private volatile int ans2 = 0;

	public Split_Positive(int start, int end, boolean haveZero, boolean negativeHash[], int negativeList[],
			int positiveList[], int negativeCount, int positiveCount, int min) {
		this.start = start;
		this.end = end;

		this.haveZero = haveZero;
		this.negativeHash = negativeHash;
		this.negativeList = negativeList;
		this.positiveList = positiveList;
		this.negativeCount = negativeCount;
		this.positiveCount = positiveCount;
		this.min = min;

	}

	public void run() {
		for (int i = start; i <= end; i++) {
			if (haveZero == true && positiveList[i] <= -min && negativeHash[positiveList[i]] == true) {
				ans0++;
			}

			for (int j = i + 1; j < positiveCount; j++) {
				int sum = positiveList[i] + positiveList[j];
				if (sum <= -min && negativeHash[sum] == true) {
					ans1++;
				}
			}

			for (int j = 0; j < negativeCount; j++) {
				int sum = positiveList[i] + negativeList[j];
				if (sum > 0 && sum <= -min && -sum != negativeList[j] && negativeHash[sum] == true) {
					ans2++;
				}
			}

		}
	}

	public int getANS0() {
		return ans0;
	}

	public int getANS1() {
		return ans1;
	}

	public int getANS2() {
		return ans2;
	}
}