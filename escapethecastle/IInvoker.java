/**
 * Interface for command invoker.
 */
public interface IInvoker {
    void setCommand(ICommand command);

    void invoke();
}
