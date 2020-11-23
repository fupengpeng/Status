package com.fpp.status.model;

import java.util.List;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/15  15:21
 */

public interface RequestListener<T> {

    void success(T data);

    void fail(Exception e);


}
