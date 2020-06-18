package ro.sdi.core.exceptions;

public class AlreadyExistingElementException extends ProgramException
{
    public AlreadyExistingElementException(String message)
    {
        super(message);
    }

    public AlreadyExistingElementException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AlreadyExistingElementException(Throwable cause)
    {
        super(cause);
    }
}
