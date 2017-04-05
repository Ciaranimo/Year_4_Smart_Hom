import java.io.*;
import org.omg.CORBA.*;
import SmartHome.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.Properties;

public class SmartHomeServer{

	public static void main (String args[]) {
		try{
			NameComponent nc[] = new NameComponent[1];

	    	// create and initialize the ORB
      Properties props = new Properties();
			// Set port for server

      props.put("org.omg.CORBA.ORBInitialPort", "1050");
	   	ORB orb = ORB.init(args, props);

			SmartHomeServant smartHomeRef = new SmartHomeServant();

			//connecting the servant to the orb
			orb.connect(smartHomeRef);
	   		System.out.println("Orb connected." + orb);

				//Locate Naming Service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	   		System.out.println("Found NameService.");

			//Next you need to convert this reference into a NamingContext reference
			//so that you can invoke the methods of the NamingContext interface.
			//This is achieved by using the narrow function of the helper class
			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

			// Add Lights context to root
			nc[0] = new NameComponent("Light Context", "Context");
			NamingContext LightsCtx = rootCtx.bind_new_context(nc);
			System.out.println("Lights bound to root");

			// Add Heat context to root
			nc[0] = new NameComponent("Heat Context", "Context");
			NamingContext HeatCtx = rootCtx.bind_new_context(nc);
			System.out.println("Heat bound to root");

			// Add Door context to root
			nc[0] = new NameComponent("Door Context", "Context");
			NamingContext DoorCtx = rootCtx.bind_new_context(nc);
			System.out.println("Door bound to root");

			// Add ALarm context to root
			nc[0] = new NameComponent("Alarm Context", "Context");
			NamingContext AlarmCtx = rootCtx.bind_new_context(nc);
			System.out.println("Alarm bound to root");

			//Add LightsObject object to Lights context
			nc[0] = new NameComponent("Light Object", "Object");
			LightsCtx.rebind(nc, LightsCtx);

			LightsCtx.rebind(nc, smartHomeRef);

			System.out.println("Light Object added to Lights context");

			//Add HeatObject object to Heat context
			nc[0] = new NameComponent("Heat Object", "Object");
			HeatCtx.rebind(nc, HeatCtx);
			HeatCtx.rebind(nc, smartHomeRef);

			System.out.println("Heat Object added to Heat context");

			//Add DoorObject object to Doors context
			nc[0] = new NameComponent("Door Object", "Object");
			DoorCtx.rebind(nc, DoorCtx);
			DoorCtx.rebind(nc, smartHomeRef);

			System.out.println("Door Object added to Door context");

			//Add AlarmObject object to Alarm context
			nc[0] = new NameComponent("Alarm Object", "Object");
			AlarmCtx.rebind(nc, AlarmCtx);
			AlarmCtx.rebind(nc, smartHomeRef);

			System.out.println("Alarm Object added to Alarm context");



    // stand by for method call from client
			orb.run();



		} catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
		}

	}
}
