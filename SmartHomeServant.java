import SmartHome.*;
import java.util.*;

class SmartHomeServant extends _HouseImplBase{
  final Scanner scanner = new Scanner(System.in);
  String statusString = "";
  Boolean status = false;

    public String lights(){
      if(status == false){
        System.out.println("Lights are off, do you want turn them on? Y/N");
        String lightChange = scanner.nextLine();
        if(lightChange.equals("Y")){
          statusString = "Lights are on";
          status = true;
        }
        else{
          System.out.println("Lights remain off");
        }
      }
      else if(status == true){
        System.out.println("Lights are on, do you want turn off the lights");
        String lightChange = scanner.nextLine();
        if(lightChange.equals("Y")){
          System.out.println("Lights are now off");
          status = false;
          String statusString = "Lights are off";
        }
      }
      else{
        System.out.println("Error");
      }

      return statusString;
    }
  }
