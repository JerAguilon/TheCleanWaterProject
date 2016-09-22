package controller;

import javafx.fxml.FXML;
import javafx.event.*;

/**
 * Created by jeremy on 9/21/16.
 */
public interface ILoginController {
    boolean isLockedOut();
    void validate();
}