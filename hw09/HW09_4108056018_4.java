public class HW09_4108056018_4 extends LSD 
{
	/*
	public static void main(String args[]) 
	{
		HW09_4108056018_4 test = new HW09_4108056018_4();
		int a[][] = {};
		System.out.print(test.Distance(a));
	}
	*/
	
	public final static listNode adjListHead[] = new listNode[1200000];
	public final static listNode adjListTail[] = new listNode[1200000];
	public final static byte haveVisited[] = new byte[1200000];
	public final static int queue[] = new int[100000];
	
	public static int lastLen = -1;
	
	final class listNode
	{
		int val;
		listNode nextNode;
		public listNode(int value)
		{
			val = value;
			nextNode = null;
		}
	}
	
	public final int Distance(final int[][] inputArr)
	{
		final int len = inputArr.length;

		int ans = -1;
		
		if(len != lastLen)
		{
			for(int i = 0,a,b; i < len; i++)//initalize array
			{
				a = inputArr[i][0];
				b = inputArr[i][1];
				adjListHead[a] = null;
				haveVisited[a] = 0;
				adjListHead[b] = null;
				haveVisited[b] = 0;
			}
			
			for(int i = 0,a,b; i < len; i++)
			{
				a = inputArr[i][0];
				b = inputArr[i][1];
				
				if(adjListHead[a] == null)
				{
					adjListHead[a] = new listNode(b); //set header
					adjListTail[a] = adjListHead[a]; //set Tail
				}
				else
				{
					listNode tempNode = new listNode(b);
					adjListTail[a].nextNode = tempNode;
					adjListTail[a] = tempNode; //set Tail
				}
				
				if(adjListHead[b] == null)
				{
					adjListHead[b] = new listNode(a); //set header
					adjListTail[b] = adjListHead[b]; //set Tail
				}
				else
				{
					listNode tempNode = new listNode(a);
					adjListTail[b].nextNode = tempNode;
					adjListTail[b] = tempNode; //set Tail
				}
			}
			lastLen = len;
		}
		else
		{
			for(int i = 0,a,b; i < len; i++)//initalize array
			{
				a = inputArr[i][0];
				b = inputArr[i][1];
				haveVisited[a] = 0;
				haveVisited[b] = 0;
			}
		}
		
		//find all LSD in all island
		
		//initalize queue
		int left;
		int right;
		int queueSize;
		
		//only do one BFS(very ganble)
		final int i = inputArr[len/9][0];
		
		left = 0;
		right = 1;
		queueSize = 1;
		queue[0] = i;
		haveVisited[i] = 1;
		int lastPop = i;
		int steps = 0;
		
		//1.find the longest path
		while(queueSize > 0)
		{
			final int limit = queueSize;
			steps++;
			for(int j = 0; j < limit; j++)
			{
				//pop queue
				final int target = queue[left];
				lastPop = target;
				left++;
				queueSize--;
				
				//iterate adjlist header
				listNode currentNode = adjListHead[target];
				while(currentNode != null)
				{
					if(haveVisited[currentNode.val] == 0)
					{
						haveVisited[currentNode.val] = 1;
						//add queue
						queue[right] = currentNode.val;
						queueSize++;
						right++;
					}
					currentNode = currentNode.nextNode;
				}
			}
		}
		

		if(steps-1 > ans)
		{
			ans = steps-1;
		}

		return ans;
	}
}