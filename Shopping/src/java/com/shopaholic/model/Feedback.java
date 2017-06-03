/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.model;

import java.util.Date;

/**
 *
 * @author Pritam
 */
public class Feedback {
    private String feedbackID,feedbackDescription;
    private Date feedbackDate;
    private int rating;

    public Feedback(String feedbackID, String feedbackDescription, Date feedbackDate, int rating) {
        this.feedbackID = feedbackID;
        this.feedbackDescription = feedbackDescription;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
    
}
