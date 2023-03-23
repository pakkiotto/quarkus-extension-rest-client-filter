package it.cparisi.pakkiotto.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.resteasy.common.spi.ResteasyJaxrsProviderBuildItem;
import io.quarkus.undertow.deployment.ServletBuildItem;
import it.cparisi.pakkiotto.extension.runtime.GreetingExtensionServlet;
import it.cparisi.pakkiotto.extension.runtime.LoggingInterceptor;
import it.cparisi.pakkiotto.extension.runtime.TransactionFilter;

class PakkiottoExtensionProcessor {

    private static final String FEATURE = "pakkiotto-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ServletBuildItem createServlet() {
        ServletBuildItem servletBuildItem = ServletBuildItem.builder("pakkiotto-extension", GreetingExtensionServlet.class.getName())
                .addMapping("/greeting")
                .build();
        return servletBuildItem;
    }

    @BuildStep
    AdditionalBeanBuildItem loggingInterceptor() {
        return AdditionalBeanBuildItem.unremovableOf(LoggingInterceptor.class);
    }

    @BuildStep
    ResteasyJaxrsProviderBuildItem createOpentracingFilter() {
        return new ResteasyJaxrsProviderBuildItem(TransactionFilter.class.getName());
    }
}
