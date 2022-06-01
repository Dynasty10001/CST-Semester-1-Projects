package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TournamentController;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import models.Game;
import models.Tournament;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class FilterDatePopup
{
	public DatePicker datePicker;
	
	
	static SchedulesView view;
	
	public void submitDate(ActionEvent actionEvent)
	{
		
		GameController gc = new GameController(App.connection);
		try
		{
			
			view.gameList.getItems().setAll(
					gc.getSchedule(new TournamentController(App.connection).getTournament()).stream().filter(e->{
						int year = e.getStartTime().getYear() + 1900;
						int month = e.getStartTime().getMonth() + 1;
						int day = e.getStartTime().getDate();
						
						System.out.println("Game Day " + day + " Month " + month + " Year " + year);
						
						LocalDate date = datePicker.getValue();
						System.out.println(" Current Day " + date.getDayOfMonth() + " Month " + date.getMonthValue() +
						                   " " +
						                     "Year " + date.getYear());
						return date.getDayOfMonth() == day&&
						       date.getMonthValue() == month &&
						       date.getYear() == year;
						       
					}).collect(Collectors.toCollection(ArrayList::new)));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setView(SchedulesView view)
	{
		FilterDatePopup.view = view;
	}
}
