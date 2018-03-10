# Cogimag

*********
## Description
A Java library of utilities for the keyboard and for JavaFX dialog boxes. 

The keyboard package aims to simplify the printing of characters to the screen
using java.awt.Robot or the EventQueue. It includes a HashMap of keystrokes,
a HashMap generation tool, test windows for the maps and implementations of
Robot and EventQueue typing utilities.

The JavaFX dialog boxes are less useful in light of the dialog boxes of JavaFX 8. Please
consider javafx.scene.control.Dialog and subclasses before using the classes in this
library.

The JavaFX combo box may be of some use. It extends the standard javafx.scene.control.ComboBox
by responding to keystrokes and selecting items from the Collection. Only the first letter is
matched. A progressive filter awaits implementation.
