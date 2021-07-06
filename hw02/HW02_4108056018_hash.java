public class HW02_4108056018_3 extends ThreeSum {

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

		return ((ans_2 / 2) + zeroAns + ans_1);
	}
}
