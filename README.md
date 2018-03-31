# Cogimag

*********
## Description
A Java library of utilities for the keyboard and for JavaFX dialog boxes. 

The keyboard packages aim to simplify the printing of characters to the screen
using java.awt.Robot or the EventQueue. There are two packages, one for Swing
applications and one for FX applications. The Swing version (classes denoted by
Awt prefix) includes a HashMap of keystrokes, a HashMap generation tool, test 
windows for the maps and implementations of Robot and EventQueue typing utilities.
The FX version (classes denoted by Fx prefix) omits the Robot implementation, 
as AwtRoboSteno may be used in both types of application.

When deciding whether to use Robot or an event dispatching technique, consider where
the keystrokes must be sent. Posting events to an event queue can reach only the
application that calls the method. If one wishes to type to the platform's event
queue, Robot must be used. For more information, see www.dickbaldwin.com/java/Java1472.htm,
https://coderanch.com/t/532652/java/Difference-Robot-EventQueue-class


The JavaFX dialog boxes are less useful in light of the dialog boxes of JavaFX 8. Please
consider javafx.scene.control.Dialog and subclasses before using the classes in this
library.

The JavaFX combo box may be of some use. It extends the standard javafx.scene.control.ComboBox
by responding to keystrokes and selecting items from the Collection. Only the first letter is
matched. A progressive filter awaits implementation.
