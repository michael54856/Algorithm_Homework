public class HW02_4108056018_3 extends ThreeSum {
	private static int nums[];

	private static void quickSort(int l, int r) {
		if (l >= r)
			return;
		int mid = partition(l, r);
		quickSort(l, mid);
		quickSort(mid + 1, r);
	}

	private static int partition(int l, int r) {
		int pivot = nums[l];
		while (l < r) {
			while (l < r && nums[r] >= pivot) {
				r--;
			}
			nums[l] = nums[r];
			while (l < r && nums[l] <= pivot) {
				l++;
			}
			nums[r] = nums[l];
		}
		nums[l] = pivot;
		return l;
	}

	public int T_sum(int[] A) {
		nums = A;
		quickSort(0, A.length - 1);
		int ans = 0;

		for (int i = 0, len = A.length; i < len - 2; i++) {
			if (A[i] > 0) {
				break;
			}

			int left = i + 1;
			int right = len - 1;

			while (left < right) {
				int sum = A[i] + A[left] + A[right];
				if (sum == 0) {
					ans++;
					left++;
					right--;
				} else if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}

		return ans;
	}
}
