public interface Observador {
	public void onSelectCard(final String combo); 
	public void onSliderChange(final int value);
	public void onSelectCardBoard(final String card); 
	public void onDeselectCardBoard(final String card);
	public void onDeselectCard(final String card);
}
