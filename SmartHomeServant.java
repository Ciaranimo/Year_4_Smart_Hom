import SmartHome.*;
import java.util.*;

class SmartHomeServant extends _HouseImplBase
{
  final Scanner scanner = new Scanner(System.in);


    public Boolean lights(Boolean switch)
    {
      Boolean status = switch;
      if(status == false){
        System.out.println("Lights are off, do you want turn on the lights");
        String lightChange = scanner.nextLine();
        if(lightChange.equals("yes")){
          System.out.println("Lights are now on");
          status = true;
          return true;
        }
      }else if(status == true){
        System.out.println("Lights are on, do you want turn off the lights");
        String lightChange = scanner.nextLine();
        if(lightChange.equals("yes")){
          System.out.println("Lights are now off");
          status = false;
          return false;
      }
    } else{
      System.out.println("Error");
    }



}
