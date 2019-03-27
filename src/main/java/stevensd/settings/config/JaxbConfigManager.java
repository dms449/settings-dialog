package stevensd.settings.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

public class JaxbConfigManager<T> extends ConfigManager<T> {
    public JAXBContext context;

    public T config;
    public T defaultConfig;

    public File configFile;
    public File defaultConfigFIle;


    public JaxbConfigManager(Class<T> clazz)  throws JAXBException{
        this.context = JAXBContext.newInstance(clazz);
    }

    public JaxbConfigManager(Class<T> clazz, URL url) throws JAXBException{


    }

    /**
     * Update the config object, T, and marshall it (write it to file).
     *
     * @param file
     * @return success
     */
    @Override
    public boolean save(File file){
        try {
            save(config);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.marshal(config, file);
            configFile = file;
            return true;
        } catch (JAXBException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update the config object, T, and marshall it (write it to file).
     *
     * @return success
     */
    public boolean save(){
        if (configFile != null){
            return save(configFile);
        } else {
            return false;
        }
    }

    /**
     * load a config object from file
     * @param file
     * @return
     */
    @Override
    public boolean load(File file) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T object = (T) unmarshaller.unmarshal(file);
            config = object;
            return load();
        } catch (JAXBException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * load the last loaded config object
     * @return
     */
    @Override
    public boolean load(){
        if (config != null){
            load(config);
            return true;
        } else {
            return false;
        }
    }

}
