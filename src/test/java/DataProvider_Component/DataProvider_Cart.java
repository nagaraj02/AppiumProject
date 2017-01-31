package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Cart {
	
	
	@DataProvider(name="dp_AddCart")
	public static Iterator<Object[]> getAddCartdata() throws IOException
	{
		List<Object[]> Obj = flagRowCount("AddCart");
		return Obj.iterator();
		
	}
	
	@DataProvider(name="dp_DeleteCart")
	public static Iterator<Object[]> getDeleteCartdata() throws IOException
	{
	
		List<Object[]> Obj = flagRowCount("DeleteCart");	
		return Obj.iterator();
		
	}
	

	public static List<Object[]> flagRowCount(String Scriptname) throws IOException
	{
		
		ExcelReadWrite xl= new ExcelReadWrite("D:\\BB_Project\\TestData\\TestData.xls");
		HSSFSheet Scenario_Cart = xl.Setsheet("Scenario_Cart");
		
		int RowCount = xl.getrowcount(Scenario_Cart);
		int ColCount = xl.getcolcount(Scenario_Cart, 0);
		
		List<Object[]> list_Cart= new ArrayList<Object[]>();
		
		for(int irow=1;irow<=RowCount;irow++)
		{
			String Execute_Flag = xl.Readvalue(Scenario_Cart, irow, "Execute_Flag");
			String Script_name = xl.Readvalue(Scenario_Cart, irow, "Script_name");
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(Scriptname)))
			{
				Map<String,String> dcMap =new HashMap<String,String>();
				
				for(int jCol=0;jCol<=ColCount;jCol++)
				{
					
					String Key = xl.Readvalue(Scenario_Cart, 0, jCol);
					String Value = xl.Readvalue(Scenario_Cart, irow, jCol);
					
					dcMap.put(Key, Value);
					
					
				}//end of col loop
				
				Object[] x= new Object[1];
				x[0]=dcMap;
				
				list_Cart.add(x);
				
				
			}//end of if condition
			
			
		}//end of for loop
		
		return list_Cart;
		
		
	}
	
}
