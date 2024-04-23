package web.steps;
import web.pages.ResetPasswordPage;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
public class ResetPasswordSteps {
    protected ResetPasswordPage resetPasswordPage;
    public void verificationCode(String[] args){

        String str = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(String mobilePhone);
        char[] chars = str.toCharArray();
        char firstChar = chars[0];
        char secondChar = chars[1];
        char thirdChar = chars[2];
        char fourthChar = chars[3];
        char fifthChar = chars[4];
        char sixthChar = chars[5];
      //  System.out.println("Первый символ: " + firstChar);
      //  System.out.println("Второй символ: " + secondChar);
     //   System.out.println("Третий символ: " + thirdChar);
     //   System.out.println("Четвертый символ: " + fourthChar);
      //  System.out.println("Пятый символ: " + fifthChar);
     //   System.out.println("Шестой символ: " + sixthChar);

    }
}

