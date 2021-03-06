package DataProviders;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestNGDataProvider {

    @DataProvider
    public Object[] add_to_Cart_data(Method m){

        if(m.getName().equalsIgnoreCase("select_required_phone_checkbox")){
            String data[]={"POCO"};
            return data;

        }
        if(m.getName().equalsIgnoreCase("choose_required_phone")){
            String data[]={"POCO M3 (Power Black, 64 GB)"};
            return data;

        }
        if(m.getName().equalsIgnoreCase("Enter_Phone_Number")){
            String data[]={"8700896892"};
            return data;

        }  if(m.getName().equalsIgnoreCase("Enter_Password")){
            String data[]={"sachin@1234"};
            return data;

        }

        return null;
    }

}
