public class HW04_4108056018_2 extends One_0k_rock {
	public final boolean[] one0k(String[] str) {
		final short len = (short) str.length;
		final boolean ans[] = new boolean[len];
		for (short i = 0; i < len; i++) {
			final short sLen = (short) str[i].length();
			if ((sLen & 1) == 1)// odd
			{
				ans[i] = false;
				continue;
			}
			boolean flag = false;
			for (short j = 0, bound = (short) (sLen >> 1); j < bound; j++) {
				if (str[i].charAt(j) != '0') {
					ans[i] = false;
					flag = true;
					break;
				}
			}
			if (flag == true) {
				continue;
			}
			for (short j = (short) (sLen >> 1); j < sLen; j++) {
				if (str[i].charAt(j) != '1') {
					ans[i] = false;
					flag = true;
					break;
				}
			}
			if (flag == true) {
				continue;
			}
			ans[i] = true;
		}
		return ans;
	}
}