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
public class countError_S {
	static List<String> realalls = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		String str = "E:\\PSOlab\\30-26";
		String roads = str + "\\real_s.txt";
		readReal(roads,realalls);
		
		Set<Integer> r_s = new HashSet<Integer>();
		int row = 1;                           
		select(r_s,realalls,row);
	
		//finals
		for(int i = 1; i <= 100; i++){
			String txts = str + "\\res\\finals\\sres" + i +".txt";
			Set<Integer> results = new HashSet<>();
			readRes(txts, results);
			
			int sTP11 = 0;
			int sizeS = r_s.size();
			int sizeIndexS = results.size();
			
			List<Integer> res = new ArrayList<>();
			Iterator<Integer> it = results.iterator();
			while(it.hasNext()){
				res.add(it.next());
			}
			
			for(int i1 = 0; i1 < sizeIndexS; i1++){
				
				if(r_s.contains(res.get(i1)+1)){
					sTP11 = sTP11+1;
				}
			}
			int sFP01 = sizeIndexS - sTP11;
			int sFN10 = sizeS - sTP11;
			
			DecimalFormat df=new DecimalFormat("0.00");

			String typeIS = df.format((float)sFP01/sizeIndexS) ;
			String typeIIS = df.format((float)sFN10/sizeS) ;
			
			//System.out.println(typeIS);
			System.out.println(typeIIS);
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
