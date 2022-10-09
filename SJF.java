import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SJF {
   //Contains the list of processes
   ArrayList<Process> processes;
   public SJF(Integer processSize){
       Random rand = new Random();
       processes = new ArrayList<>();
       //Initialize the processes
       for (int i = 0; i < processSize; i++) {
           processes.add(new Process(i, rand.nextInt(20), rand.nextInt(20 - 1) +1 ));
       }
   }

   //Starts the CPU cycle
   public void start(){
       LinkedList<Process> queue = new LinkedList<>();
       int time = 0;
       while(!processes.isEmpty() && !queue.isEmpty()){
           for(Process p : processes){
               if(time > p.arrival){

               }
               if(time == p.arrival){
                   time += p.burst;
               }
           }
       }
   }

   @Override
   public String toString() {
       String output = "";
       output += """
               PID\t\t\t\tArrival\t\t\tBurst
               ------------------------------------------
               """;

               for(Process p : processes){
                   output += p.toString() + "\n";
               }
       return output;
   }
}


public class Process {
   Integer pid, arrival, burst, start;
   public Process(Integer p, Integer a, Integer b){
       this.pid = p;
       this.arrival = a;
       this.burst = b;
       this.start = null;
   }

   @Override
   public String toString() {
       return pid + "\t\t\t\t" + arrival + "\t\t\t\t" + burst;
   }
}



public class LAB1 {
   public static void main(String[] args) {
       SJF obj = new SJF(5);

   }
}



