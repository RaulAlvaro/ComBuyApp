package com.example.raul.combuyapp;

import com.example.raul.combuyapp.models.LocalNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 24/04/18.
 */

public interface PrincipalContract {
    void getList(List<LocalNegocio> list);
}
