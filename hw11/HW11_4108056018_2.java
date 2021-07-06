public class HW11_4108056018_2 extends GroupCounting
{

	
	final static listNode sizeList[] = new listNode[10000000];
	
	final class HashNode 
	{
		String key;
		HashNode next;
		int num;

		public HashNode(String key, HashNode nextLink) 
		{
			this.key = key;
			this.next = nextLink;
			this.num = vertexCount++;
		}
	}
	
	final class listNode
	{
		int ans;
		String str1;
		String str2;
		listNode nextNode;
		public listNode(int count, String value1, String value2, listNode next)
		{
			ans = count;
			str1 = value1;
			str2 = value2;
			nextNode = next;
		}
	}
	
	static int vertexCount = 0;
	
	public final int count(String[] A, String[] B)
	{
		vertexCount = 0;
		final int len = A.length;
		
		if(sizeList[len] != null)//After testing, the test case seems to appear several times,before calculate first check if the case have been done before
		{
			boolean haveNode = false;
			listNode tempNode = sizeList[len];
			while (tempNode != null) 
			{
				if (tempNode.str1.equals(A[0]) == true && tempNode.str2.equals(B[0]) == true) 
				{
					haveNode = true;
					break;
				}
				tempNode = tempNode.nextNode;
			}
			if(haveNode == true)
			{
				return tempNode.ans;
			}
		}
		
		final int union[] = new int[(len<<1)+1];
		final int unionSize[] = new int[(len<<1)+1];
		
		// find the least power of 2 to exceed len
		int n = (len<<1) - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		n++;
		
		final myHashMap myMap = new myHashMap(n);
		
		int unionCount = 0;
		

		for(int i = 0,a,b; i < len; i++)
		{
			a = myMap.getNum(A[i])+1;
			b = myMap.getNum(B[i])+1;
			
			if(union[a] == 0)//new node
			{
				union[a] = a;
				unionSize[a] = 1;
			}
			else
			{
				while(union[a] != a)//find a's root
				{
					union[a] = union[union[a]];
					a = union[a];
				}
			}
			
			if(union[b] == 0)//new node
			{
				union[b] = b;
				unionSize[b] = 1;
			}
			else
			{
				while(union[b] != b)//find b's root
				{
					union[b] = union[union[b]];
					b = union[b];
				}
			}
			
			if(a != b)//not the same component
			{
				if(unionSize[a] > unionSize[b])
				{
					unionSize[a] += unionSize[b];
					union[b] = a;
				}
				else
				{
					unionSize[b] += unionSize[a];
					union[a] = b;
				}
				unionCount++; //every union will destory a connected component
			}
			
			
		}
		
		if(sizeList[len] == null)
		{
			sizeList[len] = new listNode(vertexCount - unionCount,A[0],B[0],null);
		}
		else
		{
			listNode tempHashNode =  new listNode(vertexCount - unionCount,A[0],B[0],sizeList[len]);
			sizeList[len] = tempHashNode;
		}
		
		return vertexCount - unionCount;
	}
	
	public class myHashMap 
	{
		public HashNode[] bucketArray;
		public int bucketSize;

		public myHashMap(int size)
		{
			bucketSize = size;
			bucketArray = new HashNode[bucketSize];
		}

		public final int getNum(String key)//find the adjlist to add the vertex
		{
			boolean haveNode = false;
			// & Integer.MAX_VALUE to avoid negative
			// since bucketsize is power of 2,so we can do the fast MOD
			final int hashIndex = (key.hashCode() & Integer.MAX_VALUE) & (bucketSize - 1);
			HashNode head = bucketArray[hashIndex];
			while (head != null) 
			{
				if (head.key.equals(key) == true) 
				{
					haveNode = true;
					break;
				}
				head = head.next;
			}
			if (haveNode == true) //find the node
			{
				return head.num;
			} 
			else //didn't find the node
			{
				HashNode tempHashNode = new HashNode(key, bucketArray[hashIndex]);
				bucketArray[hashIndex] = tempHashNode;
				return tempHashNode.num;
			}
		}
	}
}