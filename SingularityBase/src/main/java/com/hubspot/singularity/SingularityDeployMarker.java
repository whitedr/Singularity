package com.hubspot.singularity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;

public class SingularityDeployMarker implements Comparable<SingularityDeployMarker> {

  private final String requestId;
  private final String deployId;
  private final long timestamp;
  private final Optional<String> user;
  private final Optional<String> message;

  @JsonCreator
  public SingularityDeployMarker(@JsonProperty("requestId") String requestId, @JsonProperty("deployId") String deployId, @JsonProperty("timestamp") long timestamp,
      @JsonProperty("user") Optional<String> user, @JsonProperty("message") Optional<String> message) {
    this.requestId = requestId;
    this.deployId = deployId;
    this.timestamp = timestamp;
    this.user = user;
    this.message = message;
  }

  @Override
  public int compareTo(SingularityDeployMarker o) {
    return ComparisonChain.start()
        .compare(timestamp, o.getTimestamp())
        .compare(deployId, o.getDeployId())
        .result();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SingularityDeployMarker that = (SingularityDeployMarker) o;
    return Objects.equals(requestId, that.requestId) &&
        Objects.equals(deployId, that.deployId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, deployId);
  }

  public String getRequestId() {
    return requestId;
  }

  public String getDeployId() {
    return deployId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public Optional<String> getUser() {
    return user;
  }

  public Optional<String> getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "SingularityDeployMarker{" +
        "requestId='" + requestId + '\'' +
        ", deployId='" + deployId + '\'' +
        ", timestamp=" + timestamp +
        ", user=" + user +
        ", message=" + message +
        '}';
  }
}
