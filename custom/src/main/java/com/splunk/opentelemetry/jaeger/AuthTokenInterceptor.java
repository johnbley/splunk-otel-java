/*
 * Copyright Splunk Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.splunk.opentelemetry.jaeger;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthTokenInterceptor implements Interceptor {
  static final String TOKEN_HEADER = "X-SF-TOKEN";

  private final String signalfxAuthToken;

  public AuthTokenInterceptor(String token) {
    this.signalfxAuthToken = token;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    request = request.newBuilder().addHeader(TOKEN_HEADER, signalfxAuthToken).build();
    return chain.proceed(request);
  }
}
