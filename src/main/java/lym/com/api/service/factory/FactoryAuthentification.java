package lym.com.api.service.factory;

import lym.com.api.service.authentification.IServiceAuthentification;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LYMYTZ
 */
public class FactoryAuthentification {

    public static IServiceAuthentification getInstance(String classe) {
        return (IServiceAuthentification) FactoryServiceGeneric.getInstance(classe);
    }
}
