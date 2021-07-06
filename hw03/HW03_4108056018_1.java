public class HW03_4108056018_1 extends HillFinding {
	public int H_Finding(int[] A) {
		final int len = A.length;
		int l = 0;
		int r = len - 1;
		for (int mid; r > l;) {
			mid = (l + r) >> 1;
			if (A[mid] < A[r]) {
				r = mid;
			} else if (A[mid] > A[r]) {
				l = mid + 1;
			} else {
				if (A[r - 1] > A[r]) {
					l = r;
					break;
				}
				r--;
			}
		}
		return len - l - 1;
	}
}