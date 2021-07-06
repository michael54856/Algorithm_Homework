public class HW11_4108056018_3 extends GroupCounting
{
	/*
	public static void main(String args[])
	{
		HW11_4108056018_1 test = new HW11_4108056018_1();
		String A[] = {"6","8","3","8","1","6","7","7","3","7"};
		String B[] = {"8","5","8","4","6","5","6","5","4","3"};
		System.out.println(test.count(A, B));
		System.out.println(test.count(A, B));
		String A1[] = {"1","3","1","4","1","3","9","9"};
		String B1[] = {"2","2","3","5","6","7","8","10"};
		System.out.println(test.count(A1, B1));
		System.out.println(test.count(A1, B1));
		System.out.println(test.count(A, B));
	}
	*/

	final static int unionSize[] = new int[10000000];
	
	public final int count(String[] A, String[] B)
	{
		int vertexCount = 0;
		final int len = A.length;
		final int union[] = new int[1500000];
		
		int unionCount = 0;
		
		for(int i = 0,a,b; i < len; i++)
		{
			a = Integer.parseInt(A[i])+1;
			b = Integer.parseInt(B[i])+1;
			
			if(union[a] == 0)//new node
			{
				union[a] = a;
				unionSize[a] = 1;
				vertexCount++;
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
				vertexCount++;
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
	