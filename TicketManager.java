package com.Paul_McGie;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketManager {

    LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
    LinkedList<Ticket> ticketsResolved = new LinkedList<Ticket>();

    private void mainMenu() throws Exception {

        //Once file opens cycle through items in file and add to ticketQueue
        try {
            Create_Original_Queue();
        } catch (Exception e) {
        }

        //Initial Menu
        RunInitialMenu();
    }

    private void RunInitialMenu() throws IOException, ParseException {

        //Create main menu GUI
        InitialOptionsGUI mainmenu = new InitialOptionsGUI();

        //Need to have program running
        while (mainmenu.isVisible()) {
        }

        //Cycle through initial options
        if (mainmenu.getMainresponse() == 1) {
            addTickets();
        }
        else if (mainmenu.getMainresponse()  == 2) {
            deleteTicketById();
        }
        else if (mainmenu.getMainresponse()  == 3) {
            deleteTicketByIssue();
        }
        else if(mainmenu.getMainresponse()  == 4) {
            searchByIssue();
        }
        else if (mainmenu.getMainresponse()  == 5) {
            printAllTickets();
        }
        else if (mainmenu.getMainresponse()  == 6 ) {
        }
        else {
            printAllTickets();
        }
    }


    private void Create_Original_Queue() throws IOException, ParseException {
        //Open up file and start reading lines

        try {
        FileReader reader = new FileReader("Open_Tickets_Report.txt");
        BufferedReader bufReader = new BufferedReader(reader);
        String line = bufReader.readLine();

        ArrayList <Integer> ID_Nums = new ArrayList<Integer>();

        while (line != null) {
            //Split up to extract insert into Queue
            String[] Words = line.split(" ");

            //format string to date
            String fromDate = Words[9];
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dtt = df.parse(fromDate);

            //ticketIdCounter
            int New_Num = Integer.parseInt(Words[1]);

            ID_Nums.add(New_Num);

            //Generate ticket into list
            Ticket t = new Ticket(Words[3], Words[5], Words[7],dtt);

            //Ticket(String desc, int p, String rep, Date date)
            //return("ID: " + this.ticketID + " Issue: " + this.description + " Priority: " + this.priority + " Reported by: "
                    //+ this.reporter + " Reported on: " + this.dateReported);

            //re-priortize
            addTicketInPriorityOrder(t);

            //Add to Queue
            ticketQueue.add(t);

            //Read next line
            line = bufReader.readLine();

        }

        //Close out reader
        bufReader.close();

        int Max_Num = Collections.max(ID_Nums) + 1; //find max within list to find starting point

        Ticket.setSP_Ticket(Max_Num); //set Ticket to highest number + 1

        //Show user current ticket list
        printAllTickets();
    } catch (IOException e) {
        }
    }


    protected LinkedList<Ticket> searchDescription(String TicketIssue) {
        // TODO problem 3: complete this method - it should return a
        //Create new list for return of values
        LinkedList <Ticket> TicketFind = new LinkedList<Ticket>();

        // The search should be case-insensitive
        String convertSearch = TicketIssue.toLowerCase();

        // list of the tickets that contain the searchString in the description.
        for (Ticket a:ticketQueue) {
            String convertIssue = a.description.toLowerCase();
            if (convertIssue.contains(convertSearch)) {
                TicketFind.add(a);
            }
        }

        // Return an empty list if there are no matching Tickets.
        if (TicketFind.size() == 0) {
            System.out.println("No tickets found with the searched issue.");
        }

        return TicketFind;  //replace this with a return statement that returns a list
    }


    protected void searchByIssue() {
        // TODO problem 4 implement this method. Return a list of matching tickets.

        // Ask user for search term
        String TicketIssue = Input.getStringInput("Enter ticket issue");

        // Use searchDescription() method to get list of matching Tickets
        LinkedList<Ticket> TicketFind = searchDescription(TicketIssue);

        // display list
        for (Ticket a:TicketFind) {
            System.out.println(a);
        }
    }


    protected void deleteTicketByIssue() throws IOException, ParseException {
        // TODO problem 5 implement this method

        // Use searchDescription to create list of matching Tickets
        searchByIssue();

        // Delete ticket
        FinalDeletion();

    }

    protected void FinalDeletion() throws IOException, ParseException {

        //Ask user for ticket ID
        TicketDeletion DeletionTicket = new TicketDeletion();

        while (DeletionTicket.isVisible()) {
        }

        int deleteID = DeletionTicket.getIDNum();

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;

        //Ask until valid ticket
        while (!found) {
            for (Ticket ticket : ticketQueue) {
                if (ticket.getTicketID() == deleteID) {
                    found = true;

                    //create method to add resolved tickets
                    addResolvedTickets(ticket,deleteID);

                    //Remove Ticket
                    ticketQueue.remove(ticket);
                    System.out.println("Ticket Deleted: " + deleteID);
                    break; //don't need the loop any more.

                    //if you decide not to delete ticket
                } else if (!found) {
                    if (deleteID == 0) {
                        System.out.println("No Tickets Found");
                        found = true;
                    }
                }
            }


            if (found == false) {
            System.out.println("Ticket ID not found, no ticket deleted");
            deleteID = Input.getPositiveIntInput("Enter ID of ticket to delete" +
                    "(enter 0 to exit)");
            }
        }
        RunInitialMenu();
    }

    protected void addResolvedTickets(Ticket ticket,int deleteID) throws IOException {

        //Enter resolved tickets variables
        Date resdate = new Date();
        String Solution = Input.getStringInput("Enter solution");

        //Add object
        ResolvedTicket t = new ResolvedTicket(deleteID,ticket.getDescription(),ticket.getPriority(), ticket.getReporter(),
                ticket.getDateReported(),resdate,Solution);

        //Add ticketsResolved to list
        ticketsResolved.add(t);

        //display ticket
        System.out.println(t);

        //write new file
        FileWriter writer = new FileWriter(resdate + " Resolve_Tickets_Report.txt");
        BufferedWriter bufWriter = new BufferedWriter(writer);

        //add information to file for the day
        bufWriter.append("\n");
        bufWriter.append(String.valueOf(t));

        //Close out file
        bufWriter.close();
    }

    protected void deleteTicketById() throws IOException, ParseException {

        printAllTickets();   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }

        FinalDeletion();

        printAllTickets();  //print updated list
    }


    protected void addTickets() throws IOException, ParseException {

        Boolean Continue = true;

        //create a while loop that asks if user is done with entry
        while (Continue == true) {

        //Create main menu GUI
        AddTicket Tickets_Add = new AddTicket();

        //Show visible until no more issues to record
        while (Tickets_Add.isVisible()) {
        }

            Date dateReported = new Date(); //Default constructor creates Date with current date/time

            String description = Tickets_Add.getProblem();
            String reporter = Tickets_Add.getReporterName();
            String priority = Tickets_Add.getPriorityEntry();

            Ticket t = new Ticket(description, priority, reporter, dateReported);

            //ticketQueue.add(t);
            addTicketInPriorityOrder(t);

            printAllTickets();

            //Pop up continue application
            ContinueGUI CheckContinue = new ContinueGUI();

            while (CheckContinue.isVisible()) {
            }

            //Determines with boolean whether to continue with entries
            if (CheckContinue.isDetermineContinue()) {
                Continue = true;
            } else {
                Continue = false;
            }

        }

        RunInitialMenu();

    }


    protected void addTicketInPriorityOrder(Ticket newTicket){

        //Logic: assume the list is either empty or sorted

        if (ticketQueue.size() == 0 ) {//Special case - if list is empty, add ticket and return
            ticketQueue.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end

        String newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < ticketQueue.size() ; x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority == ticketQueue.get(x).getPriority()) {
                ticketQueue.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        ticketQueue.addLast(newTicket);
    }


    protected void printAllTickets() throws IOException {
        System.out.println(" ------- All open tickets ----------");

        //write new file
        FileWriter writer = new FileWriter( "Open_Tickets_Report.txt");
        BufferedWriter bufWriter = new BufferedWriter(writer);

        for (Ticket t : ticketQueue ) {
            System.out.println(t); // This calls the  toString method for the Ticket object.

            //add information to file for the day
            bufWriter.append(String.valueOf(t));
            bufWriter.append("\n");

        }

        System.out.println(" ------- End of ticket list ----------");

        //Close out file
        bufWriter.close();

    }


    /* Main is hiding down here. Create a TicketManager object, and call the mainMenu method.
    Avoids having to make all of the methods in this class static. */
    public static void main(String[] args) throws Exception {
        TicketManager manager = new TicketManager();

        manager.mainMenu();
    }

}

