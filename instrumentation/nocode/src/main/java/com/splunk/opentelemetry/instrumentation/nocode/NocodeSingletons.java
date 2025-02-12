package com.splunk.opentelemetry.instrumentation.nocode;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;

public class NocodeSingletons {
  private static final Instrumenter<NocodeMethodInvocation, Void> INSTRUMENTER;

  static {
    INSTRUMENTER = Instrumenter.<NocodeMethodInvocation,Void>builder(
        GlobalOpenTelemetry.get(),
        "com.splunk.opentelemetry.instrumentation.nocode",
        new NocodeSpanNameExtractor()).addAttributesExtractor(new NocodeAttributesExtractor())
        .buildInstrumenter(new NocodeSpanKindExtractor());
  }

  public static Instrumenter<NocodeMethodInvocation, Void> instrumentor() {
    return INSTRUMENTER;
  }
}
