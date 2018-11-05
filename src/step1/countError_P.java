package step1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * typeI typeII error
 * @author hjl
 *
 */
public class countError_P {
	static List<String> realallp = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		String str = "I:\\PSOlab\\30-26";
		String roadp = str + "\\real_p.txt";
		readReal(roadp,realallp);
		
		Set<Integer> r_p = new HashSet<Integer>();
		int row = 1;                            
		select(r_p,realallp,row);

		//finalp
		for(int i = 1; i <= 100; i++){
			String txts = str + "\\res\\finalp\\pres" + i +".txt";
			Set<Integer> resultp = new HashSet<>();
			readRes(txts, resultp);
			
			int pTP11 = 0;
			int sizeP = r_p.size();
			int sizeIndexP = resultp.size();
			
			List<Integer> res = new ArrayList<>();
			Iterator<Integer> it = resultp.iterator();
			while(it.hasNext()){
				res.add(it.next());
			}
			for(int i1 = 0; i1 < sizeIndexP; i1++){
				if(r_p.contains(res.get(i1)+1)){
					pTP11 = pTP11+1;
				}
			}
			int pFP01 = sizeIndexP - pTP11;
			int pFN10 = sizeP - pTP11;
			
			DecimalFormat df=new DecimalFormat("0.00");
			String typeIP = df.format((float)pFP01/sizeIndexP) ;
			String typeIIP = df.format((float)pFN10/sizeP) ;
			
			//System.out.println(typeIP);
			System.out.println(typeIIP);
		}
	}


	private static void readRes(String txt, Set<Integer> results) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(txt));
		String temp = br.readLine();
		while(temp != null){
			String[] items = temp.split(" ");
			int[] cur = new int[items.length];
			for(int j = 0; j < items.length; j++){
				cur[j] = Integer.parseInt(items[j]);
				if(cur[j] != -2){
					results.add(cur[j]);
				}
			}
			temp = br.readLine();
		}
		br.close();
	}

	private static void select(Set<Integer> r, List<String> real, int row) {
		for(int i = 0; i < real.size(); i++){
			if(i == (row-1)){
				String temp = real.get(i);
				String[] items = temp.split(" ");
				int[] cur = new int[items.length];
				for(int j = 0; j < items.length; j++){
					cur[j] = Integer.parseInt(items[j]);
					r.add(cur[j]);
				}
				
			}
		}
	}


	private static void readReal(String road, List<String> real) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(road));
		String cur = br.readLine();
		while(cur != null){
			real.add(cur);
			cur = br.readLine();
		}
		br.close();
	}
}
