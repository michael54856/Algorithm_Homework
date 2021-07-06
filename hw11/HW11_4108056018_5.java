public class HW11_4108056018_5 extends GroupCounting
{
	
	final static int union[] = new int[10000000];
	final static int unionSize[] = new int[10000000];
	final static int inputA[] = new int[5000000]; 
	final static int inputB[] = new int[5000000]; 
	
	public final int count(String[] A, String[] B)
	{
		int vertexCount = 0;
		final int len = A.length;

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
		
		return vertexCount - unionCount;
	}
}