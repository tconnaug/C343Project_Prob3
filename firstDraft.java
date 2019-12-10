import java.io.*;
import java.util.Scanner;

public class EditDistance { 
	//used this edit distance from hw9
	static int min(int a,int b,int c){
		int ret=-1;
		ret=(a>b)?(b>c?b:c):(a>c?c:a);		  
		return(ret);
	}
  
    static int editDistance(String str1, String str2, int nOfrows, int nOfCol) { 
        
    	int ret=-1;
		if(str1==null || str2==null){
			ret=-2;
		}  	
        int table[][] = new int[nOfrows+1][nOfCol+1]; 

        for (int i=0; i<=nOfrows; i++) { 
            for (int j=0; j<=nOfCol; j++) { 
                if (i==0) {
                    table[i][j] = j;
                }
                else if (j==0) { 
                    table[i][j] = i; 
                }
                else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    table[i][j] = table[i-1][j-1]; 
                }
                else {
                    table[i][j] = 1 + min(table[i][j-1],  table[i-1][j],  table[i-1][j-1]); 
                } 
            }
        } 
        ret = table[nOfrows][nOfCol];
   
        return ret; 
    } 
    
    //this code from lab9
    static int LCS(String a, String b) {
		int max = Math.max(a.length(), b.length());
		int[][] array = new int[max + 1][max + 1];
		int m = a.length();
		int n = b.length();
		int MAX = 0;
		a = ' ' + a;
		b = ' ' + b;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					array[i][j] = array[i - 1][j - 1] + 1;
				} else {
					array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
				}
				MAX = Math.max(MAX, array[i][j]);
			}
		}
		return MAX;
	}
    
    public static void readTextFileUsingScanner(String fileName) { 
    	try { 
    		Scanner sc = new Scanner(new File(fileName)); 
    		while (sc.hasNext()) { 
    			String str = sc.nextLine();
    			System.out.println(str); 
    			} 
    		sc.close(); 
    		}
    	catch (IOException e) { 
    		// TODO Auto-generated catch block 
    		e.printStackTrace(); 
    		}
    	
    }
    
  
    public static void main(String args[]) {
      String str1 = "AAAB";
			String str2 = "AABB";
			System.out.println("Edit distance: " + editDistance(str1, str2, str1.length(), str2.length()));

			ArrayList<String> arr = new ArrayList<String>();
			
			
			int n = 1; // n is the # of pairs of strings, in this case to keep it simple it's just the
						// pair str1 and str2 to keep it simple
			for (int i = 0; i < n; i++) {
				String s1 = str1;
				String s2 = str2;
				
				//
				
				System.out.println("LCS: " + LCS(str1, str2));
			}
			
			
			boolean first = true;
			

	        try (Scanner scanner = new Scanner(System.in); ) {
	        	//before we reach the % sign on last line
	        	String seq = null;
	            while(scanner.hasNextLine()) { //scanner.hasnextline() not working, needed to add % sign at end of both files
	                
	            	String line = scanner.nextLine().trim();
	            
	            	
	                if (line.startsWith(">")) {
	                	
	                	//add the sequence to the array and then reset the sequence var
	                	
	                	arr.add(seq);
	                	seq = "";

	                    	
	                } else {
	                
	                	//add each line of sequence to the seq variable, in order to get the full sequence
	                	String line2;
	                	line2 = line.substring(line.indexOf(" ") + 1);
	                	line2.trim();
	                	
	                	seq = seq + line2;
	                	System.out.println(arr);
	                    
	                }
	
	            }
	            
	            
	        }
	        
	       //System.out.println();
			 
	       //iterate through array of the DNA sequences and find LCS of each one compared to each other
	       for(int i = 0; i < arr.size(); i++) {
	    	   System.out.println("LCS: " + LCS(arr.get(i), arr.get(i + 1)));
	
	       }

	       System.out.println(arr);
}
