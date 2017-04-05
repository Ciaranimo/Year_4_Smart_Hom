import SmartHome.*;

class SmartHomeServant extends _HouseImplBase{


  public String lights(String status){
    String result;
    if(status.equals("on")){
      result = "Lights on";
      return result;
    }
    else{
      result = "Lights off";
      return result;
    }
  }

  }
