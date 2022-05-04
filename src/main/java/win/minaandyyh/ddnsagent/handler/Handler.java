package win.minaandyyh.ddnsagent.handler;

/**
 * Update handler interface
 *
 * @author masteryyh
 */
public interface Handler {
    /**
     * Handle the update operation.
     *
     * @throws Exception Throws when error occurs sending request and parsing JSON response
     */
    void handle() throws Exception;
}
