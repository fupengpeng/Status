package com.fpp.status.http.http;

import java.io.InputStream;

/**
 * Created by fighting on 2017/4/24.
 */

public class RealResponse {
    public InputStream inputStream;
    public InputStream errorStream;
    public int code;
    public long contentLength;
    public Exception exception;
}
