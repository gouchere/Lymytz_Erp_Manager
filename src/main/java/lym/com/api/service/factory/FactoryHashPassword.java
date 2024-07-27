package lym.com.api.service.factory;

import lym.com.api.service.authentification.IHashPassWord;

/**
 *
 * @author LYMYTZ
 */
public class FactoryHashPassword {

    public static IHashPassWord getInstance(String classe) {
        return (IHashPassWord) FactoryServiceGeneric.getInstance(classe);
    }
}
