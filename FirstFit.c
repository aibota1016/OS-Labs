#include <stdlib.h>
 
void FirstFit(int *blockSize, size_t m, int *processSize, size_t n) {
    int *allocation=(int*)malloc(n*sizeof(int));
    for (int i = 0; i < n; i++)
        allocation[i] = -1;
    
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) 
            if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
            }  
    }
    printf("Allocating Blocks:");
    printf("\nProcess No.\t\tProcess Size\t\tBlock no.\t\t Block Size\t\t Internal Fragmentation");
    for (int i = 0; i < n; i++) {
        printf("\n   P%d\t\t\t%d\t\t\t", (i+1),  processSize[i]);
        if (allocation[i] != -1){
            printf("%d", allocation[i] + 1);
            // Prints Block Size
            printf("\t\t\t\t%d", ((blockSize[allocation[i]])+processSize[i]));
            //Prints Internal Fragmentation
            printf("\t\t\t\t%d", blockSize[allocation[i]]);
        } else
            printf("Not Allocated");
    } 
    free(allocation);
}
 
int main(int argc, char** argv) {
    int blockSize[] = {100, 500, 200, 300, 600};
    int processSize[] = {312, 417, 212, 86, 635};
    size_t n = sizeof(processSize) / sizeof(processSize[0]);
    size_t m = sizeof(blockSize) / sizeof(blockSize[0]);
    
    printf("\nAvailable Blocks: \n");
    for (int i = 0; i < m; i++) {
        printf("\t|\t");
        printf("%d", blockSize[i]);
        }
    printf("\t|\t");
    printf("\n\n");
    
    FirstFit(blockSize, m, processSize, n);
    
    return (EXIT_SUCCESS);
}
