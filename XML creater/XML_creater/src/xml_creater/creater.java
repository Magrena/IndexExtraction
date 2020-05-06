package xml_creater;
import java.io.*;

public class creater 
{
	public static void main(String args[])
	{
        String str = new String("Welcome-to-Runoob");
        char name[];
        char page[];
        
          try 
          {
                BufferedReader in = new BufferedReader(new FileReader("test.txt"));
                BufferedWriter out = new BufferedWriter(new FileWriter("test.xml"));
                
                out.write("<index>\n"+"\t<file>test.pdf</file>\n");
                String str1;
                while ((str1 = in.readLine()) != null) 
                {
                	out.write("\t\t"+"<entry>\n");	//every line has a <entry>
	                System.out.println(str1);
	                
	            	//str1=str1.replace(" ", "");  //clear all space character “ ”
	         
	            	String retval[]= str1.split(",");
	            	out.write("\t\t\t<phrase>"+retval[0]+"</phrase>"+"\n");
	            	
	            	out.write("\t\t\t<pagenumbers>\n");
	            	for(int i = 1; i<retval.length;i++)
	            	{
	            		retval[i]=retval[i].replace(" ", "");  //clear all space character “ ”
	                	out.write("\t\t\t\t<number>"+retval[i]+"</number>\n");
	            	}
	            	out.write("\t\t\t</pagenumbers>\n");
	            	out.write("\t\t"+"</entry>\n");
            	
                }
                
                out.write("</index>");
                in.close();
                out.close();
          } 
          catch (IOException e) {
        	  System.out.println("error");
            }
        
        /*
        System.out.println("- 分隔符返回值 :" );
        for (String retval: str.split("-"))
        {
            System.out.println(retval);
        }
        */
 

    }
}
