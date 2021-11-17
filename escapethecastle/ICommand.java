/**
 * Command pattern interface.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ICommand  
{
    void execute();
    void setReceiver(IReceiver target);
}
