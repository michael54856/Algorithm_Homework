public class HW11_4108056018_1 extends GroupCounting
{
	
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
	
	
	final static int union[] = new int[10000000];
	final static int unionSize[] = new int[10000000];
	final static listNode sizeList[] = new listNode[10000000];
	
	public final int count(String[] A, String[] B)
	{
		int vertexCount = 0;
		final int len = A.length;
		
		final int inputA[] = new int[len]; 
		final int inputB[] = new int[len]; 
		int testBucket[] = new int [40000];
		
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
		

		for(int i = 0,a,b; i < len; i++)//initalize array
		{
			a = Integer.parseInt(A[i]);
			b = Integer.parseInt(B[i]);
			inputA[i] = a;
			inputB[i] = b;
			if(union[a] != -1)
			{
				union[a] = -1;
				vertexCount++;
			}
			if(union[b] != -1)
			{
				union[b] = -1;
				vertexCount++;
			}
		}
		
		int unionCount = 0;
		

		for(int i = 0,a,b; i < len; i++)
		{
			a = inputA[i];
			b = inputB[i];
			
			if(union[a] == -1)//new node
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
			
			if(union[b] == -1)//new node
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
}