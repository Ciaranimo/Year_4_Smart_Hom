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

			// Add context 1 to root
			nc[0] = new NameComponent("Context 1", "Context");
			NamingContext Ctx1 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 1' added to Name Space.");

			// Add context 2 to root
			nc[0] = new NameComponent("Context 2", "Context");
			NamingContext Ctx2 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 2' added to Name Space.");

			// Add context 3 to root
			nc[0] = new NameComponent("Context 3", "Context");
			NamingContext Ctx3 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 3' added to Name Space.");


			// Add Sub Context 2  to Context 1
			nc[0] = new NameComponent("Sub-Context 2", "Context");
			NamingContext SubCtx2 = Ctx1.bind_new_context(nc);
			System.out.println("Context 'Sub-Context 2' added to Context 1.");

			//Add Obj 1  to sub context 2
			nc[0] = new NameComponent("Object 1", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			SubCtx2.rebind(nc, SubCtx2);
			System.out.println("Object 'Object 1' added to Sub Context 2.");

			//Add Obj 1  to Context 1
			nc[0] = new NameComponent("Object 1", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			Ctx1.rebind(nc, Ctx1);
			System.out.println("Object 'Object 1' added to Context 1.");

			//Add Obj 2  to context 2
			nc[0] = new NameComponent("Object 2", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			Ctx2.rebind(nc, Ctx2);
			System.out.println("Object 'Object 2' added to Context 2.");

			//Add context sub context 2 to Context 1
			nc[0] = new NameComponent("Sub Context 1", "Context");
			NamingContext SubCtx1 = Ctx2.bind_new_context(nc);
			System.out.println("Context 'Sub Conetxt 1' added to Context 2.");

			//Add Obj 3  to sub context 1
			nc[0] = new NameComponent("Object 3", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			SubCtx1.rebind(nc, SubCtx1);
			System.out.println("Object 'Object 3' added to Sub Context 1.");

			//Add Obj 4  to Context 2
			// *** Method Bound to Object 4 *******
			nc[0] = new NameComponent("Object 4", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			Ctx2.rebind(nc, Ctx2);
		// ADD METHOD BIND
			Ctx2.rebind(nc, smartHomeRef);
			System.out.println("Object 'Object 4' added to Context 2.");

			//Add Obj 5  to Context 3
			nc[0] = new NameComponent("Object 5", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			Ctx3.rebind(nc, Ctx3);
			System.out.println("Object 'Object 5' added to Context 3.");

    // stand by for method call from client
			orb.run();



		} catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
		}

	}
}
