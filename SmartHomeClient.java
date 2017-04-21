import java.util.Properties;
import java.io.*;
import org.omg.CORBA.*;
import SmartHome.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;
import com.google.gson.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SmartHomeClient
{
    public static NamingContextExt rootCtx;
    public static void list(NamingContext n, String indent) {
        try {
                final int batchSize = 1;
                BindingListHolder bList = new BindingListHolder() ;
                BindingIteratorHolder bIterator = new BindingIteratorHolder();
                n.list(batchSize, bList, bIterator) ;
                if (bList.value.length != (int) 0)
                    for (int i = 0; i < bList.value.length; i++) {
                        BindingHolder bh = new BindingHolder(bList.value[0]);
                        do {
                            String stringName = rootCtx.to_string(bh.value.binding_name);
                            System.out.println(indent + stringName) ;
                            if (bh.value.binding_type.value() == BindingType._ncontext) {
                                String _indent = "----" + indent;

                                NameComponent [] name = rootCtx.to_name(stringName);
                                NamingContext sub_context = NamingContextHelper.narrow(n.resolve(name));
                                list(sub_context, _indent);
                            }
                        } while (bIterator.value.next_one(bh));
                    }
                else
                    System.out.println(indent + "Nothing more in this context.") ;
        }
        catch (Exception e) {
            System.out.println("An exception occurred. " + e) ;
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {


      	try{
              NameComponent nc[]= new NameComponent[2];

              // Set port for server
              Properties props = new Properties(); props.put("org.omg.CORBA.ORBInitialPort", "1050");
              ORB orb = ORB.init(args, props);

               // create and initialize the ORB
              org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

              rootCtx = NamingContextExtHelper.narrow(objRef);
              // Call the list function to iterate through the Name Space

              list(rootCtx, "---->");
              System.out.println("Printing Done");

              // ****** //
              //Search the Name Space for object with add method bound to it

              nc[0] = new NameComponent("Light Context", "Context");
              nc[1] = new NameComponent("Light Object", "Object");


              org.omg.CORBA.Object objRefLight = rootCtx.resolve(nc);
              String start = "";
              do{
              // CLIENT INPUT
              JOptionPane.showMessageDialog(null,"Smart Home is ready", "Smart Home", JOptionPane.PLAIN_MESSAGE);

              //LIGHTS
              String status = JOptionPane.showInputDialog("Lights are available, do you want them on or off?");
              
              String lightJson = "{'onOff': '" + status +"' }";

              House smartHomeRef = HouseHelper.narrow(objRefLight);

              String lights = smartHomeRef.lights(lightJson);

              JOptionPane.showMessageDialog(null, lights, "Smart Home", JOptionPane.PLAIN_MESSAGE);

              // ALARM
              status = JOptionPane.showInputDialog("Alarm is available, do you want it armed or disarmed?");

              String alarmJson = "{'onOff': '" + status +"' }";

              String alarm = smartHomeRef.alarm(alarmJson);

              JOptionPane.showMessageDialog(null, alarm, "Smart Home", JOptionPane.PLAIN_MESSAGE);

              // DOOR
              status = JOptionPane.showInputDialog("Door is available, do you want it locked or unlocked?");

              String doorJson = "{'onOff': '" + status +"' }";

              String door = smartHomeRef.door(doorJson);

              JOptionPane.showMessageDialog(null, door, "Smart Home", JOptionPane.PLAIN_MESSAGE);

              //HEATING
              status = JOptionPane.showInputDialog("Heating is available, do you want it on or off?");
              String heatingJson = "{'onOff': '" + status +"' }";

              String heating = smartHomeRef.heating(heatingJson);

              JOptionPane.showMessageDialog(null, heating, "Smart Home", JOptionPane.PLAIN_MESSAGE);

              start = JOptionPane.showInputDialog("Do you wish to use Smart Home again? yes / no ?");

            }while (start.equalsIgnoreCase("yes"));

              // ALL

              } catch (Exception e) {
                  System.out.println("ERROR : " + e) ;
                  e.printStackTrace(System.out);
              }


	}
}
