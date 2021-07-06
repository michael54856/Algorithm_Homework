public class HW06_4108056018_1 extends Dessert_Desert {
	class node {
		int val;
		int index;

		public node(int v, int i) {
			val = v;
			index = i;
		}
	}

	public int[] maxBlocks(int[][] inputArr) {

		final int n = inputArr.length;// n test case
		final int ans[] = new int[n];// n ans

		for (int i = 0; i < n; i++) {
			final int len = inputArr[i].length;

			final node nodeArr[] = new node[len];
			for (int j = 0; j < len; j++) {
				nodeArr[j] = new node(inputArr[i][j], j);
			}
			final node tempArr[] = new node[len];

			mergeSort(nodeArr, tempArr, 0, len - 1);

			int count = 0;
			int indexSum = 0;
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += j;
				indexSum += nodeArr[j].index;

				if (sum == indexSum) {
					count++;
				}
			}
			ans[i] = count;
		}
		return ans;
	}

	public void mergeSort(node[] nodeArr, node[] tempArr, int front, int end) {
		if (front < end) // if end == front , we dont need to sort
		{
			int mid = (front + end) / 2;
			mergeSort(nodeArr, tempArr, front, mid);
			mergeSort(nodeArr, tempArr, mid + 1, end);
			// merge:
			for (int i = front; i <= end; i++)// copy array from nodeArr to tempArr
			{
				tempArr[i] = nodeArr[i];
			}
			int i = front;
			int start_1 = front;
			int start_2 = mid + 1;
			while (start_1 <= mid && start_2 <= end) {
				nodeArr[i++] = tempArr[start_1].val <= tempArr[start_2].val ? tempArr[start_1++] : tempArr[start_2++];
			}
			while (start_1 <= mid) {
				nodeArr[i++] = tempArr[start_1++];
			}
			while (start_2 <= end) {
				nodeArr[i++] = tempArr[start_2++];
			}
		}

	}
}