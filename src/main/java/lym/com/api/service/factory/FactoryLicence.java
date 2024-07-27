package lym.com.api.service.factory;

import lym.com.api.service.ILicenceService;

/**
 *
 * @author LYMYTZ
 */
public class FactoryLicence {

    public static ILicenceService getInstance(String classe) {
        return (ILicenceService) FactoryServiceGeneric.getInstance(classe);
    }
}
