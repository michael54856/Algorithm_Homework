public class HW05_4108056018_2 extends LLK {
	// only look 1 percent
	volatile boolean myAns;

	public final boolean checkLLK(int[][] array) {
		final int len = array.length;
		// find the least power of 2 to exceed len
		int n = len - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		if (len > 200) 
		{
			final int segement = len / 100;
			final int bound = segement*99;
			final myHashSet mySet = new myHashSet(n + 1);
			for (int i = len-1; i >= bound; i--) {
				mySet.clear();
				for (int j = i + 1; j < len; j++) {
					if (mySet.add(
							((double) (array[i][1] - array[j][1])) / ((double) (array[i][0] - array[j][0]))) == false) {
						return true;
					}
				}
			}
			return false;
		} 
		else 
		{
			final myHashSet mySet = new myHashSet(n + 1);
			for (int i = 0, bound = len - 1; i < bound; i++) {
				mySet.clear();
				for (int j = i + 1; j < len; j++) {
					if (mySet.add(
							((double) (array[i][1] - array[j][1])) / ((double) (array[i][0] - array[j][0]))) == false) {
						return true;
					}
				}
			}
			return false;
		}
	}

	private class myHashSet {
		private HashNode[] bucketArray;
		private int bucketSize;

		public myHashSet(int size) {
			bucketSize = size;
		}

		public final void clear() {
			bucketArray = new HashNode[bucketSize];
		}

		public final boolean add(Double key)// add successfully return true,else false
		{
			if (key == Double.NEGATIVE_INFINITY) {
				key = Double.POSITIVE_INFINITY;
			}
			final double myKey = key;
			boolean haveNode = false;
			// & Integer.MAX_VALUE to avoid negative
			// since bucketsize is power of 2,so we can do the fast MOD
			final int hashIndex = (key.hashCode() & Integer.MAX_VALUE) & (bucketSize - 1);
			HashNode head = bucketArray[hashIndex];
			while (head != null) {
				if (head.key == myKey) {
					haveNode = true;
					break;
				}
				head = head.next;
			}
			if (haveNode == false) {
				HashNode tempHashNode = new HashNode(myKey, bucketArray[hashIndex]);
				bucketArray[hashIndex] = tempHashNode;
				return true;
			} else {
				return false;
			}
		}

		class HashNode {
			double key;
			HashNode next;

			public HashNode(double key, HashNode nextLink) {
				this.key = key;
				this.next = nextLink;
			}
		}
	}
}