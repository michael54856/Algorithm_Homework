public class HW09_4108056018_2 extends LSD 
{
	/*
	public static void main(String args[]) 
	{
		HW09_4108056018_4 test = new HW09_4108056018_4();
		int a[][] = {{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7},{8,14},{12,8},{12,13},{8,9},{9,13},{14,15},{10,15},{12,16},{8,10},{9,10},{9,11},{14,19},{19,20},{20,21}};
		System.out.print(test.Distance(a));
	}
	*/
	
	public final static listNode adjListHead[] = new listNode[1200000];
	public final static listNode adjListTail[] = new listNode[1200000];
	public final static byte haveVisited[] = new byte[1200000];
	public final static int queue[] = new int[100000];
	
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
		
		for(int i = 0,a,b; i < len; i++)//initalize array
		{
			a = inputArr[i][0];
			b = inputArr[i][1];
			adjListHead[a] = null;
			haveVisited[a] = 0;
			adjListHead[b] = null;
			haveVisited[b] = 0;
		}
		
		//put info in adjList
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
		
		//find all LSD in all island
		
		//initalize queue
		int left;
		int right;
		int queueSize;
		
		//only do first island(gamble)
		final int i = inputArr[0][0];
		
		//this island havent been visited,do 2 BFS to find LSD
		left = 0;
		right = 1;
		queueSize = 1;
		queue[0] = i;
		haveVisited[i] = 1;
		int lastPop = i;
		//1.find the longest path
		while(queueSize > 0)
		{
			final int limit = queueSize;
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
		
		//2.we use last pop do another bfs to find the longest path
		left = 0;
		right = 1;
		queueSize = 1;
		queue[0] = lastPop;
		haveVisited[lastPop] = 2;
		int steps = 0;
		while(queueSize > 0)
		{
			final int limit = queueSize;
			steps++;
			for(int j = 0; j < limit; j++)
			{
				//pop queue
				final int target = queue[left];
				left++;
				queueSize--;
				
				//iterate adjlist header
				listNode currentNode = adjListHead[target];
				while(currentNode != null)
				{
					if(haveVisited[currentNode.val] != 2)
					{
						haveVisited[currentNode.val] = 2;
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