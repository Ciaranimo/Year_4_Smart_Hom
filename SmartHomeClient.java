import java.util.Properties;
import java.io.*;
import org.omg.CORBA.*;
import SmartHome.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;


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

            nc[0] = new NameComponent("Context 2", "Context");
            nc[1] = new NameComponent("Object 4", "Object");

            // Attempt at getting user input to add two numbers
            /*
            org.omg.CORBA.Object objRefAdd = rootCtx.resolve(nc);
            Add practicalTestRef = AddHelper.narrow(objRefAdd);

            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter a number: ");
            int a = reader.nextInt();
            System.out.println("Enter a number: ");
            int b = reader.nextInt();

            String add = practicalTestRef.addMethod(a, b);
            System.out.println(add);

            */
            org.omg.CORBA.Object objRefAdd = rootCtx.resolve(nc);
            House smartHomeRef = SmartHomeHelper.narrow(objRefAdd);

            String turnOnLights = smartHomeRef.turnOnLights();
            System.out.println(turnOnLights);


            } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
            }
	}
}
