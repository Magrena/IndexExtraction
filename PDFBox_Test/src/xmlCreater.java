//package xml_creater;
import java.io.*;
import java.util.ArrayList;

public class xmlCreater 
{
	public static void getXML(String filenameTemp)throws IOException
	{
		File file = new File(filenameTemp);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader in = new BufferedReader(isr); 
		//BufferedReader in = new BufferedReader(new FileReader("test.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("test.xml"));
 
		out.write("<index>\n"+"\t<file>test.pdf</file>\n");
		
		ArrayList<String> str1 = new ArrayList<String>();
		String list;
		while ((list = in.readLine()) != null) 
		{
			str1.add(list);
		}
		in.close();
		System.out.print(str1.get(1));
		int j=0;
		while(j<str1.size())
		{
		int i=1;	//array index: normally it is the second part of a line, contains page number 
		 
		if(str1.get(j).contains(","))
		{
			out.write("\t\t"+"<entry>\n");	//every line has a <entry>
			String retval[]= str1.get(j).split(",");
			out.write("\t\t\t<phrase>"+retval[0]);


			//if the second part is still word rather than page nummer
			if (!(retval[1].contains("0")||retval[1].contains("1")||retval[1].contains("2")||retval[1].contains("3")||retval[1].contains("4")||retval[1].contains("5")||retval[1].contains("6")||retval[1].contains("7")||retval[1].contains("8")||retval[1].contains("9")))
			{
				out.write(","+retval[1]+"</phrase>"+"\n");//write the phrase of the second part
				i++;	//page number begins now at the third part of a line
			}
			else
				out.write("</phrase>"+"\n");
			
			int n=0;
			while(n<str1.size()-j)
			{
				String tmp=str1.get(j+n+1);
				if(tmp.startsWith("0")||tmp.startsWith("1")||tmp.startsWith("2")||tmp.startsWith("3")||tmp.startsWith("4")||tmp.startsWith("5")||tmp.startsWith("6")||tmp.startsWith("7")||tmp.startsWith("8")||tmp.startsWith("9")) 
				{
					System.out.print("aaa");
					n++;
				}
				else	break;
			}
			out.write("\t\t\t<pagenumbers>\n");
			while(i<retval.length-1)	//length-1: we leave one pagenumber here and don't write it down
			{
				retval[i]=retval[i].replace(" ", "");  //clear all space character “ ”
				out.write("\t\t\t\t<number>"+retval[i]+"</number>\n");
				i++;
			}
			
			if(n!=0)
			{
				String tmp;
				if(retval[retval.length-1].contains("–"))
					tmp=retval[retval.length-1];
				else
					tmp=retval[retval.length-1]+",";
				for (int k =1; k<=n; k++)
				{
					
					tmp=tmp+str1.get(j+k);
				}
				String tmp1[]= tmp.split(",");
				for(int m=0;m<tmp1.length;m++)
				{
					tmp1[m]=tmp1[m].replace(" ", "");  //clear all space character “ ”
					out.write("\t\t\t\t<number>"+tmp1[m]+"</number>\n");
				}
				j=j+n;	//skip the lines which we have already write down
			}
			else
			{
				retval[retval.length-1]=retval[retval.length-1].replace(" ", "");  //clear all space character “ ”
				out.write("\t\t\t\t<number>"+retval[retval.length-1]+"</number>\n");
			}
			
			out.write("\t\t\t</pagenumbers>\n");
			out.write("\t\t"+"</entry>\n");
		}
		j++;
		}
    
out.write("</index>");

out.close();

 

    }
}