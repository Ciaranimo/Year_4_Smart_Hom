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
      Scanner sc = new Scanner(System.in);

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

            // CLIENT INPUT
            System.out.println(" Welcome to Smart Home ");

            //LIGHTS
            System.out.println("Lights are available, do you want them on or off?");
            String status = sc.nextLine();

            String lightJson = "{'onOff': '" + status +"' }";


            House smartHomeRef = HouseHelper.narrow(objRefLight);

            String lights = smartHomeRef.lights(lightJson);

            System.out.println(lights);
            // ALARM
            System.out.println("Alarm is available, do you want it armed or disarmed?");
            status = sc.nextLine();

            String alarmJson = "{'onOff': '" + status +"' }";

            String alarm = smartHomeRef.alarm(alarmJson);

            System.out.println(alarm);

            // DOOR
            System.out.println("Door is available, do you want it locked or unlocked?");
            status = sc.nextLine();

            String doorJson = "{'onOff': '" + status +"' }";

            String door = smartHomeRef.door(doorJson);

            System.out.println(door);

            //HEATING
            System.out.println("Heating is available, do you want it on or off?");
            status = sc.nextLine();

            String heatingJson = "{'onOff': '" + status +"' }";

            String heating = smartHomeRef.heating(heatingJson);

            System.out.println(heating);

            } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
            }
	}
}
