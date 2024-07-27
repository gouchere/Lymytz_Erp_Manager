package lym.com.api.service.factory;

import lym.com.api.Initialisation;

/**
 *
 * @author LYMYTZ
 */
public class FactoryServiceGeneric {

    public FactoryServiceGeneric() {
    }

    public static Object getInstance(String entityName) {
        // trouve la classe customer à instancier dans une fichier properties
        Initialisation.reloadServicesConfig();
//        try {
//            String path=Initialisation.fileService.getProperty(entityName);
//            @SuppressWarnings("rawtypes")
//			Class entity = Class.forName(path);
//            @SuppressWarnings("unchecked")
//			Object ientity = entity.getDeclaredConstructor().newInstance();
//            return ientity;
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
//            Logger.getLogger(FactoryServiceGeneric.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }
}
