package ro.sdi.core.exceptions;

public class ElementNotFoundException extends ProgramException
{
    public ElementNotFoundException(String message)
    {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause)
    {
        super(cause);
    }
}
