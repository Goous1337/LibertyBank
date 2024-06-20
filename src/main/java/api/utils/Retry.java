package api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class Retry implements TestExecutionExceptionHandler {
    private static final Logger LOG = LogManager.getLogger("Retry");
    private static final int DEFAULT_MAX_RETRIES = 3;
    private int maxRetries;

    public Retry(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Retry() {
        this(DEFAULT_MAX_RETRIES);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        for (int i = 0; i < maxRetries; i++) {
            try {
                LOG.info(String.format("Retrying test method: " + context.getTestMethod().get().getName() +
                        ", attempt " + (i + 1)));
                context.getTestMethod().get().invoke(context.getTestInstance().get());
                return;
            } catch (Exception e) {
                throwable.addSuppressed(e);
            }
        }
        throw throwable;
    }
}
