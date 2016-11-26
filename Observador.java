import java.util.ArrayList;
public interface Observador {
	public void onSelectCard(final String text); 
	public void onSliderChange(final int value);
	public void onSelectCardBoard(final String card); 
	public void onDeselectCardBoard(final String card);
	public void onDeselectCard(final String card);
	public void onRangeProccess(final ArrayList<String> cards);
	public void onShowText(final String card);
	public void onClearCards();
}
