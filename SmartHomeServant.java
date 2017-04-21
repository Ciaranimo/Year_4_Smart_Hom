import SmartHome.*;
import com.google.gson.*;

class SmartHomeServant extends _HouseImplBase{

Gson gson = new Gson();

  public String lights(String status){


    gson = new GsonBuilder().disableHtmlEscaping().create();
    JsonObject jobj = new Gson().fromJson(status, JsonObject.class);
    String onOff  = jobj.get("onOff").getAsString();

      String result;
  //  String appliance = jobj.get("device").toString();

    if(onOff.equalsIgnoreCase("on")){
      result = "Message from Servant: Lights on";
      return result;
    }
    else if(onOff.equalsIgnoreCase("off")){
      result = "Message from Servant: Lights off";
      return result;
    }
    else{
      result = "Please enter with 'on' or 'off'";
      return result;
    }
  }

  public String alarm(String status){
    gson = new GsonBuilder().disableHtmlEscaping().create();
    JsonObject jobj = new Gson().fromJson(status, JsonObject.class);
    String onOff  = jobj.get("onOff").getAsString();

      String result;
  //  String appliance = jobj.get("device").toString();

    if(onOff.equalsIgnoreCase("armed")){
      result = "Message from Servant: Alarm armed";
      return result;
    }
    else if(onOff.equalsIgnoreCase("disarmed")){
      result = "Message from Servant: Alarm disarmed";
      return result;
    }
    else{
      result = "Please enter with 'on' or 'off'";
      return result;
    }
  }

  public String door(String status){
    gson = new GsonBuilder().disableHtmlEscaping().create();
    JsonObject jobj = new Gson().fromJson(status, JsonObject.class);
    String onOff  = jobj.get("onOff").getAsString();

      String result;
  //  String appliance = jobj.get("device").toString();

    if(onOff.equalsIgnoreCase("locked")){
      result = "Message from Servant: Door locked";
      return result;
    }
    else if(onOff.equalsIgnoreCase("unlocked")){
      result = "Message from Servant: Door unlocked";
      return result;
    }
    else{
      result = "Please enter with 'on' or 'off'";
      return result;
    }
  }

  public String heating(String status){
    gson = new GsonBuilder().disableHtmlEscaping().create();
    JsonObject jobj = new Gson().fromJson(status, JsonObject.class);
    String onOff  = jobj.get("onOff").getAsString();

      String result;
  //  String appliance = jobj.get("device").toString();

    if(onOff.equalsIgnoreCase("on")){
      result = "Message from Servant: Heating on";
      return result;
    }
    else if(onOff.equalsIgnoreCase("off")){
      result = "Message from Servant: Heating off";
      return result;
    }
    else{
      result = "Please enter with 'on' or 'off'";
      return result;
    }

  }
}
