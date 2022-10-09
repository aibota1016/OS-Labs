public class Lab1 {

   public static int[] Sort(int[] arr, int[] burst) {
       int indexes[] = new int[arr.length]; //array for keeping track of the changed indexes
       for (int i=0; i<arr.length; i++)
           indexes[i] = i+1;
       for (int i=0; i<arr.length; i++) {
           int current = arr[i];
           int temp = burst[i];
           int j = i-1;
           while (j>=0 && arr[j]>current) {
               arr[j+1] = arr[j];
               burst[j+1] = burst[j];
               indexes[j+1] = indexes[j];
               j--;
           }
           arr[j+1] = current;
           burst[j+1] = temp;
           indexes[j+1] = i+1;
       }
       return indexes;
   }

   public static void algoFCFS(int n, int[] arrivalTime, int[] burstTime) {
       int[] waitingTime = new int[n];
       int[] turnaroundTime = new int[n];
       int[] indexes = Sort(arrivalTime, burstTime); //sorting the jobs by their arrival time
       waitingTime[0] = arrivalTime[0];
       turnaroundTime[0] = burstTime[0];
       for (int i=1; i<n; i++) {
           for (int j=0; j<i; j++)
               waitingTime[i] += burstTime[j]; //calculates the startTime
           for (int j=0; j<=i; j++)
               turnaroundTime[i] += burstTime[j]; //calculates the finishTime
           //Waiting time = Start time - Arrival time
           //Turnaround time = Finish time - Arrival time
           waitingTime[i] = waitingTime[i] - arrivalTime[i];
           turnaroundTime[i] = turnaroundTime[i] - arrivalTime[i];
       }
       int total_waiting=0, total_turnaround=0;
       System.out.println("Job_No  Waiting_Time  Turnaround_time");
       for (int i=0; i<n; i++) {
           System.out.printf("%5d%10d%12d\n",indexes[i], waitingTime[i], turnaroundTime[i]);
           total_waiting += waitingTime[i];
           total_turnaround += turnaroundTime[i];
       }
       System.out.println("Average Waiting Time: " + total_waiting/n +
               "\nAverage Turnaround Time: " + total_turnaround/n);
   }
   public static void main(String[] args) {
       int[] arrival = {2,5,1,0,4};
       int[] bursts = {6,2,8,3,4};
       algoFCFS(5, arrival, bursts);
   }
}
