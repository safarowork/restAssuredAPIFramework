package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class User_DataProviders {
	
	//Returns all the data
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
	//	String path = "E:\\Eclipse_New\\RestAssured_Framework\\testData\\UserData.xlsx";
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rowcount = xl.getRowCount("User");
		int cellcount = xl.getCellCount("User", 1);
		
		String data[][] = new String[rowcount][cellcount];
		
		for(int r=1;r<=rowcount;r++)
		{
			for(int c=0;c<cellcount;c++)
			{
				data[r-1][c]= xl.getCellData("User", r, c);
			}
		}
		return data;
	}
	
	//Returns only usernames
	@DataProvider(name="userNames")
	public String[] getUserNames() throws IOException
	{
	//	String path = "E:\\Eclipse_New\\RestAssured_Framework\\testData\\UserData.xlsx";
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rowcount = xl.getRowCount("User");
		
		String username[] = new String[rowcount];
		for(int r=1;r<=rowcount;r++)
		{
			username[r-1] = xl.getCellData("User", r, 1);
		}
		
		return username;
	}
	

}
