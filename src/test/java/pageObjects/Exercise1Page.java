package pageObjects;


import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Exercise1Page {


    public Exercise1Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> allvalues = makeValueList();//Store all the values in a list

    @FindBy(how = How.NAME, using = "lbl_val1")
    private WebElement lbl_val1;


    @FindBy(how = How.NAME, using = "lbl_val2")
    private WebElement lbl_val2;


    @FindBy(how = How.NAME, using = "lbl_val3")
    private WebElement lbl_val3;


    @FindBy(how = How.NAME, using = "lbl_val4")
    private WebElement lbl_val4;


    @FindBy(how = How.NAME, using = "lbl_val5")
    private WebElement lbl_val5;


    @FindBy(how = How.NAME, using = "lbl_ttl_val")
    private WebElement lbl_ttl_val;

    private List<WebElement> makeValueList(){
        List<WebElement> value_list = new ArrayList<>();//Store all the values in a list
        value_list.add(lbl_val1);
        value_list.add(lbl_val2);
        value_list.add(lbl_val3);
        value_list.add(lbl_val4);
        value_list.add(lbl_val5);
        value_list.add(lbl_ttl_val);
        return value_list;
    }

    public boolean checkNumberOfValues() {
    //loop through all value to verify if it is displayed
        for(WebElement value: allvalues){
            if(!value.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public boolean areValuesGreaterThanzero() {
        //loop through all value to verify if it's value is greater than 0
        for(WebElement value: allvalues){
            if(value.getText().equals("$0.00")||value.getText().equals("$0")){
                return false;
            }
        }
        return true;
    }

    public boolean areValueCurrencies() {
        //loop through all value to verify if it's value is formatted as currency
        for(WebElement value: allvalues){
            if(!isCurrency(value.getText())){
                return false;
            }
        }
        return true;
    }

    public boolean checkTotalBlance() {
        //loop through all value to verify if it's value is greater than 0
        double sum = 0;
        double total_balance=0;
        for(WebElement value: allvalues){
            String s = value.getText().replace("$","");//remove '$'
            s = s.replace(",","");
            double d = Double.parseDouble(s);
            if(value == lbl_ttl_val){
                total_balance = d;
            }
            else{
                sum += d;
            }
        }

        return (sum == total_balance);
    }



    private static boolean isCurrency(String s){
        int n = s.length()-1;
        //check if the string starts with $
        if(s.charAt(0) != '$'){return false;}
        //check if the end with two digits
        if(!Character.isDigit(s.charAt(n))){return false;}
        n--;
        if(!Character.isDigit(s.charAt(n))){return false;}
        n--;
        if(s.charAt(n) == '.' ){return false;}
        n--;
        //check if rest is in "xx,xxx,xxx" form
        int counter = 0;
        while(n>0){
            if(counter == 3){
                if(s.charAt(n) != ','){
                    return false;
                }
            }
            else{
                if(!Character.isDigit(s.charAt(n))) {return false;}
            }
            n --;
            counter = (counter+1)%4;

        }
        // verify the leading digit is not ',' or '0'
        return !(s.charAt(1) =='0' || s.charAt(1) == ',');


    }


}
