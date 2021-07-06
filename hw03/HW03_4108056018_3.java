public class HW03_4108056018_3 extends HillFinding {
	public int H_Finding(int[] A) {
		for (int i = 1, len = A.length; i < len; i++) {
			if (A[i] < A[i - 1])
				return len - i - 1;
		}
		return 0;
	}
}