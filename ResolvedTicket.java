package com.Paul_McGie;

import java.util.Date;

/**
 * Created by pmcgie on 3/19/2017.
 */
public class ResolvedTicket extends Ticket {

    private Date resDate;
    private String Solution;
    private int ID;

    public ResolvedTicket(int ID, String desc, String p, String rep, Date date, Date resDate, String Solution) {
        super(desc,p, rep, date);
        this.resDate = resDate;
        this.Solution = Solution;
        this.ID = ID;
    }



    @Override
    public String toString(){
        return("ID: " + this.ticketID + " Issue: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported + " Resolution date: " +
                this.resDate + " Resolution Solution: " + this.Solution);
    }
}
