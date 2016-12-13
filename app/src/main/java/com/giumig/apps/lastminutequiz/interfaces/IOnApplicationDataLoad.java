package com.giumig.apps.lastminutequiz.interfaces;

import com.giumig.apps.lastminutequiz.model.Good;

import java.util.List;

/**
 * Created by gius on 13/12/16.
 */

public interface IOnApplicationDataLoad {

    public void onApplicationDataLoadSuccess(List<Good> goods);
    public void onApplicationDataLoadfail();

}
