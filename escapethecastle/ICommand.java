/**
 * Command pattern interface.
 */
public interface ICommand  
{
    void execute();
    void setReceiver(IReceiver target);
}
