package test;
import java.io.*;
import java.util.ArrayList;

public class xmlCreater 
{
	public static void getXML(String filenameTemp)throws IOException
	{

		
		
		
		BufferedWriter out = new BufferedWriter(new FileWriter("test.xml"));
 
		out.write("<index>\n"+"\t<file>test.pdf</file>\n");
		
		ArrayList<String> str1 = new ArrayList<String>();
		str1 = FileReader.openfile(filenameTemp);
		
		String list;
		int count_for_position = 0;
		float y_axis = text_position.y_axis(count_for_position);
		float x_axis = text_position.x_axis(count_for_position);
		float x_axis_initial = text_position.x_axis(count_for_position);
		float y_axis_initial = text_position.y_axis(count_for_position);
		boolean pagenumber = false;
		boolean phrase_already = false;
		boolean phrase_already_sub = false;
		boolean page_already = false;
		boolean subentries_already = false;
		boolean reference_exist = false;
		
		while(Math.abs(y_axis_initial-text_position.y_axis(count_for_position))<1)
		{
			count_for_position++;	
		}
		y_axis_initial=text_position.y_axis(count_for_position);
		x_axis_initial = text_position.x_axis(count_for_position);

		int j=0;
		
		while(j<str1.size())
		{
		int i=1;	//array index: normally it is the second part of a line, contains page number
		reference_exist = false;
//		String line = str1.get(j);
//		line = line.replace(" ", "");
//		char b =line.charAt(0);
//		String aaa = b+"";
//		System.out.println(line+"\n");

		
		if(j>0&&j<str1.size()-1)
		{
			while(Math.abs(y_axis-text_position.y_axis(count_for_position))<1)
			{
				count_for_position++;	
			}
			y_axis=text_position.y_axis(count_for_position);
			x_axis = text_position.x_axis(count_for_position);
			if(y_axis>y_axis_initial)
				y_axis_initial = y_axis;
			else if(Math.abs(y_axis-y_axis_initial)>100)
			{
				y_axis_initial = y_axis;
				x_axis_initial = x_axis;
			}
			else
			{
				y_axis_initial = y_axis;
				x_axis_initial = x_axis;
			}
//			System.out.print(y_axis+"\t\t");
			String a = text_position.letter(count_for_position);
//			System.out.print(a+"\t\t");
//			System.out.println(str1.get(j)+"\n");
		}
		
		if(x_axis>x_axis_initial+5)
		{
			phrase_already = true;
			String tmp=str1.get(j);
			if(tmp.startsWith("0")||tmp.startsWith("1")||tmp.startsWith("2")||tmp.startsWith("3")||tmp.startsWith("4")||tmp.startsWith("5")||tmp.startsWith("6")||tmp.startsWith("7")||tmp.startsWith("8")||tmp.startsWith("9")) 
			{
				//String retval[] = new String[99] ;;
//				if(str1.get(j).contains(","))
				String retval[] = str1.get(j).split(",");
//				else
//					retval[0] = str1.get(j);
				if(pagenumber)
				{
					out.write(retval[0]+"</number>\n");
					pagenumber = false;
					for(int m=1;m<retval.length;m++)
					{
						retval[m]=retval[m].replace(" ", "");  //clear all space character “ ”
						out.write("\t\t\t\t<number>"+retval[m]+"</number>\n");
					}
				}
				else
					for(int m=0;m<retval.length;m++)
					{
						retval[m]=retval[m].replace(" ", "");  //clear all space character “ ”
						out.write("\t\t\t\t<number>"+retval[m]+"</number>\n");
					}
			}
			else 
			{
				if(page_already)
				{
					out.write("\t\t\t</pagenumbers>\n");
					page_already =false;
				}
				
				if(subentries_already==false)
				{
					out.write("\t\t\t<subentries>\n");	
					subentries_already= true;
				}	
				if(phrase_already_sub)
					out.write("\t\t"+"</entry>\n");
				
				phrase_already_sub = true;
				out.write("\t\t"+"<entry>\n");	//every line has a <entry>
				//wiederholung
				if(str1.get(j).contains(","))
				{

					phrase_already = true;
					
					String retval[]= str1.get(j).split(",");
					out.write("\t\t\t<phrase>"+retval[0]);
					

					while(i<retval.length&&retval[i]!=null)
					{
						if (!(retval[i].contains("0")||retval[i].contains("1")||retval[i].contains("2")||retval[i].contains("3")||retval[i].contains("4")||retval[i].contains("5")||retval[i].contains("6")||retval[i].contains("7")||retval[i].contains("8")||retval[i].contains("9")||retval[i].contains("see")))
						{
							out.write(","+retval[i]);//write the phrase of the second part
							i++;	//page number begins now at the third part of a line
						}
						else if(retval[i].contains("see"))
						{
							out.write("</phrase>"+"\n");
							out.write("\t\t\t<reference>"+retval[i]+"</reference>");
							reference_exist = true;
							break;
						}
						else
							break;
					}
					if(!reference_exist)
						out.write("</phrase>"+"\n");
					
					if(i<retval.length&&(retval[i].contains("0")||retval[i].contains("1")||retval[i].contains("2")||retval[i].contains("3")||retval[i].contains("4")||retval[i].contains("5")||retval[i].contains("6")||retval[i].contains("7")||retval[i].contains("8")||retval[i].contains("9")))
					{
						page_already = true;
//						int n=0;
//						while(n<str1.size()-j)
//						{
//							String tmp=str1.get(j+n+1);
//							if(tmp.startsWith("0")||tmp.startsWith("1")||tmp.startsWith("2")||tmp.startsWith("3")||tmp.startsWith("4")||tmp.startsWith("5")||tmp.startsWith("6")||tmp.startsWith("7")||tmp.startsWith("8")||tmp.startsWith("9")) 
//							{
//								//System.out.print("aaa");
//								n++;
//							}
//							else	break;
//						}
					
					out.write("\t\t\t<pagenumbers>\n");
					while(i<retval.length-1)	//length-1: we leave one pagenumber here and don't write it down
					{
						retval[i]=retval[i].replace(" ", "");  //clear all space character “ ”
						out.write("\t\t\t\t<number>"+retval[i]+"</number>\n");
						i++;
					}
					
//						if(n!=0)
//						{
						if(retval[retval.length-1].contains("–"))
						{
							retval[retval.length-1]=retval[retval.length-1].replace(" ", "");
							char last_page = retval[retval.length-1].charAt(retval[retval.length-1].length()-1);
							if(last_page=='–')
							{
								pagenumber = true;
								out.write("\t\t\t\t<number>"+retval[retval.length-1]);
							}
							else
							{
								pagenumber = false;
								retval[retval.length-1]=retval[retval.length-1].replace(" ", "");  //clear all space character “ ”
								out.write("\t\t\t\t<number>"+retval[retval.length-1]+"</number>\n");
							}
							
						}
						else
						{
							
							pagenumber = false;
							retval[retval.length-1]=retval[retval.length-1].replace(" ", "");  //clear all space character “ ”
							out.write("\t\t\t\t<number>"+retval[retval.length-1]+"</number>\n");
						}
//							for (int k =1; k<=n; k++)
//							{
//								
//								tmp=tmp+str1.get(j+k);
//							}
//							String tmp1[]= tmp.split(",");
//							for(int m=0;m<tmp1.length;m++)
//							{
//								tmp1[m]=tmp1[m].replace(" ", "");  //clear all space character “ ”
//								out.write("\t\t\t\t<number>"+tmp1[m]+"</number>\n");
//							}
//							j=j+n;	//skip the lines which we have already write down
//						}
					}
				}
					
				
			}
		}	
				
		else if(str1.get(j).contains(","))
		{
			if(page_already)
			{
				out.write("\t\t\t</pagenumbers>\n");
				page_already =false;
			}
			if(phrase_already_sub)
			{
				out.write("\t\t"+"</entry>\n");
				phrase_already_sub = false;
			}
			if(subentries_already)
			{
				subentries_already=false;
				out.write("\t\t\t</subentries>\n");
				
			}
			if(phrase_already)
			{	
				out.write("\t\t"+"</entry>\n");
				phrase_already = false;
			}
			phrase_already = true;
			out.write("\t\t"+"<entry>\n");	//every line has a <entry>
			String retval[]= str1.get(j).split(",");
			out.write("\t\t\t<phrase>"+retval[0]);
			

			while(i<retval.length&&retval[i]!=null)
			{
				if (!(retval[i].contains("0")||retval[i].contains("1")||retval[i].contains("2")||retval[i].contains("3")||retval[i].contains("4")||retval[i].contains("5")||retval[i].contains("6")||retval[i].contains("7")||retval[i].contains("8")||retval[i].contains("9")||retval[i].contains("see")))
				{
					out.write(","+retval[i]);//write the phrase of the second part
					i++;	//page number begins now at the third part of a line
				}
				else if(retval[i].contains("see"))
				{
					out.write("</phrase>"+"\n");
					out.write("\t\t\t<reference>"+retval[i]+"</reference>");
					reference_exist = true;
					break;
				}
				else
					break;
			}
			if(!reference_exist)
				out.write("</phrase>"+"\n");
			
			if(i<retval.length&&(retval[i].contains("0")||retval[i].contains("1")||retval[i].contains("2")||retval[i].contains("3")||retval[i].contains("4")||retval[i].contains("5")||retval[i].contains("6")||retval[i].contains("7")||retval[i].contains("8")||retval[i].contains("9")))
			{
				page_already = true;
//			int n=0;
//			while(n<str1.size()-j)
//			{
//				String tmp=str1.get(j+n+1);
//				if(tmp.startsWith("0")||tmp.startsWith("1")||tmp.startsWith("2")||tmp.startsWith("3")||tmp.startsWith("4")||tmp.startsWith("5")||tmp.startsWith("6")||tmp.startsWith("7")||tmp.startsWith("8")||tmp.startsWith("9")) 
//				{
//					//System.out.print("aaa");
//					n++;
//				}
//				else	break;
//			}
			
			out.write("\t\t\t<pagenumbers>\n");
			while(i<retval.length-1)	//length-1: we leave one pagenumber here and don't write it down
			{
				retval[i]=retval[i].replace(" ", "");  //clear all space character “ ”
				out.write("\t\t\t\t<number>"+retval[i]+"</number>\n");
				i++;
			}
			
//			if(n!=0)
//			{
				if(retval[retval.length-1].contains("–"))
				{
					retval[retval.length-1]=retval[retval.length-1].replace(" ", "");
					char last_page = retval[retval.length-1].charAt(retval[retval.length-1].length()-1);
					if(last_page=='–')
					{
						pagenumber = true;
						out.write("\t\t\t\t<number>"+retval[retval.length-1]);
					}
					else
					{
						pagenumber = false;
						retval[retval.length-1]=retval[retval.length-1].replace(" ", "");  //clear all space character “ ”
						out.write("\t\t\t\t<number>"+retval[retval.length-1]+"</number>\n");
					}
					
				}
				else
				{
					
					pagenumber = false;
					retval[retval.length-1]=retval[retval.length-1].replace(" ", "");  //clear all space character “ ”
					out.write("\t\t\t\t<number>"+retval[retval.length-1]+"</number>\n");
				}
//				for (int k =1; k<=n; k++)
//				{
//					
//					tmp=tmp+str1.get(j+k);
//				}
//				String tmp1[]= tmp.split(",");
//				for(int m=0;m<tmp1.length;m++)
//				{
//					tmp1[m]=tmp1[m].replace(" ", "");  //clear all space character “ ”
//					out.write("\t\t\t\t<number>"+tmp1[m]+"</number>\n");
//				}
//				j=j+n;	//skip the lines which we have already write down
//			}
			}
			

		}
		j++;
		
		
		}
		
if(!reference_exist)
	out.write("\t\t\t</pagenumbers>\n");

out.write("\t\t"+"</entry>\n");
out.write("</index>");

out.close();

System.out.print("Converting successful!");

    }
}