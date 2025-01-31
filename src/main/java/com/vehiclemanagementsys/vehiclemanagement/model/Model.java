package com.vehiclemanagementsys.vehiclemanagement.model;

import com.vehiclemanagementsys.vehiclemanagement.view.ViewFactory;

public class Model {
    private static Model model;
    private ViewFactory viewFactory;
    private User user;

    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }

        return model;
    }

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public void resetViewFactory(){
        viewFactory = new ViewFactory();
    }
}
