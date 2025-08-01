/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.polaris.service.quarkus.metrics;

import io.micrometer.common.annotation.ValueExpressionResolver;
import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.polaris.core.context.RealmContext;

@ApplicationScoped
public class QuarkusValueExpressionResolver implements ValueExpressionResolver {

  @Inject QuarkusMetricsConfiguration metricsConfiguration;

  @Override
  @Nullable
  public String resolve(@Nonnull String expression, @Nullable Object parameter) {
    // TODO maybe replace with CEL of some expression engine and make this more generic
    if (metricsConfiguration.realmIdTag().enableInApiMetrics()
        && parameter instanceof RealmContext realmContext
        && expression.equals("realmIdentifier")) {
      return realmContext.getRealmIdentifier();
    }
    return null;
  }
}
