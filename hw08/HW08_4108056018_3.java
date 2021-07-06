public class HW08_4108056018_3 extends Buy_Phone_v2 
{
	//first mergesot and count answer(this is wrong strategy)
	
	static final int tempArr[][] = new int[100000][6];
	public static final void mergeSort(final int[][] inputArr, int front, int end)
	{
		if (front < end) // if end == front , we dont need to sort
		{
			final int mid = (front + end) >> 1;
			mergeSort(inputArr, front, mid);
			mergeSort(inputArr, mid + 1, end);
			// merge:
			System.arraycopy(inputArr, front, tempArr, front, end-front+1);
			int i = front;
			int start_1 = front;
			int start_2 = mid + 1;
			while (start_1 <= mid && start_2 <= end) 
			{
				if(tempArr[start_1][0] == tempArr[start_2][0])
				{
					if(tempArr[start_1][1] == tempArr[start_2][1])
					{
						if(tempArr[start_1][2] == tempArr[start_2][2])
						{
							if(tempArr[start_1][3] == tempArr[start_2][3])
							{
								if(tempArr[start_1][4] == tempArr[start_2][4])
								{
									inputArr[i++] = tempArr[start_1][5] < tempArr[start_2][5] ? tempArr[start_1++] : tempArr[start_2++];
								}
								else
								{
									inputArr[i++] = tempArr[start_1][4] < tempArr[start_2][4] ? tempArr[start_1++] : tempArr[start_2++];
								}
							}
							else
							{
								inputArr[i++] = tempArr[start_1][3] < tempArr[start_2][3] ? tempArr[start_1++] : tempArr[start_2++];
							}
						}
						else
						{
							inputArr[i++] = tempArr[start_1][2] < tempArr[start_2][2] ? tempArr[start_1++] : tempArr[start_2++];
						}
					}
					else
					{
						inputArr[i++] = tempArr[start_1][1] < tempArr[start_2][1] ? tempArr[start_1++] : tempArr[start_2++];
					}
				}
				else
				{
					inputArr[i++] = tempArr[start_1][0] < tempArr[start_2][0] ? tempArr[start_1++] : tempArr[start_2++];
				}
				
			}
			while (start_1 <= mid) 
			{
				inputArr[i++] = tempArr[start_1++];
			}
			while (start_2 <= end) 
			{
				inputArr[i++] = tempArr[start_2++];
			}
		}

	}
	
	
	public final int[][] bestPhone(final int[][] inputArr)
	{
		final int len = inputArr.length;
		mergeSort(inputArr, 0, len-1);

		int ansCount = 1;
		
		int ans_V0[] = new int[len];
		int ans_V1[] = new int[len];
		int ans_V2[] = new int[len];
		int ans_V3[] = new int[len];
		int ans_V4[] = new int[len];
		int ans_V5[] = new int[len];
		
		ans_V0[0] = inputArr[len-1][0];
		ans_V1[0] = inputArr[len-1][1];
		ans_V2[0] = inputArr[len-1][2];
		ans_V3[0] = inputArr[len-1][3];
		ans_V4[0] = inputArr[len-1][4];
		ans_V5[0] = inputArr[len-1][5];
		
		
		boolean canPutIn;
		for(int i = len-2; i >= 0; i--)
		{
			canPutIn = true;
			for(int j = 0; j < ansCount; j++)
			{
				if(ans_V0[j] >= inputArr[i][0] && ans_V1[j] >= inputArr[i][1] && ans_V2[j] >= inputArr[i][2] && ans_V3[j] >= inputArr[i][3] && ans_V4[j] >= inputArr[i][4] && ans_V5[j] >= inputArr[i][5])//find a [j] element all more than i
				{
					canPutIn = false;
					break;
				}
			}
			if(canPutIn == true)
			{
				ans_V0[ansCount] = inputArr[i][0];
				ans_V1[ansCount] = inputArr[i][1];
				ans_V2[ansCount] = inputArr[i][2];
				ans_V3[ansCount] = inputArr[i][3];
				ans_V4[ansCount] = inputArr[i][4];
				ans_V5[ansCount++] = inputArr[i][5];
			}
			
		}

		final int finalAns[][] = new int[ansCount][6];
		for(int i = 0; i < ansCount; i++)
		{
			finalAns[i][0] = ans_V0[ansCount-1-i];
			finalAns[i][1] = ans_V1[ansCount-1-i];
			finalAns[i][2] = ans_V2[ansCount-1-i];
			finalAns[i][3] = ans_V3[ansCount-1-i];
			finalAns[i][4] = ans_V4[ansCount-1-i];
			finalAns[i][5] = ans_V5[ansCount-1-i];
		}

		return finalAns;
		
	}
}