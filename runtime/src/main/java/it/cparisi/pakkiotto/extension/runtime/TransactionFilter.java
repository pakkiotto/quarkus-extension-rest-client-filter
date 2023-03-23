package it.cparisi.pakkiotto.extension.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;
import java.util.function.Supplier;

@Provider
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final static Logger LOG = LoggerFactory.getLogger("Application filter");
    private final static Supplier<String> RANDOM_UUID_SUPPLIER = () -> UUID.randomUUID().toString();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        LOG.info("start filter");
        var transactionId = containerRequestContext.getHeaderString("X-transactionId");
        if (transactionId != null || transactionId.trim().isEmpty()) {
            LOG.info("Generate new transactionId");
            MDC.put("transactionId", RANDOM_UUID_SUPPLIER.get());
        } else {
            LOG.info("Found transactionId in header");
            MDC.put("transactionId", transactionId);
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("X-transactionId", MDC.get("transactionId"));
        LOG.info("end filter with transactionId: {}", MDC.get("transactionId"));
    }
}
