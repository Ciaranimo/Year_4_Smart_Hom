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

  public String alarm(String status){
    String result;
    if(status.equals("armed")){
      result = "Alarm armed";
      return result;
    }
    else{
      result = "Alarm disarmed";
      return result;
    }
  }

  public String door(String status){
    String result;
    if(status.equals("locked")){
      result = "Door locked";
      return result;
    }
    else{
      result = "Door unlocked";
      return result;
    }
  }

  public String heating(String status){
    String result;
    if(status.equals("on")){
      result = "Heating on";
      return result;
    }
    else{
      result = "Heating off";
      return result;
    }
  }

  }
