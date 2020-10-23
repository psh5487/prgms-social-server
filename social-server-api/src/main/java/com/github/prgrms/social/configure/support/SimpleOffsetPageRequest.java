package com.github.prgrms.social.configure.support;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleOffsetPageRequest implements Pageable {

  private final long offset;

  private final int limit;

  public SimpleOffsetPageRequest() {
    this(0, 5);
  }

  public SimpleOffsetPageRequest(long offset, int limit) {
    if (offset < 0)
      throw new IllegalArgumentException("Offset must be greater or equals to zero.");
    if (limit < 1)
      throw new IllegalArgumentException("Limit must be greater than zero.");

    this.offset = offset;
    this.limit = limit;
  }

  @Override
  public long offset() {
    return offset;
  }

  @Override
  public int limit() {
    return limit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("offset", offset)
      .append("limit", limit)
      .toString();
  }

}