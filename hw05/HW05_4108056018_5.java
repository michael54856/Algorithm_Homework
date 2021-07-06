public class HW05_4108056018_5 extends LLK {
	volatile boolean myAns;
	public final boolean checkLLK(int[][] array) {
		final int threadCount = 16;
		final int len = array.length;
		// find the least power of 2 to exceed len
		int n = len - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		myAns = false;
		final int finN = n;
		if (len > threadCount) {
			final int segement = len / threadCount;
			class Worker extends Thread {
				int number;
				public Worker(int i) {
					this.number = i;
				}
				public void run() {
					final int start_ = number * segement;
					final int end_ = (number == threadCount - 1) ? len - 1 : (start_ + segement - 1);
					final myHashSet mySet = new myHashSet(finN + 1);
					for (int i = start_; i <= end_ && myAns == false; i++) {
						mySet.clear();
						for (int j = i + 1; j < len && myAns == false; j++) {
							if (mySet.add(((double) (array[i][1] - array[j][1]))
									/ ((double) (array[i][0] - array[j][0]))) == false) {
								myAns = true;
							}
						}
					}
				}
			}
			Worker[] workers = new Worker[threadCount];
			for (int i = 0; i < threadCount; i++) {
				workers[i] = new Worker(i);
				workers[i].start();
			}
			try {
				for (int i = threadCount - 1; i >= 0; i--) {
					workers[i].join();
				}
			} catch (InterruptedException e) {

			}
			return myAns;
		} else {
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