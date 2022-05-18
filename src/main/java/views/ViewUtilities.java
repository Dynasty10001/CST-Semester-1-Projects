package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

    public class ViewUtilities
    {

        /**
         * This is a utility method that will Return a tableColumn for use in a TableView within javafx. This Is a
         * generic method and will take two parameters, on is the label of the column, and the other is the attribute of
         * the value that is to be stored here
         * Note: be careful with this, as it returns an unknown type, I was going to cast the result into another
         * parameter that contained a class reference but it worked without it.
         * @param label the text to be held for column name ( ie. Team Name)
         * @param attributeString the name of the attribute to be displayed (ie, tableName) this has to be reflected by
         *                           in a class that will be placed in the table
         * @param <T> The type of the object this method is generic
         * @return
         */
        public static <T> TableColumn<T, String> getColumn(String label, String attributeString)
        {
            System.out.println("created column for " + attributeString);
            TableColumn<T, String> returnCol = new TableColumn<T, String>(label);
            returnCol.setCellValueFactory(new PropertyValueFactory<T, String>(attributeString));
            return  returnCol;
        }

        public static void test(){

        }

    }

