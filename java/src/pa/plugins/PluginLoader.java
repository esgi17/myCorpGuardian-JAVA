package pa.plugins;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pa.controllers.Plugins;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarFile;

public class PluginLoader {
    private ObservableList<String> pluginsName;
    private String[] pluginsPath;
    private ArrayList<Class> plugins;

    public PluginLoader() {
        this.plugins = new ArrayList<>();
    }

    public PluginLoader(String[] files) {
        this();
        this.pluginsPath = files;
    }

    public Plugins[] loadPlugins() throws Exception {
        this.initializeLoader();

        Plugins[] tmpPlugins = new Plugins[this.plugins.size()];

        for( int i = 0 ; i < tmpPlugins.length ; i++) {
            tmpPlugins[i] = (Plugins)((Class)this.plugins.get(i)).newInstance();
        }

        return tmpPlugins;
    }

    public void initializeLoader() throws Exception {
        if( this.pluginsPath == null ||this.pluginsPath.length == 0 ) {
            throw new Exception("No files selected");
        }

        if( this.plugins.size() != 0 ) {
            return ;
        }

        File[] f = new File[this.pluginsPath.length];
        URLClassLoader loader;
        String tmp = "";
        Enumeration enumeration;

        Class tmpClass = null;

        for(int i = 0 ; i < this.pluginsPath.length; i++) {
            f[i] = new File( this.pluginsPath[i] );
            if( !f[i].exists()) {
                break;
            }

            URL u = f[i].toURL();
            loader = new URLClassLoader(new URL[] {u});

            JarFile jar = new JarFile(f[i].getAbsolutePath());

            enumeration = jar.entries();

            while(enumeration.hasMoreElements()) {
                tmp = enumeration.nextElement().toString();

                if( tmp.length() > 6 && tmp.substring(tmp.length() - 6).compareTo(".class") == 0 ) {
                    tmp = tmp.substring(0, tmp.length()-6);
                    tmp = tmp.replaceAll("/",".");

                    tmpClass = Class.forName(tmp, true, loader);

                    for( int j = 0 ; j < tmpClass.getInterfaces().length ; i++ ) {
                        this.plugins.add(tmpClass);
                    }
                }
            }

        }
    }
}
