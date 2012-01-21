/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Nehru
 */
public class NewServlet extends HttpServlet {
   
    RentalInfo info = new RentalInfo();
    ArrayList<RentalInfo> store = new ArrayList<RentalInfo>();
    
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
             
           /* out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
             
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        //processRequest(request, response);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String command = request.getParameter("cmd");
        //out.println("command is = "+command);
        //System.out.println("command is "+command);
        if(command.equals("new"))
        {
            ReadNewUser(request, response);
        } 
        else if(command.equals("invoice"))
        {
            Invoice(request,response);
        }
        else if(command.equals("conf"))
        {

            Confirmation(request, response);
        }
        else if(command.equals("end"))
        {

            date(request, response);
            
            store.add(info);
            
            Iterator<RentalInfo> it = store.iterator();
            while(it.hasNext())
            {
                RentalInfo s = it.next();
                System.out.println("Tomcat: "+s.getName());
                System.out.println("Tomcat: "+s.getModel());
                System.out.println("Tomcat: "+s.getDatein());
                System.out.println("Tomcat: "+s.getDateout());
                System.out.println("**************************");
            }
        }
       
    } 
    
    private void ReadNewUser(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String acc = request.getParameter("acc");
        info.setName(name);
        out.println("Hello Mr. " + name + " you are success fully created account");
        out.close();
    }
    
    private void Invoice(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException
    {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();
        // System.out.println("****inside server invoice");
         String carinfo = req.getParameter("info");
         info.setModel(carinfo);
         out.println("you have reserved car " + carinfo );
         out.close();

    }
    
    private void Confirmation(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException
    {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();

         String name = req.getParameter("name");

         out.println("Mr.  " + name);
         //out.println("Car is reserved for the dates below:");

         //out.println("Your confirmation number is: "+ "A123FgRT");
         out.close();

    }
    
    private void date(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException
    {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();

         String datein = req.getParameter("datein");
         String dateout = req.getParameter("dateout");
         
         info.setDatein(datein);
         info.setDateout(dateout);
         out.println("Booking date from  " + datein + "to "+dateout);
         //out.println("Car is reserved for the dates below:");

         //out.println("Your confirmation number is: "+ "A123FgRT");
         out.close();

    }
    

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}

class RentalInfo
{
    private String name;
    private String model;
    private String datein;
    private String dateout;

    public RentalInfo() {
        this.name = "";
        this.model = "";
        this.datein = "";
        this.dateout = "";
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }

    public void setDateout(String dateout) {
        this.dateout = dateout;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatein() {
        return datein;
    }

    public String getDateout() {
        return dateout;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }
    
       
}



