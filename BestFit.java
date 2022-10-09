public class GFG
{
	// Method to allocate memory to blocks as per Best fit
	// algorithm
	static void bestFit(int blockSize[], int m, int processSize[],
                    	int n)
	{
    	// Stores block id of the block allocated to a
    	// processw
    	int allocation[] = new int[n];

    	// Initially no block is assigned to any process
    	for (int i = 0; i < allocation.length; i++)
        	allocation[i] = -1;

    	// pick each process and find suitable blocks
    	// according to its size ad assign to it
    	for (int i=0; i<n; i++)
    	{
        	// Find the best fit block for current process
        	int bestIdx = -1;
        	for (int j=0; j<m; j++)
        	{
            	if (blockSize[j] >= processSize[i])
            	{
                	if (bestIdx == -1)
                    	bestIdx = j;
                	else if (blockSize[bestIdx] > blockSize[j])
                    	bestIdx = j;
            	}
        	}

        	// If we could find a block for current process
        	if (bestIdx != -1)
        	{
            	// allocate block j to p[i] process
            	allocation[i] = bestIdx;

            	// Reduce available memory in this block.
            	blockSize[bestIdx] -= processSize[i];
        	}
    	}
    	System.out.println("Allocating Blocks:");
    	System.out.println("\nProcess No.\t\tProcess Size\t\tBlock no.\t\t Block Size\t\t Internal Fragmentation");
    	for (int i = 0; i < n; i++)
    	{
        	System.out.print("   " + "P"+(i+1) + "\t\t\t\t" + processSize[i] + "\t\t\t\t\t");
        	if (allocation[i] != -1){
            	System.out.print(allocation[i] + 1);
            	// Prints Block Size
            	System.out.print("\t\t\t\t"+((blockSize[allocation[i]])+processSize[i]));
            	//Prints Internal Fragmentation
            	System.out.print("\t\t\t\t"+blockSize[allocation[i]]);

        	}
        	else
            	System.out.print("Not Allocated");
        	System.out.println();
    	}
	}





// Driver Method
	public static void main(String[] args)
	{
    	int blockSize[] = {100, 500, 200, 300, 600};
    	int processSize[] = {212, 417, 112, 426, 835};
    	int m = blockSize.length;
    	int n = processSize.length;

    	{
        	System.out.println("\nAvailable Blocks: \n");
        	for (int i = 0; i < blockSize.length; i++) {
            	System.out.print("\t|\t");
            	System.out.print(blockSize[i]);
        	}
        	System.out.print("\t|\t");
        	System.out.println("\n\n");
    	}

    	bestFit(blockSize, m, processSize, n);
	}
}



