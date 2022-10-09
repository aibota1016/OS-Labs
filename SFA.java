import java.util.LinkedList;

public class SequentialFileAllocation {
   private int[] disk;
   private LinkedList<File> files;

   public SequentialFileAllocation(int diskSize) {
       disk = new int[diskSize];
       files = new LinkedList<>();
   }

   public boolean allocate(File file){
       for(int i = file.start; i < file.start+file.length; i++){
           if(disk[i] == 1){
               System.out.println(file.fid + " not allocated");
               return false;
           }
       }
       for(int i = file.start; i < file.start+file.length; i++){
           disk[i] = 1;
       }
       files.add(file);
       System.out.println(file.fid + " allocated to block " +  file.start);
       return true;
   }

   public void printAllocatedFiles(){
       System.out.println("\nDirectory\n" +
               "------------------------------------\n" +
               "FileID\tStart\tLength\n");
       for(File f : files){
           System.out.println(f);
       }
   }
}

public class File {
   public Integer fid, start, length;

   public File(Integer fid, Integer start, Integer length) {
       this.fid = fid;
       this.start = start;
       this.length = length;
   }

   @Override
   public String toString() {
       return fid + "\t\t" + start + "\t\t" + length;
   }
}


