import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class usecase2 {

	public static void main(String[] args) {
		try {
			FileReader fr=new FileReader("Battery.txt");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			JSONObject bat=new JSONObject();
			String foreground="";
			String fc="";
			String per="";
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				
				if(line.contains("Foreground activities:"))
				{
					foreground=line;
					fc=foreground.trim();
					String a[]=fc.split("\\s+");
					fc=a[2]+" "+a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7];
					//foreground=a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7]+" "+a[8];
					bat.put("Foreground_time", fc);
				}
				else if(line.contains("Uid u0a202"))
				{
					String array[]=line.split("\\s+");
					per=array[3];
					bat.put("Battery_drain", per);
					float bp=Float.parseFloat(per);
					float battery_percent=bp/1000;
					bat.put("Battery_percentage", (String.format("%.3f",battery_percent)));
				}
			}
			System.out.println(bat);
			FileWriter file=new FileWriter("usecase_two_out.json");
			file.write(bat.toJSONString());
			file.close();
			fr.close();
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}