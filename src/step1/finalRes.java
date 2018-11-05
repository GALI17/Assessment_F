
package step1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 得到筛选后结果，用来进行下一步评估
 * @author hjl
 *
 */
public class finalRes {
	static List<String> sfinal = new ArrayList<>();
	static List<String> pfinal = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
	
		for(int i = 1; i <= 100; i++){
			String road = "30-26";
			sfinal = new ArrayList<>();
			pfinal = new ArrayList<>();
			
			String roadp = "I:\\PSOlab\\" + road+ "\\res\\p\\pres" + i +".txt";
			ReadPData(roadp, pfinal);
			String roads = "I:\\PSOlab\\" + road+ "\\res\\s\\sres" + i +".txt";
			ReadPData(roads, sfinal);
			
			String outp ="I:\\PSOlab\\" + road+ "\\res\\finalp\\pres" + i +".txt";
			String outs = "I:\\PSOlab\\" + road+ "\\res\\finals\\sres" + i +".txt";
			outData(outp, outs);
		}

	}

	private static void outData(String outp, String outs) throws IOException {
		BufferedWriter bwp = new BufferedWriter(new FileWriter(outp));
		BufferedWriter bws = new BufferedWriter(new FileWriter(outs));
		
		for(int i = 0; i < 30; i++){
			if(!sfinal.get(i).equals("") && !pfinal.get(i).equals("") && (i%2 == 1)){
				bwp.write(pfinal.get(i));
				bwp.newLine();
				bws.write(sfinal.get(i));
				bws.newLine();
			}
		}
		bwp.close();
		bws.close();
	}

	private static void ReadPData(String road, List<String> spfinal) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(road));
		String cur = br.readLine();
		while(cur != null){
			spfinal.add(cur);
			cur = br.readLine();
		}
		br.close();
	}
}
