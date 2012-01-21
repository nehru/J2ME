
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.TimeZone;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStore;
 

public class Test extends MIDlet implements CommandListener{

 private String gName;
 private String gdatein, gdateout;
 private String carname;
 
 private Display display;
 public Form form,tform,carSelection,dateForm,confirmForm,recordForm,showForm;
 private Command ok,cancel,login,showCmd;
 private Image img,car_image;
  
 private TextField userName,password;
 private ChoiceGroup technology;
 private Command exit, choose,dateSelect,end,contactus,location,imageDisplay;
 private Command recordDisplay;
  
 private boolean firstTime;
 private StringItem mMessageItem,invoiceItem;
 private DateField datein, dateout;

 private Ticker ticker; 
 private Image car1,car2,car3,car4,car5,car6;
 private RecordStore r_store;

 
 private boolean valueSet = false;
 private String gmemory,received;
 private int index;
 
  public Test() {
    display = Display.getDisplay(this);
    form = new Form("Login");
    cancel = new Command("Cancel", Command.CANCEL, 2);
    ok = new Command("OK", Command.OK, 2);
    login = new Command("Login", Command.SCREEN, 2);
    exit = new Command("Exit", Command.SCREEN, 2);
    
    
    contactus = new Command("ContactUS", Command.SCREEN, 2);
    location = new Command("Location", Command.SCREEN, 2);
    imageDisplay = new Command("CarModel", Command.SCREEN, 2);
    recordDisplay = new Command("RecordDisplay", Command.SCREEN, 2);
    showCmd = new Command("show", Command.SCREEN, 2);
    
    dateSelect = new Command("Invoice", Command.SCREEN, 2);
    end = new Command("End", Command.SCREEN, 2);
    
    mMessageItem = new StringItem(null, "");
    invoiceItem = new StringItem(null, "");
    
      try
      {
        car_image = Image.createImage("/carRental.png");
        img = Image.createImage("/one.png");
      }catch(Exception e)
      {
        System.out.println(e.getMessage());
      }

    ticker = new Ticker ("      [Chevy Aveo  2 or 4 doors Automatic Transmission Air Conditioning AM/FM CD Player]    " +
            "       [Nissan Versa, Toyota Yaris  2 or 4 doors Automatic Transmission Air Conditioning AM/FM CD Player]  " +
            "       [Chevy Cobalt, Nissan Sentra, Toyota Corolla or similar 2 or 4 doors Automatic Transmission Air Conditioning AM/FM CD Player] " +
            "       [Chevy Malibu, Ford Fusion or similar 2 or 4 doors Automatic Transmission Air Conditioning AM/FM CD Player Room to seat 5 passengers]  " +
            "       [Chevy Impala, Nissan Altima, Dodge Charger or similar 4 doors Automatic Transmission Air Conditioning AM/FM CD Player]  " +
            "       [Nissan Maxima, Toyota Avalon or similar 4 doors Automatic Transmission Air Conditioning AM/FM Cassette/CD Player]  " +
            "       [Cadillac DTS, Lincoln Towncar or similar 4 doors Automatic Transmission Air Conditioning AM/FM Cassette/CD Player 6-8 Cylinder Performance Tilt/cruise Control]    " +
            "       [Dodge Grand Caravan, Toyota Sienna or similar Room to seat 7 passengers Automatic Transmission Air Conditioning AM/FM CD Player 6-Cylinder Performance Tilt/cruise Control]    " );
    
     
   
    try{form.append(car_image);}catch(Exception e){}
    userName = new TextField("LoginID:", "", 30, TextField.ANY);
    password = new TextField("Password:", "", 30, TextField.PASSWORD);
    
    form.addCommand(cancel);
    form.addCommand(login);
    form.addCommand(contactus);
    form.addCommand(location);
    form.addCommand(imageDisplay);
    form.addCommand(recordDisplay);
    form.addCommand(exit);
    
    form.append(userName);
    form.append(password);
    form.append(mMessageItem);
    form.setTicker(ticker);
    form.setCommandListener(this);



     carSelection = new Form("Select Car from the List");
     technology = new ChoiceGroup(null, Choice.MULTIPLE);
     cancel = new Command("Cancel", Command.EXIT, 1);
     choose = new Command("Choose", Command.SCREEN, 2);

     
     technology.append("Malibu", null);
     technology.append("Impala", null);
     technology.append("Maxima", null);
     technology.append("Cadillac", null);

     technology.append("Sienna", null);
     technology.append("Acadia", null);
     technology.append("Expedition", null);
     technology.append("Dakota", null);

     index = carSelection.append(technology);
     carSelection.addCommand(cancel);
     carSelection.addCommand(choose);
     carSelection.setTicker(ticker);
     carSelection.setCommandListener(this);

     dateForm = new Form("Select Date");
     datein = new DateField("Date In:", DateField.DATE, TimeZone.getTimeZone("GMT"));
     dateout = new DateField("Date Out:", DateField.DATE, TimeZone.getTimeZone("GMT"));


     dateForm.append(datein);
     dateForm.append(dateout);
     dateForm.addCommand(cancel);
     dateForm.addCommand(dateSelect);
     dateForm.setTicker(ticker);
     dateForm.setCommandListener(this);

     
     confirmForm = new Form("Car registration confirmation ");
     confirmForm.addCommand(cancel);
     confirmForm.addCommand(end);
     confirmForm.append(invoiceItem);
     confirmForm.setTicker(ticker);
     confirmForm.setCommandListener(this);
     
     
     showForm = new Form("Registration details");
     showForm.addCommand(cancel);
     showForm.addCommand(showCmd);
     showForm.setCommandListener(this);
      
    // pass = true;
     firstTime = true;
  }

   public void startApp() {

     if (firstTime) {
            display.setCurrent(form);
            firstTime = false;
        }
  }

  public void pauseApp() {}

  public void destroyApp(boolean unconditional) {
    notifyDestroyed();
  }

 
  public void tryAgain() {
    display.setCurrent(form);
  }

  public void Contactus() {
     
    Form ct = new Form("Contact Information");
    ct.append(img);
    ct.append("Email: xyzrental@somecoompany.com");
    ct.append("Phone: 555-555-5555"); 
    
    ct.addCommand(cancel);
    ct.addCommand(ok);
   
    ct.setCommandListener(this);
    display.setCurrent(ct);  
  }
  
  public void Location() {
      
    Form loc = new Form("Location");
    loc.append("FISHERMAN'S WHARF \n350 BEACH STREET \nSAN FRANCISCO, CA 94133 ");
    loc.append("UNION SQUARE 222 MASON STREET SAN FRANCISCO, CA 94102 "); 
    loc.append("WESTERN ADD.CIVIC CENTER 819 ELLIS STREET SAN FRANCISCO, CA 94109  "); 
    loc.append("VAN NESS 1133 VAN NESS AVE SAN FRANCISCO, CA 94109  "); 
    loc.append("MOSCONE CENTER 727 FOLSOM ST. SAN FRANCISCO, CA 94107   "); 
    
    loc.addCommand(cancel);
    loc.addCommand(ok);
    loc.setCommandListener(this);
    display.setCurrent(loc);  
  }
  
 
  
  public void CarImage() {
      
    Form img1 = new Form("Car Information");
    try
    {
        car1 = Image.createImage("/car1.png");
        car2 = Image.createImage("/car2.png");
        car3 = Image.createImage("/car3.png");
        car4 = Image.createImage("/car4.png");
        car5 = Image.createImage("/car5.png");
        car6 = Image.createImage("/car6.png");
        
    }
    catch(Exception e)
    {
        System.err.println(e.getMessage());
    }    
    img1.append(car1);
    img1.append(car2);
    img1.append(car3);
    img1.append(car4);
    img1.append(car5);
    img1.append(car6);
    img1.addCommand(cancel);
    img1.addCommand(ok);
    img1.setCommandListener(this);
    display.setCurrent(img1);  
  }
  
  public void OpenRecord() {
      try{
        r_store = RecordStore.openRecordStore("LocalStorage", true );
      }
      catch(Exception e)
      {
            Alert alert = new Alert("Exception", e.toString(), null, AlertType.WARNING); 
            alert.setTimeout(Alert.FOREVER); 
            display.setCurrent(alert); 
      }
 
  }
  
   public void WriteRecord(String str) {
     byte[] rec = str.getBytes();
       
       try{
        r_store.addRecord(rec, 0, rec.length);
      }
      catch(Exception e)
      {
            Alert alert = new Alert("Exception", e.toString(), null, AlertType.WARNING); 
            alert.setTimeout(Alert.FOREVER); 
            display.setCurrent(alert);
      }
  }
  
  public void CloseRecord() {
      try{
        r_store.closeRecordStore();
      }
      catch(Exception e)
      {
            Alert alert = new Alert("Exception", e.toString(), null, AlertType.WARNING); 
            alert.setTimeout(Alert.FOREVER); 
            display.setCurrent(alert);
      }
   }
  
  public void ReadRecord() {
      recordForm = new Form("Record information");
      try{
        byte[] recData = new byte[5];
        int len;
        
        for(int i=1; i <= r_store.getNumRecords();i++)
        {
            if(r_store.getRecordSize(i) > recData.length)
            {
                recData = new byte[r_store.getRecordSize(i)];   
            }
        
        len = r_store.getRecord(i, recData, 0);
        //System.out.println("------------------------------");
       // System.out.println("Record " + i + " : " + new String(recData, 0, len));
       // System.out.println("------------------------------");  
        
        recordForm.append(new String(recData, 0, len)+"\n");
        
        recordForm.addCommand(cancel);
        recordForm.addCommand(ok);
        
        recordForm.setCommandListener(this);
                 
         display.setCurrent(recordForm);
        } 
      }
      catch(Exception e)
      {
            Alert alert = new Alert("Exception", e.toString(), null, AlertType.WARNING); 
            alert.setTimeout(Alert.FOREVER); 
            display.setCurrent(alert);
      }
  }
  
 
  public void commandAction(Command c, Displayable d) {
     
      String received ="";
      OpenRecord();
     
      String label = c.getLabel();
        if(label.equals("Cancel")) 
        {
            tryAgain();
            userName.setString("");
            password.setString("");
            mMessageItem.setText("");
            //showForm.deleteAll();
        } else if(label.equals("OK")) 
        {
            display.setCurrent(form);
        }
        else if(label.equals("Exit")) {
            notifyDestroyed();
        }   
        else if(label.equals("Location")) {
            Location();
        }  
        else if(label.equals("CarModel")) {
            CarImage();
        }   
        else if(label.equals("RecordDisplay")) {
            ReadRecord();
        } 
        else if(label.equals("Login"))
        {
            gName = userName.getString();
            if(!gName.equals(null))
            {
               WriteRecord(gName);
            }
            CloseRecord();
            OpenRecord();
            ReadRecord();
 
            final String url = "http://localhost:8084/Database/NewServlet?cmd=new&name="+userName.getString()+"&acc="+password.getString();
            Form waitForm = new Form("Waiting...");
            display.setCurrent(waitForm);
            Thread t =  new Thread()
            {
                public void run()
                {
                    connect(url);
                    display.setCurrent(carSelection);
                }
            };
            t.start();               
        }
        if(label.equals("ContactUS"))
        {
            Contactus();
        }
        if(label.equals("Choose"))
        {
        
            if(!valueSet)
                try{
                    wait();
                }
                catch(InterruptedException e)
                {
                   System.out.println("InterruptedException caught"); 
                }
               // System.out.println("*****gmemory is set in main "+gmemory);
                valueSet = false;
                received = gmemory;
               
        
        String msg = "";
        StringItem message[] = new StringItem[technology.size()];
        boolean get[] = new boolean[technology.size()];
        technology.getSelectedFlags(get);
        for (int i = 0; i < get.length; i++)
        {
            if (get[i])
            {
                message[i] = new StringItem("Your Choice is: ", technology.getString(i));
                msg += message[i].getText();
                //System.out.println("msg =" + msg);
            }
        }
        
        carname = msg;
        if(!carname.equals(null)){
        WriteRecord(carname);
        }
       
        carSelection.append("You have selected: "+msg);
        final String url = "http://localhost:8084/Database/NewServlet?cmd=invoice&info="+msg;

        Form waitForm = new Form("Waiting...");
            display.setCurrent(waitForm);
            Thread t =  new Thread()
            {
                public void run()
                {
                    connect(url);
                    display.setCurrent(dateForm);
                }
            };
            t.start();
      
        }
        if(label.equals("Invoice"))
        {
            if(!valueSet)
                try{
                    wait();
                }
                catch(InterruptedException e)
                {
                   System.out.println("InterruptedException caught"); 
                }
               // System.out.println("*****gmemory is set in main "+gmemory);
                valueSet = false;
                received = gmemory;
              
        final String url = "http://localhost:8084/Database/NewServlet?cmd=conf&name="+gName;
       // System.out.println("date in"+datein.getDate());
       // System.out.println("date in"+dateout.getDate());
       // System.out.println("Your name "+gName);
        gdatein = datein.getDate().toString();
        gdateout = dateout.getDate().toString();
        
        if((!gdatein.equals(null)) && (!gdateout.equals(null))){
        WriteRecord(gdatein);
        WriteRecord(gdateout);
        }
        
        Form waitForm = new Form("Waiting...");
            display.setCurrent(waitForm);
            Thread t =  new Thread()
            {
                public void run()
                {
                    connect(url);
                    
                    String cm = "Mr." + gName + "\nCar registered in your name \nyou have selected car model -- " +carname+ "\nFrom date "+ gdatein+"\nTo date "+ gdateout;
                    display.setCurrent(confirmForm);
                    invoiceItem.setText(cm);
                }
            };
            t.start();
        }
        if(label.equals("End"))
        {
            if(!valueSet)
                try{
                    wait();
                }
                catch(InterruptedException e)
                {
                   System.out.println("InterruptedException caught"); 
                }
               // System.out.println("*****gmemory is set in main "+gmemory);
                valueSet = false;
                received = gmemory;  
         
        final String url = "http://localhost:8084/Database/NewServlet?cmd=end&datein="+gdatein.replace(' ', '-')+"&dateout="+gdateout.replace(' ', '-');
        
        Form waitForm = new Form("Waiting...");
        display.setCurrent(waitForm);
        Thread t =  new Thread()
        {
            public void run()
            {
                connect(url);
                display.setCurrent(showForm); 
                
                
                
                showForm.append("Car Booking Confirmation"+"\n");
                showForm.append("Mr. "+gName);
                showForm.append("\n"+"------------------------------");
                    
                Random num = new Random();
                int n1 = num.nextInt()%150;
                String a = Integer.toString(n1);
                num.setSeed(System.currentTimeMillis());
                int n2 = num.nextInt()%75;
                String b = Integer.toString(n2);
                String cnf;    
                cnf = "";
                n1=n2=0;
                cnf = a.toString()+"kMB"+b.toString()+"CR";
                showForm.append("Your confirmation Number is "+cnf);
                showForm.append("\n"+"------------------------------");                  
               
                
            }
        };
        t.start();
        userName.setString("");
        password.setString("");
      
        }
        if(label.equals("show"))
        {
            if(!valueSet)
                try{
                    wait();
                }
                catch(InterruptedException e)
                {
                   System.out.println("InterruptedException caught"); 
                }
               // System.out.println("*****gmemory is set in main "+gmemory);
                valueSet = false;
                received = gmemory;
                
                //To delete previous contents in the showForm(form)
                showForm.deleteAll();
                
                display.setCurrent(form);
                
        }  
      
        CloseRecord();
}
  
 private synchronized void connect(String url)
 {
        HttpConnection hc = null;
        InputStream in = null;

        try
        {
            hc = (HttpConnection)Connector.open(url);

            in = hc.openInputStream();

            int contentLength = (int)hc.getLength();
            byte[] raw = new byte[contentLength];
            int length = in.read(raw);

            in.close();
            hc.close();

            String s = new String(raw, 0, length);
          
            if(valueSet)
                try{
                    wait();
                }
                catch(InterruptedException e)
                {
                    System.out.println("InterruptedException caught");
                }
                gmemory = s;
                valueSet = true;
              //  System.out.println("*****Value set "+ gmemory);
                notify();
                       
           // System.out.println("****from server: "+s);
            
            }
            catch (IOException ioe)
            {
                mMessageItem.setText(ioe.toString());
            }
            display.setCurrent(form);
        }
 
}



